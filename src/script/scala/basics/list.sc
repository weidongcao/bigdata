"""许多数据结构是序列型的，也就是说，元素可以按特定的顺序访问，如：元素的插入顺序或其他特定顺序。
  |collection.Seq是一个trait，是所有可变或不可变序列类型的抽象，
  |其子trait collection.mutable.Seq及collection.immutable.Seq分别对应可变和不可变序列
  |"""
val list1 = List("Programming", "Scala")

//向列表里追加元素时，该元素会被追加到列表的开头，成为新列表的"头部"
val list2 = "Pelple"::"Should"::"Read"::list1

"""可以用List.apply方法创建队列，然后用::方法（称为cons，意为构造）向队列头部追加数据，
   从而创建新的列表。在这里我们使用了简单写法，活力了点号与小括号。我们也提到过，以冒号（:）
   结尾的方法向右结合，因此x::list其实是list.::(x)
  |我们也可以用::方法向Nil空队列追加元素创建新队列。
  |"""
val list3 = "Programming"::"Scala"::Nil

val list4 = "People"::"Should"::"Read"::Nil

//可以用"++"方法将两个列表连接起来。
val list5 = list4 ++ list3





