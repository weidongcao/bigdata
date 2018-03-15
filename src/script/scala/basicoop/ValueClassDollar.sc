class Dollar(val value: Float) extends AnyVal{
	override def toString = "$%.2f".format(value)
}
val benjamin = new Dollar(100)