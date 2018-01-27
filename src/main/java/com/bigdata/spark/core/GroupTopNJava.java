package bigdata.spark.core;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.PairFunction;
import org.apache.spark.api.java.function.VoidFunction;
import scala.Tuple2;

import java.util.Arrays;
import java.util.Iterator;

/**
 * Created by Administrator on 2016/10/9.
 */
public class GroupTopNJava {
    public static void main(String[] args) {
        SparkConf conf = new SparkConf()
                .setAppName("group top n java")
                .setMaster("local");

        JavaSparkContext sc = new JavaSparkContext(conf);

        JavaRDD<String> original = sc.textFile("E://Workspaces//resource//TestData//word_kv_comma.txt");

        JavaPairRDD<String, Integer> pairs = original.mapToPair(
                new PairFunction<String, String, Integer>() {
                    @Override
                    public Tuple2<String, Integer> call(String s) throws Exception {
                        String[] str = s.split(",");
                        return new Tuple2<String, Integer>(str[0], Integer.valueOf(str[1]));
                    }
                }
        );

        JavaPairRDD<String, Iterable<Integer>> groupPairs  = pairs.groupByKey();

        JavaPairRDD<String, Iterable<Integer>> topPairs = groupPairs.mapToPair(
                new PairFunction<Tuple2<String, Iterable<Integer>>, String, Iterable<Integer>>() {
                    @Override
                    public Tuple2<String, Iterable<Integer>> call(Tuple2<String, Iterable<Integer>> stringIterableTuple2) throws Exception {
                        Integer[] top3 = new Integer[3];
                        String name = stringIterableTuple2._1();
                        Iterator<Integer> iter = stringIterableTuple2._2().iterator();
                        while (iter.hasNext()) {
                            Integer num = iter.next();
                            for (int i = 0; i < 3; i++) {
                                if (null == top3[i]) {
                                    top3[i] = num;
                                    break;
                                } else if (num > top3[i]) {
                                    for(int j = 2; j > i; j--) {
                                        top3[j] = top3[j - 1];
                                    }
                                    top3[i] = num;
                                    break;
                                }
                            }
                        }
                        return new Tuple2<String, Iterable<Integer>>(name, Arrays.asList(top3));
                    }
                }
        );

        topPairs.foreach(
                new VoidFunction<Tuple2<String, Iterable<Integer>>>() {
                    @Override
                    public void call(Tuple2<String, Iterable<Integer>> stringIterableTuple2) throws Exception {
                        System.out.println("name = " + stringIterableTuple2._1());
                        Iterator<Integer> list = stringIterableTuple2._2().iterator();
                        while (list.hasNext()) {
                            System.out.println(list.next());
                        }
                    }
                }
        );
        sc.close();


    }
}
