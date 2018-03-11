/**
  * Some(i) <- list 语句会对results变量中包含的元素执行模式匹配，移除None元素，
  * 并抽取类型为Some的元素的整数值。之后，生成我们所希望得到的最终表达式。
  * 而该程序输出为Vector(20， 40)
  */
val results: Seq[Option[Int]] = Vector(Some(10), None, Some(20))

val results2 = for {
	Some(i) <- results
} yield (2 * i)

val results2b = for {
	Some(i) <- results withFilter {
		case Some(i) => true
		case None => false
	}
} yield (2 * i)

/**
  * 如果传递给Map方法的偏函数未使用使用None => ...子句，
  * 这种情况通常会比较危险。但如果map方法处理的元素出现了None对象，
  * Scala又会抛出MatchError的异常。不过由于调用 withFilter
  * 的方法中已经移掉了所有的None元素，运行代码时便不会出现这一错误
  */
val results2c = results withFilter {
	case Some(i) => true
	case None => false
} map {
	case Some(i) => (2 * i)
}