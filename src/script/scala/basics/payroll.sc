/**
  * 6.10 组合器：软件最佳组件抽象
  * @param name
  * @param title
  * @param annualSalary
  * @param taxRate
  * @param insurancePremiumsPerWeek
  */
//一个简化的工资单计算器
case class Employee(
                   name: String,
                   title: String,
                   annualSalary: Double,
                   taxRate: Double,
                   insurancePremiumsPerWeek: Double
                   )

val employees = List(
	Employee("Buck Trends", "CEO", 200000, 0.25, 100.0),
	Employee("Cindy Banks", "CFO", 170000, 0.22, 120.0),
	Employee("Joe Coder", "Developer", 130000, 0.20, 120.0)
)

//计算每周工资单
val netPay = employees map { e =>
	val net = (1.0 - e.taxRate) * (e.annualSalary / 52.0) - e.insurancePremiumsPerWeek
	(e, net)
}

//"打印"工资单
print("** Paychecks:")
netPay foreach {
	case (e, net) => println(f"  ${e.name + ':'}%-16s ${net}%10.2f")
}

//生成报表
val report = (netPay foldLeft (0.0, 0.0, 0.0)) {
	case ((totalSalary, totalNet, totalInsurance), (e, net)) =>
		(totalSalary + e.annualSalary/52.0, totalNet + net, totalInsurance + e.insurancePremiumsPerWeek)
}

print("\n** Report:")
println(f"  Total Salary:     ${report._1}%10.2f")
println(f"  Total net:        ${report._2}%10.2f")
println(f"  Total Insurance:  ${report._3}%10.2f")
