package bigdata.spark.streaming

import org.apache.spark.SparkConf
import org.apache.spark.streaming.{Seconds, StreamingContext}

/**
  * Created by Administrator on 2016/10/24.
  */
object WordCountScala {
def main(args: Array[String]): Unit = {
  val conf = new SparkConf()
    .setMaster("local[2]")
    .setAppName("WordCountScala")

  val ssc = new StreamingContext(conf, Seconds(1))

  val original = ssc.socketTextStream("spark.don.com", 9999)

  val words = original.flatMap(_.split(" "))

  val pairs = words.map(word => (word, 1))

  val wordCounts = pairs.reduceByKey(_ + _)

  wordCounts.print()

  ssc.start()

  ssc.awaitTermination()
}

}
