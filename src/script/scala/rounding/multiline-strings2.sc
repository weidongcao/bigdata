def goodbye(name: String) =
s"""xxxGoodbye, ${name}yyy
		|xxxCome again!yyy,
""".stripPrefix("xxx")
.stripSuffix("yyy")
.stripMargin

goodbye("dong")