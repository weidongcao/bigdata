/**
  * 《Scala程序设计》
  * 4.6章 case类的匹配
  * 调用zipWithIndex时，返回的无级形式为((name, cost), index).
  * 通过匹配这种形式，我们提取了输入无级的三个元素，并将其打印出来。
  * 这样的代码是我们经常使用的。
  */

val itemsCosts = Seq(("Pencil", 0.52), ("Paper", 1.35), ("Notebook", 2.43))
val itemsCostsIndices = itemsCosts.zipWithIndex
for (itemCostIndex <- itemsCostsIndices) {
	itemCostIndex match{
		case((item, cost), index) => println(s"$index: $item costs $cost each")
	}
}

val list = 1 +: 2 +: 3 +: 4 +: Nil