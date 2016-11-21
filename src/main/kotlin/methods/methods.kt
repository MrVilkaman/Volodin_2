package methods

/**
 * Created by Zahar on 21.11.16.
 */

class ArrangeMethod(val n: Int) {

	private var k: Int

	init {
		k = n / 2
	}

	var X1: Double = 0.0
	var X2: Double = 0.0
	var Y1: Double = 0.0
	var Y2: Double = 0.0

	fun add(i: Int, x: Double, y: Double) {
		if (i < k) {
			X1 += x
			Y1 += y
		} else {
			X2 += x
			Y2 += y
		}

	}

	fun getAB(): Pair<Double, Double> {
		val calcB = (X2 * Y1 - X1 * Y2) / (k * X2 - X1 * (n - k - 1))
		val calcA = (Y1 - k * calcB) / X1
		return Pair(calcA, calcB)
	}

	override fun toString(): String {
		return "Метод средних"
	}
}

class LSMethod(val n: Int) {

	var X1: Double = 0.0
	var Y1: Double = 0.0
	var XY: Double = 0.0
	var XX: Double = 0.0

	fun add(i: Int, x: Double, y: Double) {
		X1 += x
		Y1 += y
		XY += x * y
		XX += x * x
	}

	fun getAB(): Pair<Double, Double> {
		val calcA = (n * XY - Y1 * X1) / (n * XX - X1 * X1)
		val calcB = (Y1 - X1 * calcA) / n
		return Pair(calcA, calcB)
	}


	override fun toString(): String {
		return "Метод наименьших квадратов"
	}
}