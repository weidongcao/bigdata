/**
  * 5.1 隐式参数
  * 隐式对象本身不具有任何参数，除非该参数同样被标示为隐式参数
  * @param amount
  * @param rate
  * @return
  */
//永远不要用Float类型表示货币
def calcTax(amount: Float)(implicit rate: Float) : Float = amount * rate

object SimpleStateSalesTax{
	implicit val rate : Float = 0.05F
}

case class ComplicatedSalesTaxData(
      baseRate: Float,
      isTaxHoliday: Boolean,
      storeId: Int
) 

object ComplicatedSalesTax{
	private def extraTaxRateForStore(id: Int): Float = {
		//可以通过ID推断出商铺所在地，之后再计算附加税
		0.0F
	}

	implicit def rate(implicit cstd: ComplicatedSalesTaxData): Float =
		if (cstd.isTaxHoliday) 0.0F
		else cstd.baseRate + extraTaxRateForStore(cstd.storeId)
}

{
	import SimpleStateSalesTax.rate
	val amount = 100F
	println(s"Tax on $amount = ${calcTax(amount)}")
}

{
	import ComplicatedSalesTax.rate
	implicit val myStore = ComplicatedSalesTaxData(0.06F, false, 1010)

	val amount = 100F
	 println(s"Tax on $amount = ${calcTax(amount)}")
}