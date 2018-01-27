package bigdata.spark.core;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.api.java.function.PairFunction;
import scala.Tuple2;

import java.util.List;

/**
 * Created by Administrator on 2016/10/8.
 */
public class TopNJava {
    public static void main(String[] args) {
        SparkConf conf = new SparkConf()
                .setMaster("local")
                .setAppName("topn java");

        JavaSparkContext sc = new JavaSparkContext(conf);

        JavaRDD<String> original = sc.textFile("E://Workspaces//resource//TestData//number.txt");

        JavaPairRDD<Integer, String> pairs = original.mapToPair(
                new PairFunction<String, Integer, String>() {
                    @Override
                    public Tuple2<Integer, String> call(String s) throws Exception {
                        return new Tuple2<Integer, String>(Integer.valueOf(s), s);
                    }
                }
        );

        JavaPairRDD<Integer, String> sortedpairs = pairs.sortByKey(false);

        JavaRDD<Integer> sortedNums = sortedpairs.map(
                new Function<Tuple2<Integer, String>, Integer>() {
                    @Override
                    public Integer call(Tuple2<Integer, String> v1) throws Exception {
                        return v1._1();
                    }
                }
        );

        List<Integer> sortedNumList = sortedNums.take(3);

        for (Integer num : sortedNumList) {
            System.out.println(num);
        }

    }
}
