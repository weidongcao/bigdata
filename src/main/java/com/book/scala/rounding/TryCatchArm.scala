package com.book.scala.rounding

import scala.util.control.NonFatal

/**
  * Created by Cao Wei Dong on 2018-01-25.
  */
object manage{
	def apply[R <: { def close():Unit }, T](resource: => R)(f: R=> T) = {
		var res: Option[R] = None
		try{
			res = Some(resource)
			f(res.get)
		} catch {
			case NonFatal(ex) => println(s"Non Fatal Exception! $ex")
		}finally{
			if (res != None) {
				println(s"Closing resource ...")
				res.get.close()
			}
		}
	}
}
object TryCatchArm {
	def main(args: Array[String]): Unit = {
		args foreach (arg => contLines(arg))
	}

	import scala.io.Source
	def contLines(str: String) = {
		println()
		manage(Source.fromFile(str)){
			source =>
				val size = source.getLines.size
				println(s"file $str has $size lines")
				if(size > 20) throw new RuntimeException("Big file")
		}
	}

}
