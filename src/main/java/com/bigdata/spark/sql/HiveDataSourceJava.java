package bigdata.spark.sql;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.hive.HiveContext;

/**
 * Created by Administrator on 2016/10/30.
 */
public class HiveDataSourceJava {
    public static void main(String[] args) {
        SparkConf conf = new SparkConf()
                .setAppName("HiveDataSourceJava");

        JavaSparkContext sc = new JavaSparkContext(conf);

        HiveContext hiveContext = new HiveContext(sc.sc());



    }
}
