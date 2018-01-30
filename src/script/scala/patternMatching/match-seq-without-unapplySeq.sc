/**
  * 这两个sliding方法都返回迭代器，它们是"惰性"的。由于对大的序列进行复制代价太过昂贵，
  * 这两个函数都没有立即对所有操作的列表进行复制。对返回的迭代器调用 toSeq方法，
  * 可以将迭代器转为一个collection.immutable.Stream。这是一个惰性列表，创建时即对列表的头部元素求值
  * 但只在需要的时候才对尾部元素求值。调用 toList不一样，它返回一个List，创建时就求出了所有元素的值。
  */
val nonEmptyList = List(1, 2, 3, 4, 5)
val emptyList = Nil
val nonEmptyMap = Map("one" -> 1, "two" -> 2, "three" -> 3)

def windows2[T](seq: Seq[T]): String = seq match {
	case head1 +: head2 +: tail => s"($head1, $head2), " + windows2(seq.tail)
	case head +: tail => s"($head, _), " + windows2(tail)
	case Nil => "Nil"
}

for (seq <- Seq(nonEmptyList, emptyList, nonEmptyMap.toSeq)) {
	println(windows2(seq))
}

val seq = Seq(1, 2, 3, 4, 5)
val slide2 = seq.sliding(2)
slide2.toSeq
slide2.toList
seq.sliding(3,2).toList