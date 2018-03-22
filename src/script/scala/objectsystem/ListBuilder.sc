/**
  * 《Scala程序设计》第12章 Scala集合库
  * 12.3 集合库的设计惯例
  *
  * Time: 2018-03-23 06:34:54
  * Author: Weidong Cao
  */

import collection.mutable.Builder
class ListBuilder[T] extends Builder[T, List[T]]{
	private var storage = Vector.empty[T]

	def += (elem: T) = {
		storage = storage :+ elem
		this
	}
	def clear(): Unit = {storage = Vector.empty}
	def result(): List[T] = storage.toList
}

val lb = new ListBuilder[Int]
(1 to 3) foreach (i => lb += i)

lb.result()