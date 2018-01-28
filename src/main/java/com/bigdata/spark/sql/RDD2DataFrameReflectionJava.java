package com.bigdata.spark.sql;

import com.domain.Student;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SQLContext;

import java.util.List;

/**
 * Created by Administrator on 2016/10/23.
 */
public class RDD2DataFrameReflectionJava {
    public static void main(String[] args) {
        String input = "E:\\Workspaces\\resource\\TestData\\student_id_name_age.txt";

        SparkConf conf = new SparkConf()
                .setAppName("RDD2DataFrameReflectionJava")
                .setMaster("local");

        JavaSparkContext sc = new JavaSparkContext(conf);

        SQLContext sqlContext = new SQLContext(sc);

        JavaRDD<String> original = sc.textFile(input, 1);

        JavaRDD<Student> rdd = original.map(
                (Function<String, Student>) v1 -> {
                    String[] fields = v1.split("\t");
                    Student stu = new Student();
                    stu.setAge(Integer.valueOf(fields[2].trim()));
                    stu.setId(Integer.valueOf(fields[0].trim()));
                    stu.setName(fields[1]);
                    return stu;
                }
        );

        /*
         * 使用反射方式，将RDD转换为DataFrame
         * 将Student.class传入进去，其实就是用反射的方式来创建DataFrame
         * 因为Student.class本身就是反射的一个应用
         * 然后底层还得通过对Student Class进行反射，来获取其中的Fields
         * 这是要求JavaBean必须实现Serializable接口，是可序列化的
         */
        Dataset stuDF = sqlContext.createDataFrame(rdd, Student.class);

        //拿到了一个DataFrame之后就可以将其注册为一个临时表，然后针对临时表进行操作
        stuDF.registerTempTable("students");

        //针对students临时表执行SQL语句，查询年龄小于等于20风的学生
        Dataset youngDF = sqlContext.sql("select * from students where age <= 20");

        //将查询出来的DataFrame再次转换为RDD
        JavaRDD<Row> youngRDD = youngDF.javaRDD();

        //将RDD为的数据进行映射，映射为Student
        JavaRDD<Student> stuRDD = youngRDD.map(
                (Function<Row, Student>) v1 -> {
                    Student stu = new Student();
                    stu.setAge(v1.getInt(0));
                    stu.setId(v1.getInt(1));
                    stu.setName(v1.getString(2));

                    return stu;
                }
        );

        //将数据collect回来，打印出来
        List<Student> stulist = stuRDD.collect();

        for (Student stu : stulist) {
            System.out.println(stu.getId() + "\t\t" + stu.getName() + "\t\t" + stu.getAge());
        }

    }
}
