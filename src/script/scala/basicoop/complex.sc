/**
  * 《Scala程序设计》第8章：Scala面向对象编程
  * 8.6.2 一元方法
  *
  * 一旦定义了一元操作符，就可以将操作符放在实例之前，就像我们在定义c2时所做的那样。
  * 也可以像定义c3时那样，将 一元操作符其他方法一般进行调用。
  *
  * Time 2018-03-16 06:45:52
  * Author Weidong Cao
  * @param real
  * @param imag
  */
case class Complex(real: Double, imag: Double){
	def unary_- : Complex = Complex(-real, imag)
	def -(other: Complex) = Complex(real - other.real, imag - other.imag)
}

val c1 = Complex(1.1, 2.2)

val c2 = -c1

val c3 = c1.unary_-

val c4 = c1 - Complex(0.4, 1.0)