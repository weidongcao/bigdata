
case class Address(city: String, state: String, zip: String)

case class Employee(name: String, salary: Double, address: Address)

abstract class Payroll{
	def netPay(employee: Employee): Double = {
		val fedTaxes = calcFedTaxes(employee.salary)
		val stateTaxe = calcStateTaxes(employee.salary, employee.address)
		employee.salary -fedTaxes - stateTaxe
	}

	def calcFedTaxes(salary: Double):Double
	def calcStateTaxes(salary: Double, address: Address):Double
}

object Payroll2014 extends Payroll{
	val stateRate = Map(
		"XXX" -> 0.05,
		"YYY" -> 0.03,
		"ZZZ" -> 0.0
	)

	def calcFedTaxes(salary:Double):Double = salary * 0.25
	def calcStateTaxes(salary: Double, address: Address): Double = {
		salary * stateRate(address.state)
	}
}
val tom = Employee("Tome", 10000.0, Address("MyTown", "XXX", "12345"))
val jane = Employee("Jane", 11000.0, Address("BigCity", "YYY", "67890"))

Payroll2014.netPay(tom)
Payroll2014.netPay(jane)