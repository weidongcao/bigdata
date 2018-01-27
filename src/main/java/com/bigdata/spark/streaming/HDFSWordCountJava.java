package bigdata.spark.streaming;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.function.FlatMapFunction;
import org.apache.spark.api.java.function.Function2;
import org.apache.spark.api.java.function.PairFunction;
import org.apache.spark.streaming.Durations;
import org.apache.spark.streaming.api.java.JavaDStream;
import org.apache.spark.streaming.api.java.JavaPairDStream;
import org.apache.spark.streaming.api.java.JavaStreamingContext;
import scala.Tuple2;

import java.util.Arrays;

/**
 * Created by Administrator on 2016/10/25.
 */
public class HDFSWordCountJava {
    public static void main(String[] args) {
        SparkConf conf = new SparkConf()
                .setMaster("local[2]")
                .setAppName("HDFSWordCount");

        JavaStreamingContext jssc = new JavaStreamingContext(conf, Durations.seconds(5));

        /**
         * 首先使用JavaStreamingContext的textFileStream()方法，
         * 针对HDFS目录创建输入数据流
         */
        JavaDStream<String> original = jssc.textFileStream("hdfs://spark.don.com:8020/user/dong/input/streaming/");

        /**
         * 执行wordcount操作
         */
        JavaDStream<String> words = original.flatMap(
                new FlatMapFunction<String, String>() {
                    @Override
                    public Iterable<String> call(String s) throws Exception {
                        return  Arrays.asList(s.split(" "));
                    }
                }
        );

        JavaPairDStream<String, Integer> pairs = words.mapToPair(
                new PairFunction<String, String, Integer>() {
                    @Override
                    public Tuple2<String, Integer> call(String s) throws Exception {
                        return new Tuple2<String, Integer>(s, 1);
                    }
                }
        );

        JavaPairDStream wordcount = pairs.reduceByKey(
                new Function2<Integer, Integer, Integer>() {
                    @Override
                    public Integer call(Integer v1, Integer v2) throws Exception {
                        return v1 + v2;
                    }
                }
        );

        wordcount.print();

        jssc.start();
        jssc.awaitTermination();

        jssc.close();
    }

}
