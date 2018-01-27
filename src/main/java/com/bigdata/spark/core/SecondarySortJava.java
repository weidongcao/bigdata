package bigdata.spark.core;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.api.java.function.PairFunction;
import org.apache.spark.api.java.function.VoidFunction;
import scala.Tuple2;

/**
 * 二次排序主代码
 * Created by Administrator on 2016/10/8.
 */
public class SecondarySortJava {
    public static void main(String[] args) {
        SparkConf conf = new SparkConf()
                .setAppName("secondary sort")
                .setMaster("local");
        JavaSparkContext sc = new JavaSparkContext(conf);

        JavaRDD<String> oraginal = sc.textFile("E://Workspaces//resource//TestData//wc_kv_comma.input");

        JavaPairRDD<SecondarySortKey, String> pairs = oraginal.mapToPair(
                new PairFunction<String, SecondarySortKey, String>() {
                    @Override
                    public Tuple2<SecondarySortKey, String> call(String s) throws Exception {
                        String[] str = s.split(",");
                        SecondarySortKey key = new SecondarySortKey(str[0], Integer.valueOf(str[1]));
                        return new Tuple2<SecondarySortKey, String>(key, s);
                    }
                }
        );

        JavaPairRDD<SecondarySortKey, String> sortedPairs = pairs.sortByKey(false);

        JavaRDD<String> sortedLines = sortedPairs.map(
                new Function<Tuple2<SecondarySortKey, String>, String>() {
                    @Override
                    public String call(Tuple2<SecondarySortKey, String> v1) throws Exception {
                        return v1._2();
                    }
                }
        );

        sortedLines.foreach(
                new VoidFunction<String>() {
                    @Override
                    public void call(String s) throws Exception {
                        System.out.println(s);
                    }
                }
        );
        sc.close();
    }
}
