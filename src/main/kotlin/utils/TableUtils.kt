package utils

import format
import java.util.*

/**
 * Created by Zahar on 22.10.16.
 */

class TableUtils {
	companion object {
		private val count = 3
		private val s = "| %${count}s | %${count*2}s |"

		fun printTable(tableName: String, items: ArrayList<Pair<Int, Double>>) {

			println(tableName)
			println(s.format("№", "E"))

			items.forEachIndexed { i, pair ->
				println(s.format(pair.first, pair.second.format(3)))

			}
			println()
		}

		private fun printRow(x: Int, ds: Int) {
		}
	}
}