package utils

import format
import methods.EmpiricalFormulas
import java.util.*

/**
 * Created by Zahar on 22.10.16.
 */

class TableUtils {
	companion object {
		private val count = 3
		private val s = "| %${count}s | %${count*2}s |"
		private val sv = s+" %s"

		fun printTable(tableName: String, items: ArrayList<Pair<Int, Double>>) {

			println(tableName)
			println(s.format("№", "E"))

			items.forEachIndexed { i, pair ->
				println(sv.format(pair.first, pair.second.format(3), EmpiricalFormulas.getFuncName(pair.first)))

			}
			println()
		}

		private fun printRow(x: Int, ds: Int) {
		}
	}
}