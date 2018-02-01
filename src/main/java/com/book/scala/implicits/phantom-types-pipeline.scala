/**
  * Created by Cao Wei Dong on 2018-02-01.
  */
package com.book.scala.implicits

import scala.language.implicitConversions

object Pipeline{
	implicit class toPiped[V](value: V){
		def |> [R] (f: V => R) = f(value)
	}
}
object CalculatePayroll2{
	def main(args: Array[String]): Unit = {
		import Pipeline._
		import Payroll._

		val e = Employee("Buck Trends", 100000.0F, 0.25F, 200F, 0.10F, 0.05F)

		val pay = start(e)  |>
			minus401k       |>
			minusInsurance  |>
			minusTax        |>
			minusFinalDeductions

		val twoWeekGross = e.annualSalary /26.0F

		val twoWeekNet = pay.netPay
		val percent = (twoWeekNet / twoWeekGross) * 100
		println(s"For ${e.name}, The Gross vs. net pay every 2 week is:")
		println(f"$$${twoWeekGross}%.2f vs. $$${twoWeekNet}.2f or ${percent}%.1f%%")
	}
}

