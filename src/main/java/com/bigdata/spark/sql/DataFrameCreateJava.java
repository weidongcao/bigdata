package com.bigdata.spark.sql;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.SQLContext;

/**
 * Created by Administrator on 2016/10/18.
 */
public class DataFrameCreateJava {
    public static void main(String[] args) {
        SparkConf conf = new SparkConf()
                .setAppName("DataFrameCreate");

        JavaSparkContext sc = new JavaSparkContext(conf);

        SQLContext sqlContext = new SQLContext(sc);

        Dataset df = sqlContext.read().json("hdfs://spark.don.com:8020/user/dong/data/students.json");

        df.show();
    }
}
