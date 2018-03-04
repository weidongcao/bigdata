/**
  * 6.2.1章 匿名函数、Lambda与闭包
  * 测试Scala的闭包
  */
var factor = 2
val multiplier  = (i: Int) => i * factor

(1 to 10) filter (_ % 2 == 0) map multiplier reduce (_ * _)
factor = 3
(1 to 10) filter (_ % 2 == 0) map multiplier reduce (_ * _)