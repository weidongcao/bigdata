package bigdata.spark.sql;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.DataFrame;
import org.apache.spark.sql.SQLContext;

/**
 * Created by Administrator on 2016/10/23.
 */
public class DataFrameOperationJava {
    public static void main(String[] args) {
        String input = "hdfs://spark.don.com:8020/user/dong/data/students.json";

        SparkConf conf = new SparkConf()
                .setAppName("DataFrameOperation");

        JavaSparkContext sc = new JavaSparkContext(conf);

        //创建出来的DataFrame完全可以理解为一张表
        SQLContext sqlContext = new SQLContext(sc);

        //打印DataFrame中所有的数据(select * from ...)
        DataFrame df = sqlContext.read().json(input);

        //打印DataFrame的元数据（Schema）
        df.printSchema();

        //查询某列所有的数据
        df.select("name").show();

        //查某所有的数据，并对列进行计算
        df.select(df.col("name"),df.col("age").plus(1)).show();

        //根据某一列的值进行过滤
        df.filter(df.col("age").gt(18)).show();

        //根据某一列进行分组，然后进行聚合
        df.groupBy(df.col("age")).count().show();
    }
}
