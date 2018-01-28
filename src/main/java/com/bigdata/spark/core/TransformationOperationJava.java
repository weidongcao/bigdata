package com.bigdata.spark.core;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.api.java.function.VoidFunction;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Administrator on 2016/9/16.
 */
public class TransformationOperationJava {

    public static void main(String[] args) {
//        map();
        filter();
    }

    /**
     * map算子案例：将集合中每一个元素都乘以2
     */
    private static void map(){
        //创建SparkConf
        SparkConf conf = new SparkConf()
                .setAppName("map")
                .setMaster("local");

        //创建JavaSparkContext
        JavaSparkContext sc = new JavaSparkContext(conf);

        //构造集合
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 0);

        //并行化集合，创建初始RDD
        JavaRDD<Integer> numRDD = sc.parallelize(numbers);

        /*
         * 使用map算子操作，将集合中的每个元素都乘以2
         * map算子是对任何类型的RDD都可以调用的
         * 在java中，map算子接收的参数是Function对象
         * 创建的Function对象一定会让你设置第二个泛型参数，这个泛型参数就是返回的
         * 新元素的类型，同时call()方法的返回类型也必须与第二个泛型类型同步
         * 在call（）方法内部，就可以对原始RDD的每一个元素进行各种处理和计算并返回
         * 一个新的元素，所有新的元素就会组成一个新的RDD
         */
        JavaRDD<Integer> multipleNumRDD = numRDD.map(
                (Function<Integer, Integer>) v1 -> v1 * 2
        );

        //打印新的RDD
        multipleNumRDD.foreach((VoidFunction<Integer>) integer -> System.out.println(integer));

        //关闭JavaSparkContext
        sc.close();
    }

    /**
     * Filter算子操作
     */
    public static void filter(){
        SparkConf conf = new SparkConf()
                .setAppName("filter")
                .setMaster("local");

        JavaSparkContext sc = new JavaSparkContext(conf);

        List<Integer> nums = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 0);

        JavaRDD<Integer> numRDD = sc.parallelize(nums, 1);

        JavaRDD<Integer> filterNumRDD = numRDD.filter(
                (Function<Integer, Boolean>) v1 -> v1 % 2 == 0
        );

        filterNumRDD.foreach((VoidFunction<Integer>) integer -> System.out.println("integer = [" + integer + "]"));

        sc.close();
    }
}
