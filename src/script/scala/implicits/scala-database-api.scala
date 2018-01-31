/**
  * Created by Cao Wei Dong on 2018-01-30.
  */
//运用Scala封闭对象实现类Java的数据库API
package progscala2.implicits {
	package scaladb{
		object implicits{
			import javadb.JRow

			implicit class SRow(jrow:JRow){
				def get[T](colName: String)(implicit toT: (JRow, String) => T): T =
					toT(jrow, colName)
			}

			implicit val jrowToInt:(JRow, String) => Int = (jrow: JRow, colName: String) => jrow.getInt(colName)
			implicit val jrowToDouble:(JRow, String) => Double = (jrow: JRow, colName: String) => jrow.getDouble(colName)
			implicit val jrowToString:(JRow, String) => String = (jrow: JRow, colName: String) => jrow.getText(colName)
		}

		object DB{
			import implicits._

			def main(args: Array[String]) = {
				val row = javadb.JRow("one" -> 1, "two" -> 2.2, "three" -> "THREE!")

				val oneValue1:  Int      = row.get("one")
				val twowValue1: Double   = row.get("two")
				val threeValue1:  String = row.get("three")
				//val fourValue1:  Byte  = row.get("four")

				println(s"one1  ->  $oneValue1")
				println(s"two1  ->  $twowValue1")
				println(s"three1->  $threeValue1")

				val oneValue2 = row.get[Int]("one")
				val twoValue2 = row.get[Double]("two")
				val threeValue2 = row.get[String]("three")

				println(s"one2  -> $oneValue2")
				println(s"two2  -> $twoValue2")
				println(s"threeValue2 -> $threeValue2")
			}
		}
	}
}

