package outputManagePackage;

import java.awt.Dialog.ModalExclusionType;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import dataManagePackage.Database;
import dataManagePackage.Taxpayer;

public class BarChart {
	
	private static BarChart barChart = null;
	
	private BarChart() {
		
	}
	
	public static BarChart getBarChart() {
		if(barChart == null) {
			barChart = new BarChart();
		}
		return barChart;
	}
	
	private static DefaultCategoryDataset taxAnalysisBarChartDataset;

	
	public DefaultCategoryDataset getDefaultCategoryDataset() {
		return taxAnalysisBarChartDataset;
	}
	
	
	public void createTaxpayerTaxAnalysisBarJFreeChart(int taxpayerIndex){
		taxAnalysisBarChartDataset = new DefaultCategoryDataset();
		Taxpayer taxpayer = Database.getDatabase().getTaxpayerFromArrayList(taxpayerIndex);
		
		String taxVariationType = taxpayer.getTaxInxrease()!=0? "Tax Increase" : "Tax Decrease";
		double taxVariationAmount = taxpayer.getTaxInxrease()!=0? taxpayer.getTaxInxrease() : taxpayer.getTaxDecrease()*(-1);
		
		taxAnalysisBarChartDataset.setValue(taxpayer.getBasicTax(), "Tax", "Basic Tax");
		taxAnalysisBarChartDataset.setValue(taxVariationAmount, "Tax", taxVariationType);
		taxAnalysisBarChartDataset.setValue(taxpayer.getTotalTax(), "Tax", "Total Tax");

		JFreeChart taxAnalysisJFreeChart = ChartFactory.createBarChart("Tax Analysis Bar Chart", "",  "Tax Analysis in $", taxAnalysisBarChartDataset, PlotOrientation.VERTICAL, true, true, false);

		ChartFrame receiptPieChartFrame = new ChartFrame(Database.getDatabase().getTaxpayerNameAfmValuesPairList(taxpayerIndex), taxAnalysisJFreeChart);
		receiptPieChartFrame.pack();
		receiptPieChartFrame.setResizable(false);
		receiptPieChartFrame.setLocationRelativeTo(null);
		receiptPieChartFrame.setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
		receiptPieChartFrame.setVisible(true);
	}
}
