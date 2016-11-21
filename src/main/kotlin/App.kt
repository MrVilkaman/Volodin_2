import graph.DatasetHolder
import graph.GraphDrawer
import methods.ArrangeMethod
import methods.EmpiricalFormulas
import methods.LSMethod
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
	val arrangeMethod = ArrangeMethod(N)
	val lsMethod = LSMethod(N)

	for (i in 0..N) {

		val x: Double = x0 + dX * i

		// обычная

		val funcY = getFunc(x)
		val y: Double = funcY + getNoise(A, x, B)
		data.add(Pair(x, y))

		arrangeMethod.add(i, x, y)
		lsMethod.add(i, x, y)
	}

	EmpiricalFormulas().findView(data,::getFunc)

	val ab = arrangeMethod.getAB()
	println(arrangeMethod)
	println("A = ${ab.first} B = ${ab.second}")

	val ab2 = lsMethod.getAB()
	println(lsMethod)
	println(" A = ${ab2.first} B = ${ab2.second}")


	val elements = DatasetHolder("f(x)", data, Color.red, false)
	val elements2 = datasetLine(A, B, "AB", Color.blue)
	val elements3 = datasetLine(ab.first, ab.second, "AB Calc", Color.GREEN)
	val elements4 = datasetLine(ab2.first, ab2.second, "AB Calc 2", Color.orange)
//	GraphDrawer.draw(arrayListOf(elements, elements2, elements3, elements4))
}

private fun datasetLine(a: Double, b: Double, name: String, color: Color): DatasetHolder {
	val data2 = ArrayList<Pair<Double, Double>>()
	data2.add(Pair(0.0, b))
	data2.add(Pair(xMax, a * xMax + b))
	val elements2 = DatasetHolder(name, data2, color, true)
	return elements2
}

private fun getFunc(x: Double) = A * x + B

val rand: Random = Random(System.currentTimeMillis())

fun getNoise(a: Double, x: Double, b: Double): Double {
	return rand.nextGaussian() * 0.3
//	return 0.0
}

//***
//Метод Средних


