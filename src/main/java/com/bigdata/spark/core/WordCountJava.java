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
import scala.actors.threadpool.Arrays;

/**
 * Created by Administrator on 2016/10/5.
 */
public class WordCountJava {
    public static void main(String[] args) {
        //创建SparkConf和JavaSparkContext
        SparkConf conf = new SparkConf()
                .setAppName("WordCount")
                .setMaster("local");

        JavaSparkContext sc = new JavaSparkContext(conf);

        //创建Lines RDD
        JavaRDD<String> lines = sc.textFile("E://Workspaces//resource//TestData//wc.input");

        //执行我们之前做过的单词计数
        JavaRDD<String> words = lines.flatMap(
                new FlatMapFunction<String, String>() {
                    @Override
                    public Iterable<String> call(String s) throws Exception {
                        return Arrays.asList(s.split("\t"));
                    }
                }
        );

        JavaPairRDD<String, Integer> pairs = words.mapToPair(
                new PairFunction<String, String, Integer>() {
                    @Override
                    public Tuple2<String, Integer> call(String s) throws Exception {
                        return new Tuple2<String, Integer>(s, 1);
                    }
                }
        );

        JavaPairRDD<String, Integer> wordcount = pairs.reduceByKey(
                new Function2<Integer, Integer, Integer>() {
                    @Override
                    public Integer call(Integer v1, Integer v2) throws Exception {
                        return v1 + v2;
                    }
                }
        );

        /**
         *到这里为止，就得到了每个单词出现的次数
         *但是问题是我们的新需求，是要按照每个单词出现的次数的顺序，降序排列
         * WordCount RDD内的元素是什么？应该是这种格式的吧：（hello, 3) （you, 2)
         * 我们需要将RDD转换成(3, hello), (2, you) 的这种格式，才能根据单词出现次数进行排序
         * 进行Key-Value的反转映射
         */
        JavaPairRDD<Integer, String> countWords = wordcount.mapToPair(
                new PairFunction<Tuple2<String, Integer>, Integer, String>() {
                    @Override
                    public Tuple2<Integer, String> call(Tuple2<String, Integer> stringIntegerTuple2) throws Exception {
                        return new Tuple2<Integer, String>(stringIntegerTuple2._2, stringIntegerTuple2._1);
                    }
                }
        );

        //按照key进行排序
        JavaPairRDD<Integer, String> sortedCountwords = countWords.sortByKey(false);

        //再次将Value-key进行反转映射
        JavaPairRDD<String, Integer> sortedWordCount = sortedCountwords.mapToPair(
                new PairFunction<Tuple2<Integer, String>, String, Integer>() {
                    @Override
                    public Tuple2<String, Integer> call(Tuple2<Integer, String> integerStringTuple2) throws Exception {
                        return new Tuple2<String, Integer>(integerStringTuple2._2, integerStringTuple2._1);
                    }
                }
        );

        /**
         * 按照单词出现次数排序后的意识计数
         */
        sortedWordCount.foreach(
                new VoidFunction<Tuple2<String, Integer>>() {
                    @Override
                    public void call(Tuple2<String, Integer> stringIntegerTuple2) throws Exception {
                        System.out.println(stringIntegerTuple2._1() + " => " + stringIntegerTuple2._2());
                    }
                }
        );




        //关闭JavaSparkContext
        sc.close();
    }
}
