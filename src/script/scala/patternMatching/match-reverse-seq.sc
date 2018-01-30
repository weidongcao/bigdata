/**
  * 如果希望逆序处理序列中的每个元素时该怎么办呢？
  * 可以用一个对象进行处理
  * Scala库的对象" :+ "
  * 可以让你匹配List的最后一个元素，然后从后往前依次访问各元素
  */
val nonEmptyList = List(1, 2, 3, 4, 5)
val nonEmptyVector = Vector(1, 2, 3, 4, 5)
val nonEmptyMap = Map("one" -> 1, "two" -> 2, "three" -> 3)
def reverseSeqToString[T](l: Seq[T]): String = l match {
	case prefix :+ end => reverseSeqToString(prefix) + s" :+ $end"
	case Nil => "Nil"
}

/**
  * Nil是第一个输出的元素，它的结合性是左结合的。同时，
  * 对于其他两个输入的List和Vector，也生成了相同的
  */
for(seq <- Seq(nonEmptyList, nonEmptyMap.toSeq, nonEmptyVector)) {
	println(reverseSeqToString(seq))
}

/**
  * 可以用输出的内容重新构造一个集合
  */
val list1 = Nil :+ 1 :+ 2 :+ 3 :+ 4

