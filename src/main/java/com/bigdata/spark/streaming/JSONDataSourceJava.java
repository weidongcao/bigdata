package com.bigdata.spark.streaming;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.api.java.function.PairFunction;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.RowFactory;
import org.apache.spark.sql.SQLContext;
import org.apache.spark.sql.types.DataTypes;
import org.apache.spark.sql.types.StructField;
import org.apache.spark.sql.types.StructType;
import scala.Tuple2;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/10/27.
 */
public class JSONDataSourceJava {
    public static void main(String[] args) {
        SparkConf conf = new SparkConf()
                .setAppName("JSONDataSource")
                .setMaster("local[2]");


        JavaSparkContext sc = new JavaSparkContext(conf);

        SQLContext sqlContext = new SQLContext(sc);

        //针对JSON文件，创建DataFrame
        Dataset scoreDF = sqlContext.read().json("E:\\Workspaces\\resource\\TestData\\students-score.json");

        scoreDF.registerTempTable("score");

        Dataset goodDF = sqlContext.sql("select name from score where score >= 80");

        List goodStu = goodDF.javaRDD().map(
                (Function<Row, String>) v1 -> v1.getString(0)
        ).collect();

        //然后针对JavaRDD<String>创建DataFrame
        List<String> infoJson = new ArrayList<>();

        infoJson.add("{\"name\":\"Leo\", \"age\": 18}");
        infoJson.add("{\"name\":\"Marry\", \"age\": 17}");
        infoJson.add("{\"name\":\"Jack\", \"age\": 19}");

        JavaRDD<String> infoRDD = sc.parallelize(infoJson);

        Dataset infoDF = sqlContext.read().json(infoRDD);

        infoDF.registerTempTable("info");

        String sql = "select name, age from info where name in (";

        for (int i = 0; i < goodStu.size(); i++) {
            sql += "'" + goodStu.get(i) + "'";
            if (i < goodStu.size() - 1) {
                sql += ",";
            }
        }

        sql += ")";

        Dataset goodStuInfoDF = sqlContext.sql(sql);

        JavaPairRDD<String, Tuple2<Integer, Integer>> goodStuRDD =
                goodDF.javaRDD().mapToPair(
                        (PairFunction<Row, String, Integer>) row -> new Tuple2<String, Integer>(row.getString(0),
                                Integer.valueOf(String.valueOf(row.getLong(1))))
                ).join(goodStuInfoDF.javaRDD().mapToPair(
                        (PairFunction<Row, String, Integer>) row -> new Tuple2<String, Integer>(
                                row.getString(0),
                                Integer.valueOf(String.valueOf(row.getLong(1)))
                        )
                ));

        JavaRDD<Row> goodStudentRowsRdd = goodStuRDD.map(
                (Function<Tuple2<String, Tuple2<Integer, Integer>>, Row>) v1 -> RowFactory.create(v1._1(), v1._2()._1(), v1._2()._2())
        );

        List<StructField> structFields = new ArrayList<StructField>();
        structFields.add(DataTypes.createStructField("name", DataTypes.StringType, true));
        structFields.add(DataTypes.createStructField("score", DataTypes.IntegerType, true));
        structFields.add(DataTypes.createStructField("age", DataTypes.IntegerType, true));

        StructType structType = DataTypes.createStructType(structFields);

        Dataset df = sqlContext.createDataFrame(goodStudentRowsRdd, structType);

        df.write().format("json").save("E:\\Workspaces\\resource\\TestData\\aaa\\bb");


    }
}
