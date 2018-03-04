/**
  * 6.6章 Curry化与函数的其他转换
  * @param s1
  * @param s2
  * @return
  */
def cat1(s1: String)(s2: String) = s1 + s2
//Curry化函数
def cat2(s1: String) = (s2: String) => s1 + s2

val hello = cat1("Hello ") _
cat1("Hello ")("World!")
cat2("Hello ")("World!")

val cat2hello = cat2("Hello ")

cat2hello("world")

//可以将一个带有多个参数的方法转为Curry化的形式
def cat3(s1: String, s2: String) = s1 + s2
cat3("Hello ", "World!")
val cat3Curried = (cat3 _).curried
cat3Curried("Hello ")("World!")