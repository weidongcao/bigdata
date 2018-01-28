package com.bigdata.spark.core;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.api.java.function.VoidFunction;
import org.apache.spark.broadcast.Broadcast;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Administrator on 2016/9/20.
 */
public class BroadcastJava {
    public static void main(String[] args) {
        SparkConf conf = new SparkConf()
                .setAppName("Broadcast")
                .setMaster("local");
        JavaSparkContext sc = new JavaSparkContext(conf);

        /*
         * 在Java中创建共享变量就是调用SparkContext的Broadcast方法
         * 返回的结果是Broadcast<T>类型
         */
        final int factor = 3;

        final Broadcast<Integer> fb = sc.broadcast(factor);

        List<Integer> original = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        JavaRDD<Integer> rdd = sc.parallelize(original, 1);

        /*
         * 让集合中的每个数字都自尊心外部定义的那个Factor
         */
        JavaRDD<Integer> ddd = rdd.map(
                (Function<Integer, Integer>) v1 -> {
                    int factor1 = fb.getValue();
                    return v1 * factor1;
                }
        );

        ddd.foreach(
                (VoidFunction<Integer>) integer -> System.out.println("integer = " + integer)
        );

        sc.close();
    }
}
