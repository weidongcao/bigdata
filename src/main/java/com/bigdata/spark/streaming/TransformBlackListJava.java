package bigdata.spark.streaming;

import com.google.common.base.Optional;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.api.java.function.PairFunction;
import org.apache.spark.streaming.Durations;
import org.apache.spark.streaming.api.java.JavaDStream;
import org.apache.spark.streaming.api.java.JavaPairDStream;
import org.apache.spark.streaming.api.java.JavaReceiverInputDStream;
import org.apache.spark.streaming.api.java.JavaStreamingContext;
import scala.Tuple2;

import java.util.ArrayList;
import java.util.List;

/**
 * 北风网
 * 中华石杉
 * Spark从入门到精通
 * 广告实时黑名单过滤案例
 * Created by Administrator on 2016/11/7.
 */
public class TransformBlackListJava {
    public static void main(String[] args) {
//        System.setProperty("user.name", "dong");
        SparkConf conf = new SparkConf()
                .setMaster("spark://spark.don.com:7077")
                .setAppName("TransformBlackListJava");
        JavaStreamingContext jssc = new JavaStreamingContext(conf, Durations.seconds(5));
//        JavaStreamingContext jssc = new JavaStreamingContext(sc, Durations.seconds(5));

        List<Tuple2<String, Boolean>> blacklist = new ArrayList<Tuple2<String, Boolean>>();
        blacklist.add(new Tuple2<String, Boolean>("huan", true));

        final JavaPairRDD<String, Boolean> blackListRDD = jssc.sc().parallelizePairs(blacklist);

        JavaReceiverInputDStream<String> adsClickLogDStream = jssc.socketTextStream("spark.don.com", 9999);

        final JavaPairDStream<String, String> userAdsClickLogDStream = adsClickLogDStream.mapToPair(
                new PairFunction<String, String, String>() {
                    @Override
                    public Tuple2<String, String> call(String log) throws Exception {
                        return new Tuple2<String, String>(log.split(" ")[1], log);
                    }
                }
        );

        JavaDStream<String> validAdsClickLogDStream = userAdsClickLogDStream.transform(
                new Function<JavaPairRDD<String, String>, JavaRDD<String>>() {
                    @Override
                    public JavaRDD<String> call(JavaPairRDD<String, String> rdd) throws Exception {
                        JavaPairRDD<String, Tuple2<String, Optional<Boolean>>> joinedRdd = rdd.leftOuterJoin(blackListRDD);

                        //连接之后执行filter算子
                        JavaPairRDD<String, Tuple2<String, Optional<Boolean>>> filterRDD = joinedRdd.filter(
                                new Function<Tuple2<String, Tuple2<String, Optional<Boolean>>>, Boolean>() {
                                    @Override
                                    public Boolean call(Tuple2<String, Tuple2<String, Optional<Boolean>>> info) throws Exception {
                                        if (info._2()._2().isPresent() && info._2()._2().get()) {
                                            return false;
                                        }
                                        return true;
                                    }
                                }
                        );

                        JavaRDD<String> validAdsClickLogRDD = filterRDD.map(
                                new Function<Tuple2<String, Tuple2<String, Optional<Boolean>>>, String>() {
                                    @Override
                                    public String call(Tuple2<String, Tuple2<String, Optional<Boolean>>> v1) throws Exception {
                                        return v1._2()._1();
                                    }
                                }
                        );

                        return validAdsClickLogRDD;
                    }
                }
        );

        /**
         * 打印有效的广告点击日志
         * 其实在真实企业场景中这里后面就可以走写入KaFka、ActiveMQ等中间消息队列
         * 然后再开发一个专门的后台服务，作为广告计费服务，执行实时的广告计费，这里就是只拿到了有效的广告点击
         */
        validAdsClickLogDStream.print();

        jssc.start();
        jssc.awaitTermination();
        jssc.close();
    }
}
