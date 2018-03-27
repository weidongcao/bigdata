package com.book.scala.basicoop2

/**
  * Created by Cao Wei Dong on 2018-03-16.
  */
class PersonEmployeeTraits {

}

case class Address(street: String, city: String, state: String, zip: String)

object Address {
	def apply(zip: String) = new Address("[Unknown]", Address.zipToCity(zip), Address.zipToState(zip), zip)

	def zipToCity(zip: String) = "Anytown"

	def zipToState(zip: String) = "CA"
}

trait PersonState {
	val name: String
	val age: Option[Int]
	val address: Option[Address]
}

case class Person(
	                 name: String,
	                 age: Option[Int] = None,
	                 address: Option[Address] = None
                 ) extends PersonState

trait EmployeeState {
	val title: String
	val manager: Option[Employee]
}

case class Employee(
	                   name: String,
	                   age: Option[Int] = None,
	                   address: Option[Address] = None,
	                   title: String = "[Unknown]",
	                   manager: Option[Employee] = None
                   ) extends PersonState with EmployeeState
