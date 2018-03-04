import scala.annotation.tailrec

/**
  * 6.4章 尾部调用和尾部调用优化
  * Scala有一个标记：@tailrec
  * 它可以添加在你认为是尾递归的递归函数中。如果编译器无法对它做尾递归优化，系统将抛出
 *
  * factorial用嵌套的方法fact完成了计算工作。fact是尾递归的，因为它用一个累乘的参数
  * 去保存计算结果。该参数与一个乘数相乘。然后在函数的尾部调用fact自身。@tailrec标记不再
  * 抛出异常，因为编译器可以成功将其转为循环
  *
  * 如果用一个大数——如100000——去调用原始的非尾递归版本的factorial函数，一般普通的
  * 台式计算机会出现栈溢出。尾递归优化的版本则可以成功运行。并返回结果
  *
  * 定义一个嵌套的尾递归函数，将累积值作为参数，是将很多普通递归算法转为尾递归的实用技巧
  * @param i
  * @return
  */
def factorial(i: BigInt): BigInt = {
	@tailrec
	def fact(i: BigInt, accumulator: BigInt): BigInt =
		if (i ==1) accumulator
		else fact(i -1, i * accumulator)
		fact(i, 1)
}

for (i <- 1 to 10){
	println(s"$i:\t${factorial(i)}")
}