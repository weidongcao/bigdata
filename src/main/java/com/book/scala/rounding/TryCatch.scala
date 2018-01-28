package com.book.scala.rounding

/**
  * Created by Cao Wei Dong on 2018-01-25.
  */
object TryCatch {

	import scala.io.Source
	import scala.util.control.NonFatal
	def contLines(arg: String) = {
		println()
		var source:Option[Source] = None
		try{
			source = Some(Source.fromFile(arg))
			val size = source.get.getLines().size
			println(s"file $arg has $size lines")
		}catch{
			case NonFatal(ex) => println(s"Non fatal exception! $ex")
		}finally{
			for (s <- source) {
				println(s"Closing $arg ...")
				s.close()
			}
		}
	}

	def main(args: Array[String]): Unit = {
		args foreach (arg => contLines(arg))
	}

}
