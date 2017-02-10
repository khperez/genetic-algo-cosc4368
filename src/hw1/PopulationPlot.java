package hw1;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.ui.ApplicationFrame;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

@SuppressWarnings("serial")
public class PopulationPlot extends ApplicationFrame
{
	public PopulationPlot(String applicationTitle, String chartTitle, int[] popCount)
	{
		super(applicationTitle);
		JFreeChart barGraph = ChartFactory.createBarChart3D(
			chartTitle,"Run Number", "Number of Generations", createDataset(popCount), PlotOrientation.VERTICAL, true,true,false);
         
		ChartPanel chartPanel = new ChartPanel(barGraph);
		chartPanel.setPreferredSize( new java.awt.Dimension(500 , 300));
		setContentPane(chartPanel);
	}

	public PopulationPlot(String applicationTitle, String chartTitle, double[] popPco, int[] popCount)
	{
		super(applicationTitle);
		JFreeChart barGraph = ChartFactory.createBarChart3D(
			chartTitle,"PCO", "Number of Generations", createDataset2(popPco, popCount), PlotOrientation.VERTICAL, true,true,false);
         
		ChartPanel chartPanel = new ChartPanel(barGraph);
		chartPanel.setPreferredSize( new java.awt.Dimension(500 , 300));
		setContentPane(chartPanel);
	}
	

	public DefaultCategoryDataset createDataset(int[] popCount)
	{
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		int j = 0;
		for(int i = 0 ; i < popCount.length ; i++)
		{
			j++;
			dataset.addValue(popCount[i], "Generations", Integer.toString(j));
		}
		return dataset;
	}
	
	public DefaultCategoryDataset createDataset2(double[] popPco, int[] popCount)
	{
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		for(int i = 0 ; i < popPco.length ; i++)
		{
			dataset.addValue(popCount[i], "Chromosome", String.valueOf(popPco[i]));
		}
		return dataset;
	}
}