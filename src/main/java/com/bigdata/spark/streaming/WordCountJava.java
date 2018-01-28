package com.bigdata.spark.streaming;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.function.FlatMapFunction;
import org.apache.spark.api.java.function.Function2;
import org.apache.spark.api.java.function.PairFunction;
import org.apache.spark.streaming.Durations;
import org.apache.spark.streaming.api.java.JavaDStream;
import org.apache.spark.streaming.api.java.JavaPairDStream;
import org.apache.spark.streaming.api.java.JavaReceiverInputDStream;
import org.apache.spark.streaming.api.java.JavaStreamingContext;
import scala.Tuple2;

import java.util.Arrays;

/**
 * Created by Administrator on 2016/10/24.
 */
public class WordCountJava {
    public static void main(String[] args) throws Exception{
        //创建SparkConf对象
        //但是这里有一点不同，我们是要给它设置一个Master属性，但是我们测试的时候
        //使用Local模式，Local后面必须跟一个方括号，里面填一个数字，数字代表了用几个线程来执行
        SparkConf conf = new SparkConf()
                .setMaster("local[2]")
                .setAppName("WordCount");

        /*
         *创建JavaStreamingContext对象
         * 该对象就类似于SparkCore中的JavaSparkContext，就类似于SparkSQL中的SQLContext
         * 该对象除了接收SparkConf对象之外还必须接收一个Batch Interval参数
         * 就是说每收集多长时间的数据划分为一个Batch进行处理
         * 这里设置一秒
         */
        JavaStreamingContext jssc = new JavaStreamingContext(conf, Durations.seconds(1));

        /*
         * 首先创建输入DStream，代表了一个从数据源（比如Kafka、socket）来的持续不断的实时数据流
         * 调用JavaStreamingContext的socketTextStream()方法可以创建一个数据源为Socket网络端口的数据流
         * JavaReceiverInputStream代表了一个输入的DStream
         * socketTextStream()方法接收两个基本参数，第一个是监听哪个主机上的端口，第二个是监听哪个端口
         */
        JavaReceiverInputDStream<String> lines = jssc.socketTextStream("spark.don.com", 9999);

        /*
         * 到这是为止可以理解为JavaReceiverInputStream中的每隔一秒会有一个RDD其中封了这一秒改善过来的数据
         * RDD的元素类型为String，即一行一行的文本
         * 所以这里JavaReceiverInputStream的泛型类型<String>，其实就代表了它底层的RDD的泛型类型
         */

        /*
         * 开始对接收到的数据执行计算使用SparkCore提供的算子，执行应用在DStream中即可
         * 在底层实际上会对DStream中的一个一个的RDD执行我们应用在DStream上的算子
         * 产生的新的RDD会作为新的DStream中的RDD
         */
        JavaDStream<String> words = lines.flatMap(
                (FlatMapFunction<String, String>) s -> Arrays.asList(s.split(" ")).iterator()
        );

        /*
         * 这个时候每秒的数据一行一行的文本就会被拆分为多个单词，words DStream中的RDD的元素类型
         * 即为一个一个的单词
         */
        JavaPairDStream<String, Integer> pairs = words.mapToPair(
                (PairFunction<String, String, Integer>) s -> new Tuple2<>(s, 1)
        );

        /*
         * 这里正好说明一下，其实大家可以看到，用Spark Streaming开发程序，和SparkCore很像
         * 唯一不同的是SparkCore中的JAVARDD、JavaPairRDD都变成了JavaDStream、JavaPairDStream
         */
        JavaPairDStream<String, Integer> wordCounts = pairs.reduceByKey(
                (Function2<Integer, Integer, Integer>) (v1, v2) -> v1 + v2
        );

        /*
         * 至上为止，我们就实现了实时的wordcount程序了
         * 大家总结一下思路，加深一下印象
         * 每秒钟发送到指定socket端口上的数据都会被lines DStream接收到
         * 然后LinesDStream会把每秒的数据也就是一行一行的文本，诸如hello word 封闭为一个RDD
         * 然后就会对每秒钟对应的RDD执行后续的一系列的算子操作
         * 比如，对lines RDD 执行了FlatMap之后，得到一个wordRdd，作为wrods DStream中的一个RDD
         * 以此类推，直到生成最后一个， wordCounts RDD ，作为wordCounts DStream中的一个RDD
         * 此时，一定要注意，SparkStreaming的计算模型就决定了我们必须自己来进行中间缓存的控制
         * 比如写入redis等缓存
         * 它的计算模型跟Storm是完全不同的，storm是自己编写的一个一个的程序，运行在节点上，相当于
         * 一个一个的对象，可以自己在对象中控制缓存
         * 但是Spark本身是函数式编程的计算模型，所以比如在words 或pairs DStream中，没法在实例变量中进行缓存
         * 此时就只能将最后计算出来的wordCounts中的一个一个听RDD，写入外部的缓存或者持久化DB
         *
         * 最后每次计算完，都打印一下这个一秒钟的单词计数情况
         * 并休眠3秒钟，以便于我们测试和观察
         */
        Thread.sleep(3000);
        wordCounts.print();


        /*
         * 首先对JavaStreamingContext进行一下后续处理
         * 必须调用JavaStreamingContext的start()方法，整个SparkStreaming Application都会启动执行
         * 否则是不会执行的
         */
        jssc.start();
        jssc.awaitTermination();
        jssc.close();
    }

}
