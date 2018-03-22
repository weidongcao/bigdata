import com.book.scala.basicoop2.Address

/**
  * 《Scala程序设计》第8章：Scala面向对象编程
  * 8.8 调用父类构造器
  *
  * 派生类的主构造器必须调用父类的构造器，可以是父类的主构造器或次级构造器
  * 在Java中，我们会定义构造方法，并用super调用父类的初始化逻辑。而Scala中，
  * 我们用ChildClass(...) extends ParentClass(...)的语法隐式地调用父类的构造器。
  *
  * 尽管像在Java中一样，super可以用来调用被覆写的父类方法，但它不能用来调用父类的构造器。
  *
  * Time：2018-03-16 07:37:03
  * Author： Weidong Cao
  * @param name
  * @param age
  * @param address
  */
case class Person(
	 name: String,
	 age:  Option[Int] = None,
	 address: Option[Address] = None
 )

//Employee被声明为一个普通的类，而不是case类
//新的字段title和manager需要用val关键字声明，因为Employee不是case类。其他参数是从Person继承的字段。
//注意，我们也调用了Person的主构造器。
class Employee(
	name: String,
	age: Option[Int] = None,
	address: Option[Address] = None,
	val title: String = "[unknown]",
	val manager: Option[Employee] = None
) extends Person(name, age, address){
	//覆写toString方法，如果不覆写，将会调用Person.toStrin
	override def toString =
		s"Employee($name, $age, $title, $manager)"
}

val a1 = new Address("1 Scala Lane", "Anytown", "CA", "98765")
val a2 = new Address("98765")

val ceo = new Employee("Dong CEO", title="CEO")

new Employee("Buck Trends1")

new Employee("Buck Trends2", Some(20), Some(a1))

new Employee("Buck Trends3", Some(20), Some(a1), "Zombie Dev")

new Employee("Buck Trends4", Some(20), Some(a1), "Zombie Dev", Some(ceo))