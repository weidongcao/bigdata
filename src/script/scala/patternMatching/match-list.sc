val nonEmptyList = List(1, 2, 3, 4, 5)
val emptyList = Nil
def listToString[T](list: List[T]): String = list match {
	case head :: tail => s"($head :: ${listToString(tail)}"
	case Nil => "(Nil"
}
for (l <- List(nonEmptyList, emptyList)){
	println(listToString(l))
}
val s1 = (1 +: (2 +: (3 +: (4 +: (5 +: Nil)))))
val l = (1 :: (2 :: (3 :: (4 :: (5 :: Nil)))))
val s2 = (("one", 1) +: (("two",2) +: (("three", 3) +: Nil)))
val m = Map(s2 :_*)