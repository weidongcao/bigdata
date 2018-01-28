/**
  实现阶乘的功能来测试嵌套方法的宝岛与递归
  */
def factorial(i: Int): Long ={
	def fact(i: Int, accumulator: Int): Long = {
		if (i <= 1) {
			accumulator
		} else {
			fact(i -1, i * accumulator)
		}
	}

	fact(i, 1)
}

(0 to 5) foreach(i => println(factorial(i)))