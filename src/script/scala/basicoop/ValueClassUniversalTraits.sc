trait Digitizer extends Any{
	def digits(s: String): String = s.replaceAll("""\D""", "")
}

trait Formatter extends Any{
	def format(areaCode: String, exchange: String, subnumber: String): String =
		s"($areaCode) $exchange-$subnumber"
}

class USPhoneNumber(val s: String) extends AnyVal
	with Digitizer with Formatter{

	override def toString: String = {
		val digs = digits(s)
		val areaCode  = digs.substring(0,3)
		val exchange = digs.substring(3,6)
		val subnumber = digs.substring(6,10)
		format(areaCode, exchange, subnumber)
	}
}

val number = new USPhoneNumber("987-654-3210")