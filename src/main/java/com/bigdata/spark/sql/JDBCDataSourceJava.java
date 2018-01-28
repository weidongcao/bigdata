package com.bigdata.spark.sql;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.api.java.function.PairFunction;
import org.apache.spark.api.java.function.VoidFunction;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.RowFactory;
import org.apache.spark.sql.SQLContext;
import org.apache.spark.sql.types.DataTypes;
import org.apache.spark.sql.types.StructField;
import org.apache.spark.sql.types.StructType;
import scala.Tuple2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/11/1.
 */
public class JDBCDataSourceJava {
    public static void main(String[] args) {
        SparkConf conf = new SparkConf()
                .setAppName("JDBCDataSourceJava")
                .setMaster("local");

        JavaSparkContext sc = new JavaSparkContext(conf);

        SQLContext sqlContext = new SQLContext(sc.sc());

        /*
         * JDBC数据源首先是通过SQLContext的read系列方法将mysql中的数据加载到DataFrame
         * 然后可能将DataFrame转换为RDD，使用Spark Core提供的各种算子进行操作
         * 最后可以将得到的数据结果通过foreach算子，写入到MySQL、HBase、Redi等等
         */
        Map<String, String> options = new HashMap<>();
        options.put("url", "jdbc:mysql://spark.don.com:3306/spark");
        options.put("dbtable", "stu_info");
        Dataset<Row> stu_infoDF = sqlContext.read().format("jdbc").options(options).load();

        options.put("dbtable", "stu_score");
        Dataset<Row> stu_scoreDF = sqlContext.read().format("jdbc").options(options).load();

        //将两个DataFrame转换为JavaPairRDD，执行join操作
        JavaPairRDD stuRDD = stu_infoDF.javaRDD().mapToPair(
                (PairFunction<Row, String, Integer>) row -> new Tuple2<>(
                        row.getString(0),
                        row.getInt(1)
                )
        ).join(stu_scoreDF.javaRDD().mapToPair(
                (PairFunction<Row, String, Integer>) row -> new Tuple2<>(
                        row.getString(0),
                        row.getInt(1)
                )
        ));

        //将JavaPairRDD转换为JAVARDD<Row>
        JavaRDD stu_rowRDD = stuRDD.map(
                (Function<Tuple2<String, Tuple2<Integer, Integer>>, Row>) v1 -> RowFactory.create(v1._1(), v1._2()._1(), v1._2()._2())
        );

        //过滤出分数大于80分的数据
        JavaRDD filterStu_rowRdd = stu_rowRDD.filter(
                (Function<Row, Boolean>) v1 -> v1.getInt(2) > 80
        );

        //转换为DataFrame
        List<StructField> structFields = new ArrayList<>();
        structFields.add(DataTypes.createStructField("name", DataTypes.StringType, true));
        structFields.add(DataTypes.createStructField("age", DataTypes.IntegerType, true));
        structFields.add(DataTypes.createStructField("score", DataTypes.IntegerType,true));

        StructType structType = DataTypes.createStructType(structFields);

        Dataset<Row> stuDF = sqlContext.createDataFrame(filterStu_rowRdd, structType);

        Row[] rows = stuDF.collect();
        for (Row row : rows) {
            System.out.println(row);
        }

        //将DataFrame中的数据保存到MySQL表中
        stuDF.javaRDD().foreach(
                (VoidFunction<Row>) row -> {
                    String sql = "insert into good_stu_info values("
                            + "'" + row.getString(0) + "', "
                            + row.getInt(1) + ", "
                            + row.getInt(2) + ")";

                    Class.forName("com.mysql.jdbc.Driver");

                    Connection conn = null;

                    Statement stmt = null;

                    try {
                        conn = DriverManager.getConnection("jdbc:mysql://spark.don.com:3306/spark", "root", "123123");
                        stmt = conn.createStatement();
                        stmt.execute(sql);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }finally {
                        if (stmt != null) {
                            stmt.close();
                        }
                        if (conn != null) {
                            conn.close();
                        }
                    }

                }
        );

        sc.close();
    }
}
