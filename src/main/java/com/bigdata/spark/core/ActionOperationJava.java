package com.bigdata.spark.core;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.api.java.function.Function2;
import scala.Tuple2;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/9/19.
 */
public class ActionOperationJava {
    public static void main(String[] args) {

    }

    private static void countByKey() {
        //创建SparkConf和SparkContext
        SparkConf conf = new SparkConf()
                .setAppName("countByKey")
                .setMaster("local");
        JavaSparkContext sc = new JavaSparkContext(conf);

        //模拟集合
        List<Tuple2<String, String>> original = Arrays.asList(
                new Tuple2<>("class1", "dong"),
                new Tuple2<>("class2", "carmon"),
                new Tuple2<>("class1", "poem"),
                new Tuple2<>("class2", "ling"),
                new Tuple2<>("class1", "kang")
        );

        //并行化集合，停建JavaSparkContext
        JavaPairRDD<String, String> stu = sc.parallelizePairs(original);

        /*
         * 对Rdd应用CountByKey操作，统计每个班级的学生人数，也就是统计每个Key对应的元素个数
         * 这就是CountByKey的作用
         * CountByKey返回的类型直接就是Map<String,Object>
         */
        Map<String, Long> stuCount = stu.countByKey();

        for (Map.Entry<String, Long> stc : stuCount.entrySet()) {
            System.out.println(stc.getKey() + " => " + stc.getValue());
        }

        // 关闭JavaSparkContext
        sc.close();
    }

    private static void saveAsTextfile() {
        //创建SparkConf和SparkContext
        SparkConf conf = new SparkConf()
                .setAppName("take")
                .setMaster("local");
        JavaSparkContext sc = new JavaSparkContext(conf);

        //创建测试数据，一个集合，里面有1到10，10个数字，现在要对102上数字进行累加
        List<Integer> original = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        //使用Map操作将集合中所有数字乘以2
        JavaRDD<Integer> rdd = sc.parallelize(original);

        JavaRDD<Integer> ddd = rdd.map(
                (Function<Integer, Integer>) v1 -> v1 * 2
        );

        /*
         * 直接将Rdd中的数据保存在HDFS文件中
         * 但是要注意，我们这里只能指定文件夹，也就是目录
         */
        ddd.saveAsTextFile("hdfs://spark01.cao.com:8020/user/dong/spark/core/double_number.txt");

        // 关闭JavaSparkContext
        sc.close();
    }

    private static void take() {
        //创建SparkConf和SparkContext
        SparkConf conf = new SparkConf()
                .setAppName("take")
                .setMaster("local");
        JavaSparkContext sc = new JavaSparkContext(conf);

        //创建测试数据，一个集合，里面有1到10，10个数字，现在要对102上数字进行累加
        List<Integer> original = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        JavaRDD<Integer> rdd = sc.parallelize(original);

        /*
         * 对Rdd使用count操作，统计它有多少个元素
         * take操作，与collect类似，也是从远程集群上获取rdd的数据
         * 但是Collect是获取Rdd的所有数据，take只是获取前n个数据
         */
        List<Integer> takelist = rdd.take(3);

        for (Integer num : takelist) {
            System.out.println("num = " + num);
        }

        //关闭JavaSparkContext
        sc.close();
    }

    private static void count() {
        //创建SparkConf和SparkContext
        SparkConf conf = new SparkConf()
                .setAppName("count")
                .setMaster("local");
        JavaSparkContext sc = new JavaSparkContext(conf);

        //创建测试数据，一个集合，里面有1到10，10个数字，现在要对102上数字进行累加
        List<Integer> original = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        JavaRDD<Integer> rdd = sc.parallelize(original);

        //// 对rdd使用count操作，统计它有多少个元素
        long sum = rdd.count();

        System.out.println("sum = [" + sum + "]");

        //关闭JavaSparkContext
        sc.close();
    }

    private static void collect() {
        //创建SparkConf和JavaSparkContext
        SparkConf conf = new SparkConf()
                .setAppName("collect")
                .setMaster("local");
        JavaSparkContext sc = new JavaSparkContext(conf);

        //创建测试数据，一个集合，里面有1到10，10个数字，现在要对102上数字进行累加
        List<Integer> numberList = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        JavaRDD<Integer> rdd = sc.parallelize(numberList);

        /*
         * 使用Map操作将集合中所有数字乘以2
         */
        JavaRDD<Integer> doubleRdd = rdd.map(
                (Function<Integer, Integer>) v1 -> v1 * 2
        );

        /*
         * 不用foreach action操作，在远程集群上遍历rdd中的元素
         * 使用Collect操作将分布在远程集群上的doubleRdd的数据摘取到本地
         * 这种方式一般不建议使用，因为如果Rdd中的数据时不时比较大的话比如超过一万条
         * 那么性能会比较差，因为要从远程走大量的网络传输，将数据获取到本地。
         * 此外，除了性能差，还可能在Rdd中数据时不时特别大的情况下，发生oom异常，内在溢出
         * 因此，通常还是推荐使用foreach Action操作，来对最终的Rdd元素进行处理
         */
        List<Integer> doublist = doubleRdd.collect();

        for (Integer num : doublist) {
            System.out.println("num = [" + num + "]");
        }

        //关闭JavaSparkContext
        sc.close();
    }

    private static void reduce() {
        //创建SparkConf和JavaSparkContext
        SparkConf conf = new SparkConf()
                .setAppName("reduce")
                .setMaster("local");

        JavaSparkContext sc = new JavaSparkContext(conf);

        //创建测试数据，一个集合，里面有1到10，10个数字，现在要对102上数字进行累加
        List<Integer> numberList = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        JavaRDD<Integer> rdd = sc.parallelize(numberList);

        /*
         * 使用Reduce操作，对集合中的数字进行累加
         * Reduce操作的原理：
         *      首先将第一个和第二个元素传入call方法，进行计算，会获取一个结果，比如说1 + 2 = 3
         *      接着将该结果与下一个元素传入call（）方法，进行计算，比如 3 + 3 = 6
         *      以此类推
         * 所以Reduce操作的本质就是聚合，将多个元素聚合成一个元素
         */
        int sum = rdd.reduce(
                (Function2<Integer, Integer, Integer>) (v1, v2) -> v1 + v2
        );

        System.out.println("sum = [" + sum + "]");

        //关闭JavaSparkContext
        sc.close();
    }


}
