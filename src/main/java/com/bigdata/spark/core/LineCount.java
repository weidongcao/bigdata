package com.bigdata.spark.core;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function2;
import org.apache.spark.api.java.function.PairFunction;
import org.apache.spark.api.java.function.VoidFunction;
import scala.Tuple2;

/**
 * 北风网课程培训  中华石杉 Spark从入门到精通
 * create @2016-09-15
 */
public class LineCount {
    public static void main(String[] args){
        //创建SparkConf
        SparkConf conf = new SparkConf()
                .setAppName("LineCount")
                .setMaster("local");

        //创建JavaSparkContext
        JavaSparkContext sc = new JavaSparkContext(conf);

        //创建初始RDD，Lines，每个元素是一行文本
        JavaRDD<String> lines = sc.textFile("E:\\Workspaces\\resource\\TestData\\wc.input");

        //对lines RDD执行MapToPair牌子，将每一行映射为（line,1)的这种key-value对的格式
        //然后后面才能统计每一行次数
        JavaPairRDD<String, Integer> pairs = lines.mapToPair(
                (PairFunction<String, String, Integer>) s -> new Tuple2<>(s, 1)
        );

        //对Pairs RDD执行ReduceByKey算子，统计出每一行出现的叫次数
        JavaPairRDD<String, Integer> lineCounts = pairs.reduceByKey(
                (Function2<Integer, Integer, Integer>) (v1, v2) -> v1 + v2
        );

        //执行一个Action操作，Foreach，打印出第一行出现的次数
        lineCounts.foreach((VoidFunction<Tuple2<String, Integer>>) t -> System.out.println(t._1 + "     appears  " + t._2 + " times"));

        //关闭JavaSparkContext
        sc.close();
    }
}
