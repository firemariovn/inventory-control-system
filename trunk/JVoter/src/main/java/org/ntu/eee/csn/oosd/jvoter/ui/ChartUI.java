package org.ntu.eee.csn.oosd.jvoter.ui;

import java.awt.Font;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;

/**
 * This is the chart component for presentation of vote results.
 * @author WangDing
 *
 */
@SuppressWarnings("serial")
public class ChartUI extends ApplicationFrame {

	public ChartUI(String title) {
		super(title);
		this.setContentPane(createPanel());
	}

	private static CategoryDataset createDataSet() {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();

		dataset.setValue(1111, "Nokia", "Nokia");
		dataset.setValue(1222, "iPhone", "iPhone");
		dataset.setValue(2000, "BlackBerry", "BlackBerry");
		dataset.setValue(3000, "Android", "Android");
		return dataset;
	}

	private static JFreeChart createJFreeChart(CategoryDataset dataset) {
		/**
		 * Construct JFreeChart
		 */
	
		JFreeChart jfreeChart = ChartFactory.createBarChart3D("What Phone do you want? ",
				"Distribution", "User Number", dataset, PlotOrientation.VERTICAL, true, false,
				false);
		/**
		 * Set JFreeChart's Properties
		 */
		jfreeChart.setTitle(new TextTitle("What Phone do you want? ", new Font("Arial", Font.BOLD
				+ Font.ITALIC, 20)));
		CategoryPlot plot = (CategoryPlot) jfreeChart.getPlot();
		CategoryAxis categoryAxis = plot.getDomainAxis();
		categoryAxis.setLabelFont(new Font("Arial", Font.ROMAN_BASELINE, 12));
		return jfreeChart;
	}

	public static JPanel createPanel() {
		JFreeChart chart = createJFreeChart(createDataSet());
		return new ChartPanel(chart);
	}

	public static void main(String[] args) {

		ChartUI chart = new ChartUI("What Phone do you want?");
		chart.pack();
		chart.setVisible(true);
	}
}
