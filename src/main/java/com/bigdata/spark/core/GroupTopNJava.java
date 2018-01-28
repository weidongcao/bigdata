package com.bigdata.spark.core;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.PairFunction;
import org.apache.spark.api.java.function.VoidFunction;
import scala.Tuple2;

import java.util.Arrays;

/**
 * Created by Administrator on 2016/10/9.
 */
public class GroupTopNJava {
    public static void main(String[] args) {
        SparkConf conf = new SparkConf()
                .setAppName("group top n java")
                .setMaster("local");

        JavaSparkContext sc = new JavaSparkContext(conf);

        JavaRDD<String> original = sc.textFile("E://Workspaces//resource//TestData//word_kv_comma.txt");

        JavaPairRDD<String, Integer> pairs = original.mapToPair(
                (PairFunction<String, String, Integer>) s -> {
                    String[] str = s.split(",");
                    return new Tuple2<>(str[0], Integer.valueOf(str[1]));
                }
        );

        JavaPairRDD<String, Iterable<Integer>> groupPairs  = pairs.groupByKey();

        JavaPairRDD<String, Iterable<Integer>> topPairs = groupPairs.mapToPair(
                (PairFunction<Tuple2<String, Iterable<Integer>>, String, Iterable<Integer>>) stringIterableTuple2 -> {
                    Integer[] top3 = new Integer[3];
                    String name = stringIterableTuple2._1();
                    for (Integer num : stringIterableTuple2._2()) {
                        for (int i = 0; i < 3; i++) {
                            if (null == top3[i]) {
                                top3[i] = num;
                                break;
                            } else if (num > top3[i]) {
                                System.arraycopy(top3, i, top3, i + 1, 2 - i);
                                top3[i] = num;
                                break;
                            }
                        }
                    }
                    return new Tuple2<>(name, Arrays.asList(top3));
                }
        );

        topPairs.foreach(
                (VoidFunction<Tuple2<String, Iterable<Integer>>>) stringIterableTuple2 -> {
                    System.out.println("name = " + stringIterableTuple2._1());
                    for (Integer integer : stringIterableTuple2._2()) {
                        System.out.println(integer);
                    }
                }
        );
        sc.close();


    }
}
