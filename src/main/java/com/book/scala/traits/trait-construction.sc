

/**
  * 《Scala程序设计》第9章：特征
  * 9.4：构造trait
  * 就像类一样，每次创建使用了trait的实例时，特征休都会被执行。
  * 我们在脚本中定义了C12类的基类Base12.运行脚本时会实行执行Base12类，
  * 之后再执行T1和T2特征体（依声明顺序从左到右的执行特征）
  * 最后才是C12类体
  *
  *
  * Time：2018-03-18 05:56:40
  * Author：Weidong Cao
  */
trait T1{
	println(s"  in T1:x = $x")
	val x = 1
	println(s"  in T1:x = $x")
}

trait T2{
	println(s"  in T2:y = $y")
	val y = "T2"
	println(s"  in T2:y = $y")
}

class Base12{
	println(s"  in Base12:b = $b")
	val b = "Base2"
	println(s"  in Base12:b = $b")
}

class C12 extends Base12 with T1 with T2{
	println(s"  in C12:c = $c")
	val c = "C12"
	println(s"  in C12:c = $c")
}

println(s"Creating C12:")
new C12
println(s"After CreatingC12")


