/**
  * 请留意这两个for推导式中第二个和第三个表达式。
  * 这两个表达式使用了之前表达式的结果值，这些表达式似乎都认为程序将按照
  * "正常流程运行"，因此使用从Option[Int]对象中抽取出的Int值是安全的。
  * 我们认为第一个for推导式能正常执行。而第二个for推导式也能正常执行。
  * 一旦返回 了None值，后续的表达式将会停止运行。这是因为map或flatmap
  * 不会对这些函数字面量进行处理。
  *
  * @param i
  * @return
  */
def positive(i: Int): Option[Int] =
	if (i > 0) Some(i) else None

for {
	i1 <- positive(5)
	i2 <- positive(10 * i1)
	i3 <- positive(25 * i2)
	i4 <- positive(2 * 13)
} yield (i1 + i2 + i3 + i4)

for {
	i1 <- positive(5)
	i2 <- positive(-1 * i1)
	i3 <- positive(25 * i2)
	i4 <- positive(-2 * 13)
} yield (i1 + i2 + i3 + i4)