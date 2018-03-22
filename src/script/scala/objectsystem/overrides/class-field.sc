/**
  * 《Scala程序设计》第11章 Scala对象系统2
  * 11.4 覆写trait字段和具体字段
  * 测试覆写具体字段
  *
  * Time: 2018-03-21 06:15:27
  * Author: Weidong Cao
  */
class C{
	val name = "C"
	var age = 0
}

class ChildC extends C{
	override val name = "ChildC"
	age = 1
}

val c = new ChildC()
println(c.name)
println(c.age)

/**
  * 下面对上面的救命进行修改，将基类中的val字段和var字段都修改成abstract字段。
  */
abstract class AbstractClassH{
	val name :String
	var age :Int
}

//由于这些字段均声明为abstract类型，因此ChildAbstractClassH中不再需要override关键字
//name和age抽象字段，它们并不是包含默认值 的具体字段。
// 如果在Javva类中进行类似的声明，Java声明一个具有默认值 的具体字段。
//而在本例 中默认值为 null。Java并不支持抽象字段，只支持抽象方法。
class ChildAbstractClassH extends AbstractClassH{
	val name = "ChildAbstractClassH"
	var age = 28
}

val cc = new ChildAbstractClassH()
println(cc.name)
println(cc.age)
