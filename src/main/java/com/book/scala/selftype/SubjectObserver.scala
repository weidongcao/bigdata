package com.book.scala.selftype

/**
  * 《Scala程序设计》第14章 Scala类型系统1
  * 14.6 自类型标记
  *
  * Time:2018-03-28 06:10:55
  * Created by Cao Wei Dong on 2018-03-28.
  */
abstract class SubjectObserver {
	type S <: Subject
	type O <: Observer

	trait Subject{
		//为Subject声明一个自类型标记self:S。这意味着我们现在可以假设Subject为子类型
		//S的实例。S实际上是混入了Subject特征的任何类型。
		self: S =>
		private var observers = List[O]()
		def addObserver(observer: O) = observers ::= observer
		def notifyObservers() = observers.foreach(_.receiveUpdate(self))
	}

	trait Observer{
		def receiveUpdate(subject: S)
	}
}
