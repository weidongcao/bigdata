package com.book.scala.traits.observer

/**
  * 《Scala程序设计》第9章 特征
  *  9.2混入trait
  *  创建两个trait，分别用于声明观察者模式中的主体(Subject)和观察者(Observer)
  *  这两个trait同时还实现了主体和观察者的部分功能。
  *  之后我们会运用这两个trait处理回调行为
  *
  *  Time：2018-03-17 05:52:06
  * Created by Cao Wei Dong on 2018-03-17.
  */
//那些希望能在状态发生变化时得到通知的客户将运用这一trait。这些客户代码必须实现receiverUpdate方法
trait Observer[-State]{
	/**
	  * 由于Observer特征既未实现它所声明的方法，也未定义其他任何成员，在字节码级别，
	  * 该trait实际上与Java8之前的接口是完全相同的。
	  * 由于缺少方法实现体明显是一个抽象对象，
	  * 因此我们不需要使用abstract关键字
	  * 不过在声明那些未实现方法的抽象类时，必须使用abstract关键字
	  *
	  * @param state
	  */
	def receiveUpdate(state: State):Unit
}

//那些需要向观察者发送通知的主体应该使用该trait
trait Subject[State] {
	//一组待通知的观察者列表。由于该列表为可变列表，因此非线程安全。
	private var observers: List[Observer[State]] = Nil

	//用于添加观察者的方法
	def addObserver(observer: Observer[State]): Unit =
		observers ::= observer

	//把observer对象安放在observers列表的最前面，并将尊重成的列表赋给observers列表
	def notifyObservers(state: State): Unit=
	//向观察者通知状态变更
		observers foreach (_.receiveUpdate(state))
}

