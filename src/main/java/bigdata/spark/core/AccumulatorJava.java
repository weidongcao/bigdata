package bigdata.spark.core;

import org.apache.spark.Accumulator;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.VoidFunction;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Administrator on 2016/9/20.
 */
public class AccumulatorJava {
    public static void main(String[] args) {
        SparkConf conf = new SparkConf()
                .setAppName("Accumulator")
                .setMaster("local");
        JavaSparkContext sc = new JavaSparkContext(conf);

        final Accumulator<Integer> sum = sc.accumulator(0);

        List<Integer> original = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 0);

        JavaRDD<Integer> rdd = sc.parallelize(original, 1);

        rdd.foreach(
                new VoidFunction<Integer>() {
                    @Override
                    public void call(Integer integer) throws Exception {
                        sum.add(integer);
                    }
                }
        );

        System.out.println("sum = " + sum);

        sc.close();
    }
}
