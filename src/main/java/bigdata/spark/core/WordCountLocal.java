package bigdata.spark.core;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.FlatMapFunction;
import org.apache.spark.api.java.function.Function2;
import org.apache.spark.api.java.function.PairFunction;
import org.apache.spark.api.java.function.VoidFunction;
import scala.Tuple2;

import java.util.Arrays;

/**
 * Created by Administrator on 2016/9/3.
 * 使用Java开发本地测试的WordCount程序
 */
public class WordCountLocal {
    public static void main(String[] args){
        /**
        编写Spark应用程序
        本地执行
        第一步：
        创建SparkConf对象，设置Spark应用的配置信息
        使用SetMaster()可以设置Spark应用程序要连接的Spark集群的Master节点的URL
        但是如果设置为Local则代表在本地运行
         */
        SparkConf conf = new SparkConf()
                .setAppName("WordCountLocal")
                .setMaster("local");

        /**
         * 第二步：创建JavaSparkContext对象
         * 在Spark中，SparkContext是Spark所有功能的一个入口，无论是JAVA、Scala、甚至是Python编写
         * 都必须要有一个SparkContext，它的主要作用包括：初始化Spark应用程序所需要的一些核心组件，
         * 包括：调试器（DAGScheduler、TaskScheduler），还会去到Spark Master节点上进行注册等等
         *
         * SparkContext是Spark应用中可以说 是最最重要的一个对象
         * 但是呢，在Spark中编写不同类型的Spark应用程序，使用的SparkContext是不同的，如果使用Scala
         * 使用的就是原生的SparkContext对象
         * 如果使用Java，那么就是JavaSparkContext对象
         * 如果是开发SparkSQL程序，那么就是SQLContext、HiveContext
         * 如果是开发SparkStreaming程序，那么就是它独有的SparkContext
         * 以此类推
         */
        JavaSparkContext jsc = new JavaSparkContext(conf);

        /**
         * 第三步：要针对输入源（HDFS文件，本地文件等等），创建一个初始的RDD
         * 输入源中的数据会打散，分配到RDD的每个partition中，从而形成一个初始的分布式的数据集
         * 我们这里呢，因为是本地渡，所以呢，就是针对本地文件
         * SparkContext中用于根据文件类型的僌源创建RDD的方法，叫做textFile()方法
         * 在JAVA中，创建的普通RDD,都叫做JAVARDD
         * 在这里呢，RDD中有元素这种概念，如果是HDFS或者本地文件呢，创建的Rdd，每一个元素是文件里的一行
         */
        JavaRDD<String> lines = jsc.textFile("E://Workspaces//resource//TestData//wc_kv_comma.input");

        /**
         * 第四步：对初始RDD进行Transformation操作，也就是一些计算操作
         * 通常操作会通过创建Function，并配合RDD的Map、FlatMap等算子来执行
         * function，通常如果比较简单，则创建指定Function的匿名内部类
         * 但是如果Function比较复杂，则会单独创建一个类，作为实现这个Function接口的类
         *
         * 先将每一行拆分成单个的单词
         * FlatMapFunction有两个泛型参数，分别代表了输入和输出类型
         * 我们这是呢输入肯定是String，因为是一行一行的文本，输入其实也是String，因为是每一行的文本
         * 这是先简要介绍flapMap算子的作用，其实就是将RDD的一个元素拆分成一个或多个元素
         */
        JavaRDD<String> words = lines.flatMap(new FlatMapFunction<String, String>() {

            public Iterable<String> call(String line) throws Exception {
                return Arrays.asList(line.split(","));
            }
        });

        /**
         * 接着，需要将每一个单词映射为（单词，1）的这种格式
         * 因为只有这样，后面才能根据单词作为Key，来进行每个单词的出现次数进行累加
         * MapToPair，其实就是将每个元素映射为一个（v1,v2)这样的Tupple2类型的元素
         * 如果大家还记得Scala里面讲的Tuple，那么没有错这里的Tuple2就是Scala类型，
         * 包含了两个值MapToPair这个算子要求的是与PairFunction配合使用，第一个泛型
         * 参数代表了输入类型第二和第三个泛型参数代表了输出的Tuple2的第一个值和第二个值
         * 的类型JavaPairRDD的两个泛型参数，分别代表了Tuple元素的第一个值和第二个值的类型
         */
        JavaPairRDD<String, Integer > pairs = words.mapToPair(new PairFunction<String, String, Integer>() {
            public Tuple2<String, Integer> call(String word) throws Exception {
                return new Tuple2<String, Integer>(word, 1);
            }
        });

        /**
         * 接着，需要以单词作为Key，统计每个单词出现的次数
         * 这里要使用ReduceByKey这个算子，对每个Key对应的Value都进行操作
         * Reduce操作 ，相当于是把第一个值与第二个值进行计算，然后再将结果与第三个值进行计算
         * 最后返回的是JavaPairRDD中的元素，也是Tuple，但是第一个值就是每个Key，第二个值就是每个Key的Value
         * Reduce之后的结果，相当于就是每个单词出现的次数
         */

        final JavaPairRDD<String, Integer> wordCounts = pairs.reduceByKey(new Function2<Integer, Integer, Integer>() {
            public Integer call(Integer v1, Integer v2) throws Exception {
                return v1 + v2;
            }
        });

        /**
         * 到这里为止，我们通过几个Spark算子操作已经统计出了单词的个数
         * 但是之前我们使用的FlapMap、Map、ReduceByKey这种操作都是Transformation操作
         * 一个Spark应用中光是有Transformation操作是不行的，是不会执行的，必须要有一种叫做Action的操作
         * 接着，最后可以使用一种叫做Action操作，比如说foreach，来触发程序的执行
         */
        wordCounts.foreach(new VoidFunction<Tuple2<String, Integer>>() {
            public void call(Tuple2<String, Integer> wordCount) throws Exception {
                System.out.println(wordCount._1 + "\t => " + wordCount._2());
            }
        });


        jsc.close();
    }

}
