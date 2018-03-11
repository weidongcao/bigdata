//for推导式的一个例子。
val map = Map("one" ->1, "two" ->2)

val list1 = for {
	(key, value) <- map
	i10 = value + 10
} yield (i10)

//翻译后的语句
val list2 = for{
	(i, i10) <- for{
		x1 @ (key, value) <- map
	} yield {
		val x2 @ i10 = value + 10
		(x1, x2)
	}
} yield (i10)