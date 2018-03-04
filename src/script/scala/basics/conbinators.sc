"""
  |
""".stripMargin
object Combinators{
	def map[A, B](f: (A) => B)(list: List[A]): List[B] = list map f
}
val intToString = (i:Int) => s"N=$i"

val flist = Combinators.map(intToString) _

val list = flist(List(1,2,3,4))

