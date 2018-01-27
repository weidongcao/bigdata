package bigdata.spark.streaming;

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
    public static void main(String[] args) {
        SparkConf conf = new SparkConf()
                .setMaster("local[2]")
                .setAppName("KafkaDirectWordCountJava");

        JavaStreamingContext jssc = new JavaStreamingContext(conf, Durations.seconds(5));

        //首先要创建一份kafka参数map
        Map<String, String> kafkaParams = new HashMap<String, String>();

        kafkaParams.put(
               "metadata.broker.list", "spark.don.com:9092"
        );

        Set<String> topics = new HashSet<String>();

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
                new FlatMapFunction<Tuple2<String, String>, String>() {
                    @Override
                    public Iterable<String> call(Tuple2<String, String> stringStringTuple2) throws Exception {
                        return Arrays.asList(stringStringTuple2._2().split(" "));
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

        JavaPairDStream<String, Integer> wordcount = pairs.reduceByKey(
                new Function2<Integer, Integer, Integer>() {
                    @Override
                    public Integer call(Integer v1, Integer v2) throws Exception {
                        return v1 + v2;
                    }
                }
        );

        wordcount.print();

        jssc.start();
        jssc.awaitTermination();
        jssc.close();
    }
}
