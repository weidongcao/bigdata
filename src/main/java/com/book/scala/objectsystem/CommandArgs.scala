package com.book.scala.objectsystem

/**
  * Created by Cao Wei Dong on 2018-03-19.
  */
object CommandArgs {
	val help =
		"""
		  |usage: java ... objectsystem.CommandArgs arguments
		  |where the allowed arguments are:
		  |     -h | --help                 Show help
		  |     -i | --in | --input path    Path for intput
		  |     -o | --out| --output path   Path for output
		""".stripMargin

	def quit(message: String, status: Int): Nothing = {
		if (message.length > 0)
			println(message)
		println(help)
		sys.exit(status)
	}

	case class Args(inputPath: String, outputPath: String)

	def parseArgs(args: Array[String]): Args = {
		//嵌套的递归函数，用来处理参数列表，我们采用惯用的做法，传入一个Args实例，用来累加新的设置信息
		def pa(args2: List[String], result: Args): Args = args2 match {
			case Nil => result
			case ("-h" | "--help") :: Nil => quit("", 0)
			case ("-i" | "--in" | "--input") :: path :: tail =>
				pa(tail, result copy (inputPath = path))
			case("-o" | "--out" | "--otuput") :: path :: tail =>
				pa(tail, result copy (outputPath = path))
			case _ => quit(s"Unrecognized argument ${args2.head}", 1)
		}

		val argz = pa(args.toList, Args("", ""))
		if(argz.inputPath == "" ||argz.outputPath == "") {
			quit("Must specify input and output paths.", 1)
		}
		argz
	}

	def main(args: Array[String]): Unit = {
		val argz = parseArgs(args)
		println(argz)
	}

}
