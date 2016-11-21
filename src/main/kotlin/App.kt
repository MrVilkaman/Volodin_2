import graph.DatasetHolder
import graph.GraphDrawer
import java.awt.Color
import java.util.*


val A = 0.3
val B = 0.5
val xMax = 10.0
val N = 250
val x0 = 0

val dX = (xMax - x0) / N

fun main(args: Array<String>) {


	val data = ArrayList<Pair<Double, Double>>()
	var X1: Double = 0.0;
	var X2: Double = 0.0;
	var Y1: Double = 0.0;
	var Y2: Double = 0.0;

	var k: Int = N / 2

	for (i in 0..N) {

		val x: Double = x0 + dX * i

		// обычная

		val funcY = getFunc(x)
		val y: Double = funcY + getNoise(A, x, B)
		data.add(Pair(x, y))

		if (i < k) {
			X1 += x
			Y1 += y
		} else {
			X2 += x
			Y2 += y
		}
	}

	val calcB = (X2*Y1 - X1*Y2)/(k*X2-X1*(N-k-1))
	val calcA = (Y1- k*calcB)/X1

	print("A = $calcA B = $calcB")


	val elements = DatasetHolder("f(x)", data, Color.red, false)

	val data2 = ArrayList<Pair<Double, Double>>()

	data2.add(Pair(0.0,B));
	data2.add(Pair(xMax,A*xMax+B));

	val elements2 = DatasetHolder("AB", data2, Color.blue, true)
	GraphDrawer.draw(arrayListOf(elements,elements2))
}

private fun getFunc(x: Double) = A * x + B

val rand: Random = Random(System.currentTimeMillis())

fun getNoise(a: Double, x: Double, b: Double): Double {
	return rand.nextGaussian()*0.3
//	return 0.0
}
