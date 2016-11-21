import graph.GraphDrawer
import java.util.*

fun main(args: Array<String>) {

	val xMax = 10.0
	val N = 25
	val x0 = 0

	val dX = (xMax - x0) / N

	val A = 0.3
	val B = 0.5

	val data = ArrayList<Pair<Double, Double>>()
	for (i in 0..N) {

		val x: Double = x0 + dX * i

		// обычная

		val y: Double = A * x + B
		data.add(Pair(x,y))
	}
	GraphDrawer.draw(data)

}
