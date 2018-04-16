/**
  * Created by Cao Wei Dong on 2018-03-28.
  */
package com.book.scala.selftype

case class Button(label: String){
	def click(): Unit = {}
}
object ButtonSubjectObserver extends SubjectObserver {
	type S = ObservableButton
	type O = ButtonObserver

	class ObservableButton(label: String) extends Button(label) with Subject{
		override def click() = {
			super.click()
			notifyObservers()
		}
	}

	trait ButtonObserver extends Observer{
		override def receiveUpdate(subject: ObservableButton)
	}
}

import ButtonSubjectObserver._

import scala.collection.mutable

class ButtonClickObserver extends ButtonObserver{
	val clicks = new mutable.HashMap[String, Int]()

	override def receiveUpdate(subject: ObservableButton): Unit = {
		val count = clicks.getOrElse(subject.label, 0) + 1
		clicks.update(subject.label, count)
	}
}

