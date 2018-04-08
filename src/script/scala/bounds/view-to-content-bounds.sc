/**
  * 《Scala程序设计》第14章 Scala类型系统
  * 14.4 视力边界
  *
  * Time:2018-03-27 06:31:10
  * Author:Weidong Cao
  */

import scala.language.implicitConversions

object Serialization{
	case class Rem[A](value: A){
		def serialized: String = s"-- $value --"
	}
	type Writable[A] = A => Rem[A]
	implicit val fromInt: Writable[Int] =(i: Int)   => Rem(i)
	implicit val fromFloat: Writable[Float] = (f: Float) => Rem(f)
	implicit val fromString: Writable[String] = (s: String) => Rem(s)

}

import Serialization._
object RemoteConnection{
	def write[T: Writable](t: T): Unit = {
		println(t.serialized)
	}
}

RemoteConnection.write(100)
RemoteConnection.write(3.14f)
RemoteConnection.write("Hello")
//RemoteConnection.write((1,2))