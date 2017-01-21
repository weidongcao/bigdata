package bigdata.spark.streaming;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.api.java.function.PairFunction;
import org.apache.spark.sql.DataFrame;
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
        DataFrame scoreDF = sqlContext.read().json("E:\\Workspaces\\resource\\TestData\\students-score.json");

        scoreDF.registerTempTable("score");

        DataFrame goodDF = sqlContext.sql("select name from score where score >= 80");

        List<String> goodStu = goodDF.javaRDD().map(
                new Function<Row, String>() {
                    @Override
                    public String call(Row v1) throws Exception {
                        return v1.getString(0);
                    }
                }
        ).collect();

        //然后针对JavaRDD<String>创建DataFrame
        List<String> infoJson = new ArrayList<>();

        infoJson.add("{\"name\":\"Leo\", \"age\": 18}");
        infoJson.add("{\"name\":\"Marry\", \"age\": 17}");
        infoJson.add("{\"name\":\"Jack\", \"age\": 19}");

        JavaRDD<String> infoRDD = sc.parallelize(infoJson);

        DataFrame infoDF = sqlContext.read().json(infoRDD);

        infoDF.registerTempTable("info");

        String sql = "select name, age from info where name in (";

        for (int i = 0; i < goodStu.size(); i++) {
            sql += "'" + goodStu.get(i) + "'";
            if (i < goodStu.size() - 1) {
                sql += ",";
            }
        }

        sql += ")";

        DataFrame goodStuInfoDF = sqlContext.sql(sql);

        JavaPairRDD<String, Tuple2<Integer, Integer>> goodStuRDD =
                goodDF.javaRDD().mapToPair(
                        new PairFunction<Row, String, Integer>() {
                            @Override
                            public Tuple2<String, Integer> call(Row row) throws Exception {
                                return new Tuple2<String, Integer>(row.getString(0),
                                        Integer.valueOf(String.valueOf(row.getLong(1))));
                            }
                        }
                ).join(goodStuInfoDF.javaRDD().mapToPair(
                        new PairFunction<Row, String, Integer>() {
                            @Override
                            public Tuple2<String, Integer> call(Row row) throws Exception {
                                return new Tuple2<String, Integer>(
                                        row.getString(0),
                                        Integer.valueOf(String.valueOf(row.getLong(1)))
                                );
                            }
                        }
                ));

        JavaRDD<Row> goodStudentRowsRdd = goodStuRDD.map(
                new Function<Tuple2<String, Tuple2<Integer, Integer>>, Row>() {
                    @Override
                    public Row call(Tuple2<String, Tuple2<Integer, Integer>> v1) throws Exception {
                        return RowFactory.create(v1._1(), v1._2()._1(), v1._2()._2());
                    }

                }
        );

        List<StructField> structFields = new ArrayList<StructField>();
        structFields.add(DataTypes.createStructField("name", DataTypes.StringType, true));
        structFields.add(DataTypes.createStructField("score", DataTypes.IntegerType, true));
        structFields.add(DataTypes.createStructField("age", DataTypes.IntegerType, true));

        StructType structType = DataTypes.createStructType(structFields);

        DataFrame df = sqlContext.createDataFrame(goodStudentRowsRdd, structType);

        df.write().format("json").save("E:\\Workspaces\\resource\\TestData\\aaa\\bb");


    }
}
