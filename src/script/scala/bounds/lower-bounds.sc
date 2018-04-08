/**
  * 《Scala程序设计》第14章Scala类型系统1
  * 14.2.2 类型边界下限
  *
  */
class Parent(val value: Int){
	override def toString: String = s"${this.getClass.getName} --> (${value})"
}
class Child(value: Int) extends Parent(value)

val op1: Option[Parent] = Option(new Child(1))
val p1: Parent = op1.getOrElse(new Parent(10))

val op2: Option[Parent] = Option[Parent](null)
val p2a: Parent = op2.getOrElse(new Parent(10))
val p2b: Parent = op2.getOrElse(new Child(100))

val op3: Option[Parent] = Option[Child](null)
val p3a: Parent = op3.getOrElse(new Parent(20))
val p3b: Parent = op3.getOrElse(new Child(200))
