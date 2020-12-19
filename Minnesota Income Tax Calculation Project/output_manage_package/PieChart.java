package output_manage_package;

import java.awt.Dialog.ModalExclusionType;
import java.text.DecimalFormat;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.PieSectionLabelGenerator;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;

import data_manage_package.Database;
import data_manage_package.Taxpayer;

public class PieChart {
	
	private static PieChart pieChart;
	
	private static DefaultPieDataset receiptPieChartDataset;
	private static JFreeChart receiptPieJFreeChart;
	private static PiePlot piePlot;
	private static ChartFrame receiptPieChartFrame;
	
	private PieChart() {
		
	}
	
	public static PieChart getPieChart() {
		if (pieChart == null) {
			pieChart = new PieChart();
		}
		return pieChart;
	}
	
	public DefaultPieDataset getReceiptPieChartDataset() {
		return receiptPieChartDataset;
	}
	
	public JFreeChart getReceiptPieJFreeChart() {
		return receiptPieJFreeChart;
	}

	public PiePlot getPiePlot() {
		return piePlot;
	}

	public ChartFrame getReceiptPieChartFrame() {
		return receiptPieChartFrame;
	}
	
	
	public void createTaxpayerReceiptsPieJFreeChart(int taxpayerIndex){
		receiptPieChartDataset = new DefaultPieDataset();
		Taxpayer taxpayer = Database.getDatabase().getTaxpayerFromArrayList(taxpayerIndex);
		
		receiptPieChartDataset.setValue("Basic", taxpayer.getKindOfReceiptsTotalAmount("Basic"));
		receiptPieChartDataset.setValue("Entertainment", taxpayer.getKindOfReceiptsTotalAmount("Entertainment"));
		receiptPieChartDataset.setValue("Travel", taxpayer.getKindOfReceiptsTotalAmount("Travel"));
		receiptPieChartDataset.setValue("Health", taxpayer.getKindOfReceiptsTotalAmount("Health"));
		receiptPieChartDataset.setValue("Other", taxpayer.getKindOfReceiptsTotalAmount("Other"));
		
		receiptPieJFreeChart = ChartFactory.createPieChart("Receipt Pie Chart", receiptPieChartDataset);
		piePlot = (PiePlot)receiptPieJFreeChart.getPlot();
		PieSectionLabelGenerator generator = new StandardPieSectionLabelGenerator("{0}: {1}$ ({2})", new DecimalFormat("0.00"), new DecimalFormat("0.00%"));
		piePlot.setLabelGenerator(generator); 

		receiptPieChartFrame = new ChartFrame(Database.getDatabase().getTaxpayerNameAfmValuesPairList(taxpayerIndex), receiptPieJFreeChart);
		receiptPieChartFrame.pack();
		receiptPieChartFrame.setResizable(false);
		receiptPieChartFrame.setLocationRelativeTo(null);
		receiptPieChartFrame.setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
		receiptPieChartFrame.setVisible(true);
	}
}
