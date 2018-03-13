/**
  * 《Scala程序设计》第7章深入了解for推导式7.4.2 Either：Option类型的逻辑扩展
  * Option类型有一个弊端：None对象不能提供任何信息告诉我们为什么不返回值。例如：
  * 由于发生错误，返回None对象的情况。使用Either替代Option是一种解决方案。
  * 与Either的英文字面意思一样，Either是一类能且只能持有两种事物 中一种的容器。
  * 换言之，Option能持有0个或一个元素，而Either则持有这个或那个元素项。
  * Either是一个包含两个参数的参数化类型，该类型的签名为Either[+A, +B]，
  * 其中A和B是Either对象中可能持有的元素类型。+A表示Either是类型参数A的协变，+B也是如此。
  * 这意味着如果你需要类型Either[Any，Any]的值，你可以通过使用类型Either[String, Int]得到。
  * 这是因为String和Int类型都是Any类型的子类型。所以Either[String, Int]是Either[Any, Any]的子类型。
  * Either同时也是sealed抽象类（只能在相同文件中声明子类型）。Either有两个子类Left[A]和Right[B]，
  * 我们可以通过这两个子类从两个可能的元素中选择一种。Either概念的产生时间早于Scala。
  * 很长时间以来它被认为是招聘异常的一种替代方案。为了尊重历史习惯，当Either用于表示错误标志或某一对象值时，
  * Left值用于表示错误标志，如：信息字符串或下层库抛出的殿堂 而正常返回时则使用Right对象。
  * 很明显，Either可以用于任何需要持有某一个或另一个对象的场景中，而这个两个对象的类型可能不同。
  * 在我们深入了解Either类型的某些特殊点之前，我们先用Either类型把之前的示例重写一遍。
  * 首先，假如你持有一组Either对象并希望忽略掉错误值（Left对象），一个简单的for推导式便能做到这一点。
  *
  * #Time 2018-03-12 06:38:36
  * @param i
  * @return
  */
def positive(i: Int): Either[String, Int] =
	if (i > 0) Right(i) else Left(s"nonpositive number $i")

for {
	i1 <- positive(5).right
	i2 <- positive(10 * i1).right
	i3 <- positive(25 * i2).right
	i4 <- positive(2 * 13).right
} yield (i1 + i2 + i3 + i4)

for {
	i1 <- positive(5).right
	i2 <- positive(-1 * i1).right
	i3 <- positive(25 * i2).right
	i4 <- positive(-2 * i3).right
} yield (i1 + i2 + i3 + i4)

//两个Either[String, Int]对象，其中一个对象赋予了Left[String]值，另一个赋予了Right[Int]值。
val l: Either[String, Int] = Left("boo")
val r: Either[String, Int] = Right(12)

/**
  * 如果一个类型中包含两个参数，那么它可以使用中缀表示法表示类型说明。
  */
val l1: Either[String, Int] = Left("boo")
val l2: String Either Int = Left("boooooooo")
val r1: String Either Int = Right(12)

/**
  * 使用类型别名
  * 为了更好的表示类型，我希望可以将Either类型更名为Or类型，假如你也青睐于Or,也可以在代码中使用类型别名
  * @tparam A
  * @tparam B
  */
type Or[A, B] = Either[A, B]
val l3: String Or Int = Left("better?")

/**
  * Either本身并未定义组合方法map, fold等，我们只能访问Either.left或Either.right中的组合方法。
  * 之所以如此，是因为我们的绷脸吉他接受单个函数参数，但我们需要为Either容器指定两个函数参数。
  * 当Either值为Left值时，调用一个，而Either值为 Right值时调用另一个。Either对象提供了left和right方法，
  * 这两个方法会构建出一个提供组合方法的投影对象。
  */
l.left
l.right

r.left
r.right


