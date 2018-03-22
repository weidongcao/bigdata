/**
  * 《Scala程序设计》第11章Scala对象系统2
  * 11.7 对象层次结构的线性化算法
  * 用于测试Scala构造函数的调用顺序
  *
  * Time: 2018-03-22 07:32:42
  * Author: Weidong Cao
  */
class C1{
	print("C1   ")
}

trait T1 extends C1{
	print("T1   ")
}

trait T2 extends C1 {
	print("T2   ")
}

trait T3 extends C1{
	print("T3   ")
}

class C2 extends T1 with T2 with T3{
	print("C2   ")
}

val c2 = new C2