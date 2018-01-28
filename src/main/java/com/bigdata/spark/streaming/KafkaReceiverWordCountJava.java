package com.bigdata.spark.streaming;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.function.FlatMapFunction;
import org.apache.spark.api.java.function.Function2;
import org.apache.spark.api.java.function.PairFunction;
import org.apache.spark.streaming.Durations;
import org.apache.spark.streaming.api.java.JavaDStream;
import org.apache.spark.streaming.api.java.JavaPairDStream;
import org.apache.spark.streaming.api.java.JavaPairReceiverInputDStream;
import org.apache.spark.streaming.api.java.JavaStreamingContext;
import org.apache.spark.streaming.kafka.KafkaUtils;
import scala.Tuple2;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by Administrator on 2016/10/25.
 */
public class KafkaReceiverWordCountJava {
public static void main(String[] args) throws InterruptedException {
    SparkConf conf = new SparkConf()
            .setMaster("local[2]")
            .setAppName("KafkaReceiverWordCountJava");

    JavaStreamingContext jssc = new JavaStreamingContext(conf, Durations.seconds(5));

    Map<String, Integer> topicThreadMap = new HashMap<String, Integer>();
    //此处的key必须与KaFka的Topic的名字一致
    topicThreadMap.put("test", 1);

    //使用KafkaUtils.createStream()方法，创建针对Kafka的输入数据流
    JavaPairReceiverInputDStream<String, String> original = KafkaUtils.createStream(
            jssc,
            "spark.don.com:2181",
            "DefaultConsumerGroup",
            topicThreadMap
    );

    JavaDStream<String> words = original.flatMap(
            new FlatMapFunction<Tuple2<String, String>, String>() {
                @Override
                public Iterator<String> call(Tuple2<String, String> stringStringTuple2) throws Exception {
                    return Arrays.asList(stringStringTuple2._2().split(" ")).iterator();
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

    JavaPairDStream<String, Integer> wordcounts = pairs.reduceByKey(
            new Function2<Integer, Integer, Integer>() {
                @Override
                public Integer call(Integer v1, Integer v2) throws Exception {
                    return v1 + v2;
                }
            }
    );

    wordcounts.print();

    jssc.start();
    jssc.awaitTermination();
    jssc.close();
}
}
