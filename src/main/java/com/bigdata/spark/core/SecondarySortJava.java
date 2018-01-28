package com.bigdata.spark.core;

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

        JavaRDD<String> original = sc.textFile("E://Workspaces//resource//TestData//wc_kv_comma.input");

        JavaPairRDD<SecondarySortKey, String> pairs = original.mapToPair(
                (PairFunction<String, SecondarySortKey, String>) s -> {
                    String[] str = s.split(",");
                    SecondarySortKey key = new SecondarySortKey(str[0], Integer.valueOf(str[1]));
                    return new Tuple2<>(key, s);
                }
        );

        JavaPairRDD<SecondarySortKey, String> sortedPairs = pairs.sortByKey(false);

        JavaRDD<String> sortedLines = sortedPairs.map(
                (Function<Tuple2<SecondarySortKey, String>, String>) Tuple2::_2
        );

        sortedLines.foreach(
                (VoidFunction<String>) System.out::println
        );
        sc.close();
    }
}
