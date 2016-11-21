fun main(args: Array<String>) {

	val tMax = 10.0
	val N = 400
	val t0 = 0
	val dT = (tMax - t0)/N

	var z = 1.0
	val k = 1



	val const1 = Math.sqrt(3.0)
	for (i in 0..N) {

		val t = t0+dT * i

		// обычная
		val funcX = Math.sin(t) + Math.cos(t)

		// производная
		val funcXD = Math.cos(t) - Math.sin(t)


		val zNext = (dT * (k + const1) * (funcX + z))/5 + z

		val dz = (zNext - z)/dT

//		println("$i ${t.format(3)} ${funcX.format(3)} ${funcXD.format(3)}  ${zNext.format(3)} ${dz.format(3)}")
		println("${dz.format(3)}")

		z = zNext
	}

}

fun Double.format(digits: Int) = java.lang.String.format("%.${digits}f", this)