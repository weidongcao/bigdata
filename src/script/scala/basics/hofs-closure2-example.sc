/**
  * 6.2.1章 匿名函数、Lambda与闭包
  * 验证Scala的闭包
  *
  * 调用m2，返回了一个类型为Int =>Int的函数。返回的内部值multiplier，multiplier引用了m2中定义的
  * factor，一旦m2返回，就离开了factor变量的作用域然后调用m1，将m2的返回值传递给它。尽管Factor变量
  * 已经离开了m1的作用域，但是程序的输出与之前的例子相同，仍为122880.m2返回的函数事实上是一个闭包，
  * 它包含了对Factor的引用。
  *
  * 闭包：是一个函数，可能匿名或具有名称，在定义中包含了自由变量，函数中包含了环境信息，以绑定其引用
  * 的自由变量。
  * @param multiplier 闭包引用的外部可变变量
  * @return
  */
def m1(multiplier: Int => Int) = {
	(1 to 10) filter (_ % 2 == 0) map multiplier reduce (_ * _)
}

def m2: Int => Int = {
	val factor = 2
	val multiplier = (i: Int) => i * factor
	multiplier
}

m1(m2)