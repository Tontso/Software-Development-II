package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import dataManagePackage.Database;
import dataManagePackage.Taxpayer;
import dataManagePackage.Receipt.Receipt;
import inputManagePackage.InputSystem;
import outputManagePackage.OutputSystem;


@TestMethodOrder(OrderAnnotation.class)
class OutputSystemTest {
	
	Database database = Database.getDatabase();
	

	@Test
	@Order(1)
	void testSaveUpdatedTaxpayerTxtInputFile() {

		Receipt testReceipt5 = new Receipt("Other", "5", "3/5/2014", "600", "Toys Store", "Greece", "Ioannina", "Omhrou", "5");

		List<String> myTestList = new ArrayList<>();
		myTestList.add("130456093_INFO.txt");
		InputSystem.getInputSystem().addTaxpayersDataFromFilesIntoDatabase("test/testsOutputFiles", myTestList);
		
		database.getTaxpayerFromArrayList(0).removeReceiptFromList(4);
		
		OutputSystem.getOutputSystem().saveUpdatedTaxpayerTxtInputFile("test//testsOutputFiles//130456093_INFO.txt", 0);
		
		Scanner inputStream = null;
		try	{
			inputStream = new Scanner(new FileInputStream("test//testsOutputFiles//130456093_INFO.txt"));
		}catch(FileNotFoundException e){
			System.out.println("Problem opening file.");
			System.exit(0);
		}
		
		assertEquals("Name: Apostolos Zarras", inputStream.nextLine());
		assertEquals("AFM: 130456093", inputStream.nextLine());
		assertEquals("Status: married filing jointly", inputStream.nextLine());
		assertEquals("Income: 22570.0", inputStream.nextLine());
		assertEquals("", inputStream.nextLine());
		assertEquals("Receipts:", inputStream.nextLine());
		assertEquals("", inputStream.nextLine());
		assertEquals("Receipt ID: 1", inputStream.nextLine());
		assertEquals("Date: 25/2/2014", inputStream.nextLine());
		assertEquals("Kind: Basic", inputStream.nextLine());
		assertEquals("Amount: 2000.0", inputStream.nextLine());
		assertEquals("Company: Hand Made Clothes", inputStream.nextLine());
		assertEquals("Country: Greece", inputStream.nextLine());
		assertEquals("City: Ioannina", inputStream.nextLine());
		assertEquals("Street: Kaloudi ", inputStream.nextLine());
		assertEquals("Number: 10", inputStream.nextLine());
		assertEquals("", inputStream.nextLine());
		assertEquals("Receipt ID: 2", inputStream.nextLine());
		assertEquals("Date: 28/2/2014", inputStream.nextLine());
		assertEquals("Kind: Entertainment", inputStream.nextLine());
		assertEquals("Amount: 500.0", inputStream.nextLine());
		assertEquals("Company: Floca Cafe", inputStream.nextLine());
		assertEquals("Country: Greece", inputStream.nextLine());
		assertEquals("City: Ioannina", inputStream.nextLine());
		assertEquals("Street: Kavafi", inputStream.nextLine());
		assertEquals("Number: 4", inputStream.nextLine());
		assertEquals("", inputStream.nextLine());
		assertEquals("Receipt ID: 3", inputStream.nextLine());
		assertEquals("Date: 10/3/2014", inputStream.nextLine());
		assertEquals("Kind: Health", inputStream.nextLine());
		assertEquals("Amount: 500.0", inputStream.nextLine());
		assertEquals("Company: Dentist", inputStream.nextLine());
		assertEquals("Country: Greece", inputStream.nextLine());
		assertEquals("City: Ioannina", inputStream.nextLine());
		assertEquals("Street: Lamprou", inputStream.nextLine());
		assertEquals("Number: 20", inputStream.nextLine());
		assertEquals("", inputStream.nextLine());
		assertEquals("Receipt ID: 4", inputStream.nextLine());
		assertEquals("Date: 3/5/2014", inputStream.nextLine());
		assertEquals("Kind: Other", inputStream.nextLine());
		assertEquals("Amount: 900.0", inputStream.nextLine());
		assertEquals("Company: Public", inputStream.nextLine());
		assertEquals("Country: Greece", inputStream.nextLine());
		assertEquals("City: Ioannina", inputStream.nextLine());
		assertEquals("Street: Vlahlidou", inputStream.nextLine());
		assertEquals("Number: 20", inputStream.nextLine());
		
		
		//RESET TEXTFILE FOR NEXT TESTS
		database.getTaxpayerFromArrayList(0).addReceiptToList(testReceipt5);
		OutputSystem.getOutputSystem().saveUpdatedTaxpayerTxtInputFile("test//testsOutputFiles//130456093_INFO.txt", 0);
		
		database.removeTaxpayerFromArrayList(0);
		
		
	}

	@Test
	@Order(2)
	void testSaveUpdatedTaxpayerXmlInputFile() {
//		Taxpayer expectedTaxpayer = new Taxpayer("Nikos Zisis", "130456094", "head of household", "40000");
//		Receipt testReceipt = new Receipt("Other", "1", "25/2/2014", "2000", "Omega Watches", "Greece", "Ioannina", "Kaloudi ", "4");
//		Receipt testReceipt2 = new Receipt("Entertainment", "2", "10/5/2014", "2000", "Hotel Du Lac", "Greece", "Ioannina", "Lake", "89");
		Receipt testReceipt3 = new Receipt("Basic", "3", "5/6/2014", "4000", "IKEA", "Greece", "Ioannina", "Averof", "100 ");
//		expectedTaxpayer.addReceiptToList(testReceipt);
//		expectedTaxpayer.addReceiptToList(testReceipt2);
//		expectedTaxpayer.addReceiptToList(testReceipt3);
		
		List<String> myTestList = new ArrayList<>();
		myTestList.add("130456094_INFO.xml");
		
		InputSystem.getInputSystem().addTaxpayersDataFromFilesIntoDatabase("test/testsOutputFiles", myTestList);		
		
		database.getTaxpayerFromArrayList(0).removeReceiptFromList(2);
		
		OutputSystem.getOutputSystem().saveUpdatedTaxpayerXmlInputFile("test//testsOutputFiles//130456094_INFO.xml", 0);
		
		Scanner inputStream = null;
		try	{
			inputStream = new Scanner(new FileInputStream("test//testsOutputFiles//130456094_INFO.xml"));
		}catch(FileNotFoundException e){
			System.out.println("Problem opening file.");
			System.exit(0);
		}
		assertEquals("<Name> Nikos Zisis </Name>", inputStream.nextLine());
		assertEquals("<AFM> 130456094 </AFM>", inputStream.nextLine());
		assertEquals("<Status> single </Status>", inputStream.nextLine());
		assertEquals("<Income> 40000.0 </Income>", inputStream.nextLine());
		assertEquals("", inputStream.nextLine());
		assertEquals("<Receipts>", inputStream.nextLine());
		assertEquals("", inputStream.nextLine());
		assertEquals("<ReceiptID> 1 </ReceiptID>", inputStream.nextLine());
		assertEquals("<Date> 25/2/2014 </Date>", inputStream.nextLine());
		assertEquals("<Kind> Other </Kind>", inputStream.nextLine());
		assertEquals("<Amount> 2000.0 </Amount>", inputStream.nextLine());
		assertEquals("<Company> Omega Watches </Company>", inputStream.nextLine());
		assertEquals("<Country> Greece </Country>", inputStream.nextLine());
		assertEquals("<City> Ioannina </City>", inputStream.nextLine());
		assertEquals("<Street> Kaloudi </Street>", inputStream.nextLine());
		assertEquals("<Number> 4 </Number>", inputStream.nextLine());
		assertEquals("", inputStream.nextLine());
		assertEquals("<ReceiptID> 2 </ReceiptID>", inputStream.nextLine());
		assertEquals("<Date> 10/5/2014 </Date>", inputStream.nextLine());
		assertEquals("<Kind> Entertainment </Kind>", inputStream.nextLine());
		assertEquals("<Amount> 2000.0 </Amount>", inputStream.nextLine());
		assertEquals("<Company> Hotel Du Lac </Company>", inputStream.nextLine());
		assertEquals("<Country> Greece </Country>", inputStream.nextLine());
		assertEquals("<City> Ioannina </City>", inputStream.nextLine());
		assertEquals("<Street> Lake </Street>", inputStream.nextLine());
		assertEquals("<Number> 89 </Number>", inputStream.nextLine());
		
		//RESET TEXTFILE FOR NEXT TESTS
		database.getTaxpayerFromArrayList(0).addReceiptToList(testReceipt3);
		OutputSystem.getOutputSystem().saveUpdatedTaxpayerXmlInputFile("test//testsOutputFiles//130456094_INFO.xml", 0);
		
		database.removeTaxpayerFromArrayList(0);
		
		
	}
	
	@Test
	@Order(3)
	void testSaveTaxpayerInfoToTxtLogFile() {
	
		Taxpayer expectedTaxpayer = new Taxpayer("Apostolos Zarras", "130456093", "married filing jointly", "22570");
		Receipt testReceipt = new Receipt("Basic", "1", "25/2/2014", "2000", "Hand Made Clothes", "Greece", "Ioannina", "Kaloudi ", "10");
		Receipt testReceipt2 = new Receipt("Entertainment", "2", "28/2/2014", "500", "Floca Cafe", "Greece", "Ioannina", "Kavafi", "4");
		Receipt testReceipt3 = new Receipt("Health", "3", "10/3/2014", "500", "Dentist", "Greece", "Ioannina", "Lamprou", "20 ");
		Receipt testReceipt4 = new Receipt("Other", "4", "3/5/2014", "900", "Public", "Greece", "Ioannina", "Vlahlidou", "20");
		expectedTaxpayer.addReceiptToList(testReceipt);
		expectedTaxpayer.addReceiptToList(testReceipt2);
		expectedTaxpayer.addReceiptToList(testReceipt3);
		expectedTaxpayer.addReceiptToList(testReceipt4);
		database.addTaxpayerToList(expectedTaxpayer);
		OutputSystem.getOutputSystem().saveTaxpayerInfoToTxtLogFile("C:\\Users\\tommy\\Desktop", 0);
		
		Scanner inputStream = null;
		try
		{
			inputStream = new Scanner(new FileInputStream("C:\\Users\\tommy\\Desktop\\130456093_LOG.txt"));
		}
		catch(FileNotFoundException e)
		{
			System.out.println("Problem opening file.");
			System.exit(0);
		}
		assertEquals("Name: Apostolos Zarras", inputStream.nextLine());
		assertEquals("AFM: 130456093", inputStream.nextLine());
		assertEquals("Income: 22570.0", inputStream.nextLine());
		assertEquals("Basic Tax: 1207.49", inputStream.nextLine());
		assertEquals("Tax Increase: 96.6", inputStream.nextLine());
		assertEquals("Total Tax: 1304.09", inputStream.nextLine());
		assertEquals("Total Receipts Amount: 3900.0", inputStream.nextLine());
		assertEquals("Entertainment: 500.0", inputStream.nextLine());
		assertEquals("Basic: 2000.0", inputStream.nextLine());
		assertEquals("Travel: 0.0", inputStream.nextLine());
		assertEquals("Health: 500.0", inputStream.nextLine());
		assertEquals("Other: 900.0", inputStream.nextLine());
		
		database.removeTaxpayerFromArrayList(0);
		
	}

	@Test
	@Order(4)
	void testSaveTaxpayerInfoToXmlLogFile() {
		Taxpayer expectedTaxpayer = new Taxpayer("Nikos Zisis", "130456094", "head of household", "40000");
		Receipt testReceipt = new Receipt("Other", "1", "25/2/2014", "2000", "Omega Watches", "Greece", "Ioannina", "Kaloudi ", "4");
		Receipt testReceipt2 = new Receipt("Entertainment", "2", "10/5/2014", "2000", "Hotel Du Lac", "Greece", "Ioannina", "Lake", "89");
		Receipt testReceipt3 = new Receipt("Basic", "3", "5/6/2014", "4000", "IKEA", "Greece", "Ioannina", "Averof", "100 ");
		expectedTaxpayer.addReceiptToList(testReceipt);
		expectedTaxpayer.addReceiptToList(testReceipt2);
		expectedTaxpayer.addReceiptToList(testReceipt3);
		database.addTaxpayerToList(expectedTaxpayer);
		OutputSystem.getOutputSystem().saveTaxpayerInfoToXmlLogFile("C:\\Users\\tommy\\Desktop", 0);
		
		Scanner inputStream = null;
		try
		{
			inputStream = new Scanner(new FileInputStream("C:\\Users\\tommy\\Desktop\\130456094_LOG.xml"));
		}
		catch(FileNotFoundException e)
		{
			System.out.println("Problem opening file.");
			System.exit(0);
		}
		
		assertEquals("<Name> Nikos Zisis </Name>", inputStream.nextLine());
		assertEquals("<AFM> 130456094 </AFM>", inputStream.nextLine());
		assertEquals("<Income> 40000.0 </Income>", inputStream.nextLine());
		assertEquals("<BasicTax> 2303.38 </BasicTax>", inputStream.nextLine());
		assertEquals("<TaxIncrease> 92.14 </TaxIncrease>", inputStream.nextLine());
		assertEquals("<TotalTax> 2395.51 </TotalTax>", inputStream.nextLine());
		assertEquals("<Receipts> 8000.0 </Receipts>", inputStream.nextLine());
		assertEquals("<Entertainment> 2000.0 </Entertainment>", inputStream.nextLine());
		assertEquals("<Basic> 4000.0 </Basic>", inputStream.nextLine());
		assertEquals("<Travel> 0.0 </Travel>", inputStream.nextLine());
		assertEquals("<Health> 0.0 </Health>", inputStream.nextLine());
		assertEquals("<Other> 2000.0 </Other>", inputStream.nextLine());
	}

	@Test
	@Order(5)
	void testCreateTaxpayerReceiptsPieJFreeChart() {
		database.removeTaxpayerFromArrayList(0);
		Taxpayer expectedTaxpayer = new Taxpayer("Apostolos Zarras", "130456093", "married filing jointly", "22570");
		Receipt testReceipt = new Receipt("Basic", "1", "25/2/2014", "2000", "Hand Made Clothes", "Greece", "Ioannina", "Kaloudi ", "10");
		Receipt testReceipt2 = new Receipt("Entertainment", "2", "28/2/2014", "500", "Floca Cafe", "Greece", "Ioannina", "Kavafi", "4");
		Receipt testReceipt3 = new Receipt("Health", "3", "10/3/2014", "500", "Dentist", "Greece", "Ioannina", "Lamprou", "20 ");
		Receipt testReceipt4 = new Receipt("Other", "4", "3/5/2014", "900", "Public", "Greece", "Ioannina", "Vlahlidou", "20");
		expectedTaxpayer.addReceiptToList(testReceipt);
		expectedTaxpayer.addReceiptToList(testReceipt2);
		expectedTaxpayer.addReceiptToList(testReceipt3);
		expectedTaxpayer.addReceiptToList(testReceipt4);
		database.addTaxpayerToList(expectedTaxpayer);
		
		DefaultPieDataset receiptPieChartDataset = new DefaultPieDataset();;
		receiptPieChartDataset.setValue("Basic", expectedTaxpayer.getKindOfReceiptsTotalAmount("Basic"));
		receiptPieChartDataset.setValue("Entertainment", expectedTaxpayer.getKindOfReceiptsTotalAmount("Entertainment"));
		receiptPieChartDataset.setValue("Travel", expectedTaxpayer.getKindOfReceiptsTotalAmount("Travel"));
		receiptPieChartDataset.setValue("Health", expectedTaxpayer.getKindOfReceiptsTotalAmount("Health"));
		receiptPieChartDataset.setValue("Other", expectedTaxpayer.getKindOfReceiptsTotalAmount("Other"));
		
		OutputSystem.getOutputSystem().createTaxpayerReceiptsPieJFreeChart(0);
		assertEquals(receiptPieChartDataset.getValue("Basic"), OutputSystem.getOutputSystem().getReceiptPieChartDataset().getValue("Basic"));
		assertEquals(receiptPieChartDataset.getValue("Entertainment"), OutputSystem.getOutputSystem().getReceiptPieChartDataset().getValue("Entertainment"));
		assertEquals(receiptPieChartDataset.getValue("Travel"), OutputSystem.getOutputSystem().getReceiptPieChartDataset().getValue("Travel"));
		assertEquals(receiptPieChartDataset.getValue("Health"), OutputSystem.getOutputSystem().getReceiptPieChartDataset().getValue("Health"));
		assertEquals(receiptPieChartDataset.getValue("Other"), OutputSystem.getOutputSystem().getReceiptPieChartDataset().getValue("Other"));
		
		database.removeTaxpayerFromArrayList(0);
	}

	@Test
	@Order(6)
	void testCreateTaxpayerTaxAnalysisBarJFreeChart() {
		Taxpayer expectedTaxpayer = new Taxpayer("Apostolos Zarras", "130456093", "married filing jointly", "22570");
		Receipt testReceipt = new Receipt("Basic", "1", "25/2/2014", "2000", "Hand Made Clothes", "Greece", "Ioannina", "Kaloudi ", "10");
		Receipt testReceipt2 = new Receipt("Entertainment", "2", "28/2/2014", "500", "Floca Cafe", "Greece", "Ioannina", "Kavafi", "4");
		Receipt testReceipt3 = new Receipt("Health", "3", "10/3/2014", "500", "Dentist", "Greece", "Ioannina", "Lamprou", "20 ");
		Receipt testReceipt4 = new Receipt("Other", "4", "3/5/2014", "900", "Public", "Greece", "Ioannina", "Vlahlidou", "20");
		Receipt testReceipt5 = new Receipt("Other", "5", "3/5/2014", "600", "Toys Store", "Greece", "Ioannina", "Omhrou", "5");
		expectedTaxpayer.addReceiptToList(testReceipt);
		expectedTaxpayer.addReceiptToList(testReceipt2);
		expectedTaxpayer.addReceiptToList(testReceipt3);
		expectedTaxpayer.addReceiptToList(testReceipt4);
		expectedTaxpayer.addReceiptToList(testReceipt5);
		database.addTaxpayerToList(expectedTaxpayer);
		
		DefaultCategoryDataset taxAnalysisBarChartDataset = new DefaultCategoryDataset();
		Taxpayer taxpayer = database.getTaxpayerFromArrayList(0);
		
		String taxVariationType = taxpayer.getTaxInxrease()!=0? "Tax Increase" : "Tax Decrease";
		double taxVariationAmount = taxpayer.getTaxInxrease()!=0? taxpayer.getTaxInxrease() : taxpayer.getTaxDecrease()*(-1);
		
		taxAnalysisBarChartDataset.setValue(taxpayer.getBasicTax(), "Tax", "Basic Tax");
		taxAnalysisBarChartDataset.setValue(taxVariationAmount, "Tax", taxVariationType);
		taxAnalysisBarChartDataset.setValue(taxpayer.getTotalTax(), "Tax", "Total Tax");

		JFreeChart taxAnalysisJFreeChart = ChartFactory.createBarChart("Tax Analysis Bar Chart", "",  "Tax Analysis in $", taxAnalysisBarChartDataset, PlotOrientation.VERTICAL, true, true, false);

		ChartFrame receiptPieChartFrame = new ChartFrame(database.getTaxpayerNameAfmValuesPairList(0), taxAnalysisJFreeChart);
		
		OutputSystem.getOutputSystem().createTaxpayerTaxAnalysisBarJFreeChart(0);
		
		assertEquals(taxAnalysisBarChartDataset.getValue("Tax", "Basic Tax"), OutputSystem.getOutputSystem().getDefaultCategoryDataset().getValue("Tax", "Basic Tax"));
		assertEquals(taxAnalysisBarChartDataset.getValue("Tax", "Total Tax"), OutputSystem.getOutputSystem().getDefaultCategoryDataset().getValue("Tax", "Total Tax"));
		assertEquals(taxAnalysisBarChartDataset.getValue("Tax", taxVariationType), OutputSystem.getOutputSystem().getDefaultCategoryDataset().getValue("Tax", taxVariationType));

		
		
		
	}

}
