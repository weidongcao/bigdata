/**
  * 6.2.1章 匿名函数、Lambda与闭包
  * 在上一小节讨论函数捕捉外部变量时，我们将匿名函数multipoier为一个值
  * 不过，也可以用方法代替函数
  *
  * multiplier此时是一个方法。比较一下函数定义与方法定义的语法区别。除了
  * multiplier是方法以外，我们对它的使用与函数相同，因此它并没有引用this
  * 在需要的地方用了方法，我们就称该方法被提升为了函数。
  */
object Multiplier{
	var factor = 2
	def multiplier(i : Int) = i * factor
}

(1 to 10) filter ( _ % 2 == 0) map Multiplier.multiplier reduce (_ * _)

Multiplier.factor = 3
(1 to 10 ) filter ( _ % 2 == 0) map Multiplier.multiplier reduce (_ * _)