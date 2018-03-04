/**
  * 6.4章尾递归的trampoline优化
  *
  * 存在这样一种递归：
  * 函数A不调用自身而是调用了另一个函数B，而函数B也不调用自身而是调用了A，
  * 然后A再调用B，如此循环。
  * trampoline可以将这种来回调用的计算的数据结构也转化为循环
  */

import scala.util.control.TailCalls._

def isEven(xs: List[Int]): TailRec[Boolean] =
	if (xs.isEmpty) done(true) else tailcall(isOdd(xs.tail))

def isOdd(ints: List[Int]): TailRec[Boolean] =
	if (ints.isEmpty) done(false) else tailcall(isEven(ints.tail))

for (i <- 1 to 5){
	val even = isEven((1 to i).toList).result
	println(s"$i is even? $even")
}

