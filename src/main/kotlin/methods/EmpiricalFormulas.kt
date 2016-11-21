package methods

import utils.TableUtils
import java.util.*

/**
 * Created by Zahar on 22.11.16.
 */

class EmpiricalFormulas {

	fun findView(data: List<Pair<Double, Double>>, func: (v: Double) -> Double) {
		val dd = (data.size * 0.05).toInt()
		val val1 = data[dd]
		val val2 = data[data.size - dd]

		val yArrange = getArrange(val1.second, val2.second)
		val yGeometric = getGeometric(val1.second, val2.second)
		val yHarmonic = getHarmonic(val1.second, val2.second)

		val y2Arrange = func(getArrange(val1.first, val2.first))
		val y2Geometric = func(getGeometric(val1.first, val2.first))
		val y2Harmonic = func(getHarmonic(val1.first, val2.first))

		val e1 = Math.abs(y2Arrange - yArrange)
		val e2 = Math.abs(y2Arrange - yGeometric)
		val e3 = Math.abs(y2Arrange - yHarmonic)
		val e4 = Math.abs(y2Geometric - yArrange)
		val e5 = Math.abs(y2Geometric - yGeometric)
		val e6 = Math.abs(y2Harmonic - yArrange)
		val e7 = Math.abs(y2Harmonic - yHarmonic)

		var arr = ArrayList<Pair<Int, Double>>()
		arrayListOf(e1, e2, e3, e4, e5, e6, e7).forEachIndexed { i, d -> arr.add(Pair(i + 1, d)) }

		arr.sortBy { it.second }
		TableUtils.printTable("asd", arr)
	}

	private fun getArrange(val1: Double, val2: Double): Double = (val1 + val2) / 2
	private fun getGeometric(val1: Double, val2: Double): Double = Math.sqrt(val1 * val2)
	private fun getHarmonic(val1: Double, val2: Double): Double = (2 * val1 * val2) / (val1 + val2)

	companion object {
		public fun getFuncName(number: Int): String {
			return when (number) {
				1 -> "линейная функция: у = Ах + В"
				2 -> "показательная функция: у = АВ^х "
				3 -> "дробно-рациональная функция: у = (Ах + В)^-1 "
				4 -> "логарифмическая функция: у = А^ln(х) + В"
				5 -> "смешанная функция: у = Ах^В "
				6 -> "гиперболическая функция: у = А + В/х"
				7 -> "дробно-рациональная функция: у = х/(Ах + В)"
				else -> ""
			}
		}
	}
}