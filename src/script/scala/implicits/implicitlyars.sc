import math.Ordering

case class MyList[A](list: List[A]){
	def sortBy1[B](f:A =>B)(implicit ord: Ordering[B]):List[A] =
		list.sortBy(f)(ord)

	def sortBy2[B: Ordering](f: A=> B): List[A] =
		list.sortBy(f)(implicitly[Ordering[B]])
}

val list = MyList(List(1, 3, 2, 7, 4))

list sortBy1(i => -1)

list sortBy2(i => -i)