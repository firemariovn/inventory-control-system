package org.ntu.eee.csn.oosd.jvoter.ui;

import java.awt.Font;
import java.util.ArrayList;
import java.util.HashMap;

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
import org.ntu.eee.csn.oosd.jvoter.model.VoteResult;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * This is the chart component for presentation of vote results.
 * @author WangDing
 *
 */
@SuppressWarnings("serial")
public class VoteResultChartUI extends ApplicationFrame {


	
	public VoteResultChartUI(VoteResult r) {
		super("Vote Title : "+r.getName());
	
		this.setContentPane(createPanel(r));
	}
	
	public void windowClosing(final WindowEvent event) { 
		 if (event.getWindow() == this) { 
		  dispose(); 
		 }
		} 

	
	private static CategoryDataset createDataSet(VoteResult r) {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        String option1 = r.getOptions().get(0);
        String option2 = r.getOptions().get(1);
        String option3 = r.getOptions().get(2);
        String option4 = r.getOptions().get(3);
		dataset.setValue(r.getResult().get(0), option1, option1);
		dataset.setValue(r.getResult().get(1), option2, option2);
		dataset.setValue(r.getResult().get(2), option3, option3);
		dataset.setValue(r.getResult().get(3), option4, option4);
		return dataset;
	}

	private static JFreeChart createJFreeChart(CategoryDataset dataset) {
		/**
		 * Construct JFreeChart
		 */
	
		JFreeChart jfreeChart = ChartFactory.createBarChart3D("What Phone do you want? ",
				"Distribution", "Quantity", dataset, PlotOrientation.VERTICAL, true, false,
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

	public static JPanel createPanel(VoteResult r) {
		JFreeChart chart = createJFreeChart(createDataSet(r));
		return new ChartPanel(chart);
	}

	public static void main(String[] args) {
        //Construct an instance of VoteResult
		VoteResult r = new VoteResult();
		r.setName("What Phone do you want?");
		ArrayList<String> options = new ArrayList<String>();
		options.add("Nokia");
		options.add("iPhone");
		options.add("BlackBerry");
		options.add("Adnroid");
		ArrayList<Integer> result =new ArrayList<Integer>();
		r.setOptions(options);
		result.add(200);
		result.add(300);
		result.add(400);
		result.add(900);
		r.setResult(result);
		
		//Pass an instance of VoteResult to the constructor
		VoteResultChartUI chart = new VoteResultChartUI(r);
		chart.pack();
		chart.setVisible(true);

	}
}
