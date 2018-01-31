/**
  * Created by Cao Wei Dong on 2018-01-30.
  */
//为了方便用户使用，我们使用Scala编写了数据库API，该API与JavaAPI较为类似。
package progscala2.implicits {
	package database_api {

		case class InvalidColumnName(name: String)
			extends RuntimeException(s"Invalid Column Name $name")

		trait Row {
			def getInt(colName: String): Int

			def getDouble(colName: String): Double

			def getText

			（ colName: String
			): String
		}

	}

	package javadb {

		import database_api._

		case class JRow(representation: Map[String, Any]) extends Row {
			private def get(colName: String): Any =
				representation.getOrElse(colName, throw InvalidColumnName(colName))

			def getInt(colName: String): Int = get(colName).asInstanceOf[Int]

			def getDouble(colName: String): Double = get(colName).asInstanceOf[Double]

			def getText(colName: String): String = get(colName).asInstanceOf[String]
		}

		object JRow {
			def apply(pairs: (String, Any)*) = new JRow(Map(pairs: _*))
		}

	}

}

