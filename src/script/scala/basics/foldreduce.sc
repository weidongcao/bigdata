val list = List(1, 2, 3, 4, 5, 6)

list reduce (_ + _)

list.fold(10)(_ * _)
(list fold 10)(_ * _)

//用偏应用的方法创建了fold1，随后我们给出了剩下的参数列表(_ * _)以调用fold1能够想到
//(list fold 10)是偏应用的开关，随后加上后面的函数(_ * _)
val fold1 = (list fold 10) _

fold1(_ * _)

/**
  * 如果我们对任何一个空的集合执行fold操作，它会返回初始种子的值。与此不同，
  * reduce无法对空集合进行操作，因为没有值可以返回
  */
(List.empty[Int] fold 10) (_ + _)
//List.empty[Int] reduce (_ + _)

/**
  * 然而如果你还确定集合是否为空，（例如当集合是传入到你函数中的参数时）
  * 你可以用reduceOption代替reduce
  */
List.empty[Int] reduceOption(_ + _)
List(1, 2, 3, 4, 5) reduceOption(_ + _)

(List(1, 2, 3, 4, 5, 6) foldRight List.empty[String]) {
	(x, list) => ("[" + x + "]") :: list
}


