package bigdata.spark.sql;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.api.java.function.PairFunction;
import org.apache.spark.api.java.function.VoidFunction;
import org.apache.spark.sql.DataFrame;
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

        /**
         * JDBC数据源首先是通过SQLContext的read系列方法将mysql中的数据加载到DataFrame
         * 然后可能将DataFrame转换为RDD，使用Spark Core提供的各种算子进行操作
         * 最后可以将得到的数据结果通过foreach算子，写入到MySQL、HBase、Redi等等
         */
        Map<String, String> options = new HashMap<String, String>();
        options.put("url", "jdbc:mysql://spark.don.com:3306/spark");
        options.put("dbtable", "stu_info");
        DataFrame stu_infoDF = sqlContext.read().format("jdbc").options(options).load();

        options.put("dbtable", "stu_score");
        DataFrame stu_scoreDF = sqlContext.read().format("jdbc").options(options).load();

        //将两个DataFrame转换为JavaPairRDD，执行join操作
        JavaPairRDD<String, Tuple2<Integer, Integer>> stuRDD = stu_infoDF.javaRDD().mapToPair(
                new PairFunction<Row, String, Integer>() {
                    @Override
                    public Tuple2<String, Integer> call(Row row) throws Exception {
                        return new Tuple2<String, Integer>(
                                row.getString(0),
                                row.getInt(1)
                        );
                    }
                }
        ).join(stu_scoreDF.javaRDD().mapToPair(
                new PairFunction<Row, String, Integer>() {
                    @Override
                    public Tuple2<String, Integer> call(Row row) throws Exception {
                        return new Tuple2<String, Integer>(
                                row.getString(0),
                                row.getInt(1)
                        );
                    }
                }
        ));

        //将JavaPairRDD转换为JAVARDD<Row>
        JavaRDD<Row> stu_rowRDD = stuRDD.map(
                new Function<Tuple2<String, Tuple2<Integer, Integer>>, Row>() {
                    @Override
                    public Row call(Tuple2<String, Tuple2<Integer, Integer>> v1) throws Exception {
                        return RowFactory.create(v1._1(), v1._2()._1(), v1._2()._2());
                    }
                }
        );

        //过滤出分数大于80分的数据
        JavaRDD<Row> filterStu_rowRdd = stu_rowRDD.filter(
                new Function<Row, Boolean>() {
                    @Override
                    public Boolean call(Row v1) throws Exception {
                        if (v1.getInt(2) > 80) {
                           return true;
                        }
                        return false;
                    }
                }
        );

        //转换为DataFrame
        List<StructField> structFields = new ArrayList<StructField>();
        structFields.add(DataTypes.createStructField("name", DataTypes.StringType, true));
        structFields.add(DataTypes.createStructField("age", DataTypes.IntegerType, true));
        structFields.add(DataTypes.createStructField("score", DataTypes.IntegerType,true));

        StructType structType = DataTypes.createStructType(structFields);

        DataFrame stuDF = sqlContext.createDataFrame(filterStu_rowRdd, structType);

        Row[] rows = stuDF.collect();
        for (Row row : rows) {
            System.out.println(row);
        }

        //将DataFrame中的数据保存到MySQL表中
        stuDF.javaRDD().foreach(
                new VoidFunction<Row>() {
                    @Override
                    public void call(Row row) throws Exception {
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
                }
        );

        sc.close();
    }
}
