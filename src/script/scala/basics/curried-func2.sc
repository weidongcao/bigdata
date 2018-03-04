/**
  * 6.6章Curry化与其他转换
  * 类型签名String => String => String 与String => (String => String)是等价的。
  * 调用f1或f2时的第一个参数列表将会返回一个类型为String => String的新函数。
  */
val f1: String => String => String = (s1: String) => (s2: String) => s1 + s2

val f2: String => (String => String) = (s1: String) => (s2: String) => s1 + s2

f1("Hello ")("World")
f2("Hello ")("World")

def cat3(s1: String, s2: String) = s1 + s2

val cat3Curried = (cat3 _ ).curried
val cat3Uncurried = Function.uncurried(cat3Curried)

cat3Uncurried("Hello ", "World!")

def mutiplier(i: Int)(factor: Int) = i * factor

val byFive = mutiplier(5) _
val byTen = mutiplier(10) _

byFive(2)
byTen(2)