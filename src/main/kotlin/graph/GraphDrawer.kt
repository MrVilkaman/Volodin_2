package graph

import org.jfree.chart.ChartFactory
import org.jfree.chart.ChartPanel
import org.jfree.chart.JFreeChart
import org.jfree.chart.axis.NumberAxis
import org.jfree.chart.plot.PlotOrientation
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer
import org.jfree.data.xy.XYDataset
import org.jfree.data.xy.XYSeries
import org.jfree.data.xy.XYSeriesCollection
import org.jfree.ui.ApplicationFrame
import org.jfree.ui.RefineryUtilities
import java.awt.Color
import java.awt.GradientPaint
import java.awt.geom.Ellipse2D

/**
 * A simple demonstration application showing how to create a line chart using data from an
 * [XYDataset].

 */
class GraphDrawer(title: String, data: List<DatasetHolder>) : ApplicationFrame(title) {

	init {
		val chart = createChart(data)
		val chartPanel = ChartPanel(chart)
		chartPanel.preferredSize = java.awt.Dimension(1200, 640)
		contentPane = chartPanel
	}

	private fun createChart(data: List<DatasetHolder>): JFreeChart {

		val dataset = XYSeriesCollection()
		data.forEachIndexed { i, datasetHolder ->
			val series1 = XYSeries(datasetHolder.name)
			for (p in datasetHolder.data) {
			series1.add(p.first, p.second)
		}
		dataset.addSeries(series1)
	}

		// create the chart...
		val chart = ChartFactory.createXYLineChart(
			"", // chart title
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

//		plot.setDataset()


		plot.backgroundPaint = Color.white
		//    plot.setAxisOffset(new Spacer(Spacer.ABSOLUTE, 5.0, 5.0, 5.0, 5.0));
		plot.domainGridlinePaint = Color.white
		plot.rangeGridlinePaint = Color.white

		val renderer = XYLineAndShapeRenderer()
		//		renderer.setSeriesLinesVisible(0, false);
		//		renderer.setSeriesShapesVisible(1, false);
		val size = 3.0
		val shape = Ellipse2D.Double(-size/2, -size/2, size, size)
//		renderer.baseShape = shape
//		renderer.basePaint = Color.red
		for (i in 0..data.size-1) {
			renderer.setSeriesShape(i, shape)
			renderer.setSeriesPaint(i, data[i].paint)
			renderer.setSeriesLinesVisible(i, data[i].line)
		}
		plot.renderer = renderer

		// change the auto tick unit selection to integer units only...
		val rangeAxis = plot.rangeAxis as NumberAxis
		rangeAxis.standardTickUnits = NumberAxis.createIntegerTickUnits()
//		// OPTIONAL CUSTOMISATION COMPLETED.
//		plot.getDomainAxis().setRange(0.00, 10.00)
//		plot.getDomainAxis().standardTickUnits = NumberAxis.createIntegerTickUnits()
//		plot.getDomainAxis().isVerticalTickLabels = true
//		plot.getRangeAxis().setRange(0.0, 5.0)
//		plot.getRangeAxis().standardTickUnits = NumberAxis.createIntegerTickUnits()


		return chart
	}

	companion object {
		fun draw(data: List<DatasetHolder>) {
			val demo = GraphDrawer("", data)
			demo.pack()
			RefineryUtilities.centerFrameOnScreen(demo)
			demo.isVisible = true
		}
	}
}

class DatasetHolder(val name: String, val data: List<Pair<Double, Double>>, val paint: Color, val line: Boolean)