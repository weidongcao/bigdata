import scala.util.{Failure, Success, Try}

/**
  * 《Scala程序设计》第7章，深入学习for推导式
  * 7.4.3 Try类型
  *
  * #time 2018年3月14日 05:46:17
  * @param i
  * @return
  */
def positive(i: Int): Try[Int] = Try {
	assert(i > 0, s"nonpositive number $i")
	i
}

//def positive2(i: Int): Try[Int] = Try {
//	if (i > 0) Success(i)
//	else Failure(new AssertionError("Assertion Failed"))
//}

for {
	i1 <- positive(5)
	i2 <- positive(10 * i1)
	i3 <- positive(25 * i2)
	i4 <- positive(2 * i3)
} yield (i1 + i2 + i3 + i4)

for {
	i1 <- positive(5)
	i2 <- positive(-1 * i1)
	i3 <- positive(25 * i2)
	i4 <- positive(-2 * i3)
} yield (i1 + i2 + i3 + i4)
