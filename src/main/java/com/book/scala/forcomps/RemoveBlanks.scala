package com.book.scala.forcomps

/**
  * Created by Cao Wei Dong on 2018-03-04.
  */
object RemoveBlanks {
	/**
	  * 从指定的输入文件中移除空行
	  */
	def apply(path: String, compressWhiteSpace: Boolean = false): Seq[String] = for {
		//使用scala.io.Source对象打开文件并读取文件行，getLines返回scala.collection.Iterator对象。
		// 由于for推导式无法返回Iterator对象，for推导式的返回类型由初始的生成器所决定，因此我们必须将其转化成一个序列。
		line <- scala.io.Source.fromFile(path).getLines().toSeq
		//使用正则表达式过滤空行
		if line.matches("""^\s*$""") == false
			//定义局部变量，假如未开启空白符压缩，那么局部变量将存储未变的非空行，反之则会将局部变量设置为一个新的行值，
			// 该行值已经将所有的空白符压缩为一个空格
			line2 = if (compressWhiteSpace) line replaceAll("\\s+", " ")
			else
				line
	//由于我们使用yield方法返回行内容，因此for推导式构造了apply方法返回 的Seq[String]。随后，我们也将处理apply返回的实际容器。
	} yield line2

	/**
	  * 从指定的输入文件中移除窄，并将其他行内容依次发送给标准输出。
	  *
	  */
	//main方法使用for白发苍苍处理参数列表，每个参数都会被视为待处理的文件路径。
	def main(args: Array[String]) = for {
		path2 <- args
		//文件路径以-字符起始，空白符会被压缩，否则只会除去空白行。
		(compress, path) = if (path2 startsWith "-") (true, path2.substring(1))
				else (false, path2)
		line <- apply(path, compress)
	//将所有处理后的行内容一直输出到标准输出stdout
	} println(line)

}
