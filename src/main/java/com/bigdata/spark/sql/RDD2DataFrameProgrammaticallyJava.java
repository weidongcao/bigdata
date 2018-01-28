package com.bigdata.spark.sql;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.RowFactory;
import org.apache.spark.sql.SQLContext;
import org.apache.spark.sql.types.DataTypes;
import org.apache.spark.sql.types.StructField;
import org.apache.spark.sql.types.StructType;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/10/23.
 */
public class RDD2DataFrameProgrammaticallyJava {
public static void main(String[] args) {

    String input = "E:\\Workspaces\\resource\\TestData\\student_id_name_age.txt";

    //创建SparkConf
    SparkConf conf = new SparkConf()
            .setAppName("RDD2DataFrameProgrammaticallyJava")
            .setMaster("local");

    JavaSparkContext sc = new JavaSparkContext(conf);

    SQLContext sqlContext = new SQLContext(sc);

    //第一步，创建一个普通的RDD,但是必须将其转换为RDD<Row>的这种格式
    JavaRDD<String> original = sc.textFile(input);

    /*
     */
    JavaRDD<Row> stuRDD = original.map(
            (Function<String, Row>) v1 -> {
                String[] fields = v1.split("\t");
                return RowFactory.create(
                        Integer.valueOf(fields[0]),
                        fields[1],
                        Integer.valueOf(fields[2])
                );
            }
    );


    /*
     * 第二步：动态构造元数据
     */
    List<StructField> structFields = new ArrayList<>();

    structFields.add(DataTypes.createStructField("id", DataTypes.IntegerType, true));
    structFields.add(DataTypes.createStructField("name", DataTypes.StringType, true));
    structFields.add(DataTypes.createStructField("age", DataTypes.IntegerType, true));

    StructType structType = DataTypes.createStructType(structFields);

    /*
     * 第三步，使用动态构造的元数据将RDD转换为DataFrame
     */
    Dataset stuDF = sqlContext.createDataFrame(stuRDD, structType);

    stuDF.registerTempTable("stu");

    Dataset teenagerDF = sqlContext.sql("select * from stu where age <=14");

    List<Row> rows = teenagerDF.javaRDD().collect();

    for (Row row : rows) {
        System.out.println(row);
    }



}
}
