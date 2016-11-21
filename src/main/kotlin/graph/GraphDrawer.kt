package graph

import java.awt.Color

import org.jfree.chart.ChartFactory
import org.jfree.chart.ChartPanel
import org.jfree.chart.JFreeChart
import org.jfree.chart.axis.NumberAxis
import org.jfree.chart.plot.PlotOrientation
import org.jfree.chart.plot.XYPlot
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer
import org.jfree.data.xy.XYDataset
import org.jfree.data.xy.XYSeries
import org.jfree.data.xy.XYSeriesCollection
import org.jfree.ui.ApplicationFrame
import org.jfree.ui.RefineryUtilities
import org.jfree.util.ShapeUtilities
import java.awt.geom.Ellipse2D

/**
 * A simple demonstration application showing how to create a line chart using data from an
 * [XYDataset].

 */
class GraphDrawer(title: String, data: List<Pair<Double,Double>>) : ApplicationFrame(title) {

	init {
		val dataset = createDataset(data)
		val chart = createChart(dataset)
		val chartPanel = ChartPanel(chart)
		chartPanel.preferredSize = java.awt.Dimension(1200, 640)
		contentPane = chartPanel
	}

	private fun createDataset(data: List<Pair<Double,Double>>): XYDataset {

		val series1 = XYSeries("First")
		for (p in data) {
			series1.add(p.first, p.second)
		}


		val dataset = XYSeriesCollection()
		dataset.addSeries(series1)
		return dataset
	}

	private fun createChart(dataset: XYDataset): JFreeChart {

		// create the chart...
		val chart = ChartFactory.createXYLineChart(
			"Line Chart Demo 6", // chart title
			"X", // x axis label
			"Y", // y axis label
			dataset, // data
			PlotOrientation.VERTICAL,
			true, // include legend
			true, // tooltips
			false                     // urls
		)

		// NOW DO SOME OPTIONAL CUSTOMISATION OF THE CHART...
		chart.backgroundPaint = Color.white

		//        final StandardLegend legend = (StandardLegend) chart.getLegend();
		//      legend.setDisplaySeriesShapes(true);

		// get a reference to the plot for further customisation...
		val plot = chart.xyPlot




		plot.backgroundPaint = Color.lightGray
		//    plot.setAxisOffset(new Spacer(Spacer.ABSOLUTE, 5.0, 5.0, 5.0, 5.0));
		plot.domainGridlinePaint = Color.white
		plot.rangeGridlinePaint = Color.white

		val renderer = XYLineAndShapeRenderer()
		//		renderer.setSeriesLinesVisible(0, false);
		//		renderer.setSeriesShapesVisible(1, false);
		val size = 3.0
		val shape = Ellipse2D.Double(-size/2, -size/2, size, size)
		renderer.baseShape = shape
		renderer.basePaint = Color.red
//		renderer.setLinesVisible(false)
		renderer.setSeriesShape(0,shape)
		plot.renderer = renderer

		// change the auto tick unit selection to integer units only...
		val rangeAxis = plot.rangeAxis as NumberAxis
		rangeAxis.standardTickUnits = NumberAxis.createIntegerTickUnits()
		// OPTIONAL CUSTOMISATION COMPLETED.

		return chart
	}

	companion object {

		fun draw(data: List<Pair<Double,Double>>) {
			val demo = GraphDrawer("Line Chart Demo 6", data)
			demo.pack()
			RefineryUtilities.centerFrameOnScreen(demo)
			demo.isVisible = true
		}
	}

}