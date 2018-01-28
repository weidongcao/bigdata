package com.bigdata.spark.streaming;

import kafka.serializer.StringDecoder;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.function.FlatMapFunction;
import org.apache.spark.api.java.function.Function2;
import org.apache.spark.api.java.function.PairFunction;
import org.apache.spark.streaming.Durations;
import org.apache.spark.streaming.api.java.JavaDStream;
import org.apache.spark.streaming.api.java.JavaPairDStream;
import org.apache.spark.streaming.api.java.JavaStreamingContext;
import org.apache.spark.streaming.kafka.KafkaUtils;
import scala.Tuple2;

import java.util.*;

/**
 * Created by Administrator on 2016/10/25.
 */
public class KafkaDirectWordCountJava {
    public static void main(String[] args) throws InterruptedException {
        SparkConf conf = new SparkConf()
                .setMaster("local[2]")
                .setAppName("KafkaDirectWordCountJava");

        JavaStreamingContext jssc = new JavaStreamingContext(conf, Durations.seconds(5));

        //首先要创建一份kafka参数map
        Map<String, String> kafkaParams = new HashMap<>();

        kafkaParams.put(
               "metadata.broker.list", "spark.don.com:9092"
        );

        Set<String> topics = new HashSet<>();

        topics.add("test");

        JavaPairDStream<String, String> original = KafkaUtils.createDirectStream(
                jssc,
                String.class,
                String.class,
                StringDecoder.class,
                StringDecoder.class,
                kafkaParams,
                topics
        );

        JavaDStream<String> words = original.flatMap(
                (FlatMapFunction<Tuple2<String, String>, String>) stringStringTuple2 -> Arrays.asList(stringStringTuple2._2().split(" ")).iterator()
        );

        JavaPairDStream<String, Integer> pairs = words.mapToPair(
                (PairFunction<String, String, Integer>) s -> new Tuple2<>(s, 1)
        );

        JavaPairDStream<String, Integer> wordcount = pairs.reduceByKey(
                (Function2<Integer, Integer, Integer>) (v1, v2) -> v1 + v2
        );

        wordcount.print();

        jssc.start();
        jssc.awaitTermination();
        jssc.close();
    }
}
