package com.bigdata.spark.core;

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
                (PairFunction<String, Integer, String>) s -> new Tuple2<>(Integer.valueOf(s), s)
        );

        JavaPairRDD<Integer, String> sortedpairs = pairs.sortByKey(false);

        JavaRDD<Integer> sortedNums = sortedpairs.map(
                (Function<Tuple2<Integer, String>, Integer>) Tuple2::_1
        );

        List<Integer> sortedNumList = sortedNums.take(3);

        for (Integer num : sortedNumList) {
            System.out.println(num);
        }

    }
}
