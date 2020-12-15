package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import dataManagePackage.Database;
import dataManagePackage.Taxpayer;
import dataManagePackage.Receipt.Receipt;
import inputManagePackage.InputSystem;

@TestMethodOrder(OrderAnnotation.class)
class InputSystemTest {
	 
	Database database = Database.getDatabase();

	@Test()
	@Order(1)
	void testAddTaxpayersDataFromFilesIntoDatabaseForTxt() {
		
		
		List<String> myTestList = new ArrayList<>();
		myTestList.add("130456093_INFO.txt");
		InputSystem.getInputSystem().addTaxpayersDataFromFilesIntoDatabase("test/testsInputFiles", myTestList);
		Taxpayer expectedTaxpayer = new Taxpayer("Apostolos Zarras", "130456093", "Married Filing Jointly", "22570");
		
		//Check if load right the Taxpayer
		assertEquals(expectedTaxpayer.getName(), database.getTaxpayerFromArrayList(0).getName());
		assertEquals(expectedTaxpayer.getAFM(), database.getTaxpayerFromArrayList(0).getAFM());
		assertEquals(expectedTaxpayer.getFamilyStatus(), database.getTaxpayerFromArrayList(0).getFamilyStatus());
		assertEquals(expectedTaxpayer.getIncome(), database.getTaxpayerFromArrayList(0).getIncome());
		
		//Check if load right taxpayer's receipts
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
		
		
		for(int i=0; i<5; i++) {
			
			assertEquals(expectedTaxpayer.getReceiptsArrayList().get(i).getId(), database.getTaxpayerFromArrayList(0).getReceiptsArrayList().get(i).getId());
			
			assertEquals(expectedTaxpayer.getReceiptsArrayList().get(i).getKind(), database.getTaxpayerFromArrayList(0).getReceiptsArrayList().get(i).getKind());
			
			assertEquals(expectedTaxpayer.getReceiptsArrayList().get(i).getDate(), database.getTaxpayerFromArrayList(0).getReceiptsArrayList().get(i).getDate());
			
			assertEquals(expectedTaxpayer.getReceiptsArrayList().get(i).getAmount(), database.getTaxpayerFromArrayList(0).getReceiptsArrayList().get(i).getAmount());
			
		}
	
		database.removeTaxpayerFromArrayList(0);
			
	}
	
	
	@Test()
	@Order(2)
	void testAddTaxpayersDataFromFilesIntoDatabaseForXml() {
		List<String> myTestList = new ArrayList<>();
		myTestList.add("130456094_INFO.xml");
		
		InputSystem.getInputSystem().addTaxpayersDataFromFilesIntoDatabase("test/testsInputFiles", myTestList);
		
		Taxpayer expectedTaxpayer = new Taxpayer("Nikos Zisis", "130456094", "Single", "40000");
		
		//Check if load right the Taxpayer
		assertEquals(expectedTaxpayer.getName(), database.getTaxpayerFromArrayList(0).getName());
		assertEquals(expectedTaxpayer.getAFM(), database.getTaxpayerFromArrayList(0).getAFM());
		assertEquals(expectedTaxpayer.getFamilyStatus(), database.getTaxpayerFromArrayList(0).getFamilyStatus());
		assertEquals(expectedTaxpayer.getIncome(), database.getTaxpayerFromArrayList(0).getIncome());
		
		//Check if load right taxpayer's receipts
		Receipt testReceipt = new Receipt("Other", "1", "25/2/2014", "2000", "Omega Watches", "Greece", "Ioannina", "Kaloudi ", "4");
		Receipt testReceipt2 = new Receipt("Entertainment", "2", "10/5/2014", "2000", "Hotel Du Lac", "Greece", "Ioannina", "Lake", "89");
		Receipt testReceipt3 = new Receipt("Basic", "3", "5/6/2014", "4000", "IKEA", "Greece", "Ioannina", "Averof", "100 ");
		;
		expectedTaxpayer.addReceiptToList(testReceipt);
		expectedTaxpayer.addReceiptToList(testReceipt2);
		expectedTaxpayer.addReceiptToList(testReceipt3);
		
		
		
		for(int i=0; i<3; i++) {
			
			assertEquals(expectedTaxpayer.getReceiptsArrayList().get(i).getId(), database.getTaxpayerFromArrayList(0).getReceiptsArrayList().get(i).getId());
			
			assertEquals(expectedTaxpayer.getReceiptsArrayList().get(i).getKind(), database.getTaxpayerFromArrayList(0).getReceiptsArrayList().get(i).getKind());
			
			assertEquals(expectedTaxpayer.getReceiptsArrayList().get(i).getDate(), database.getTaxpayerFromArrayList(0).getReceiptsArrayList().get(i).getDate());
			
			assertEquals(expectedTaxpayer.getReceiptsArrayList().get(i).getAmount(), database.getTaxpayerFromArrayList(0).getReceiptsArrayList().get(i).getAmount());
			
		}
	
		database.removeTaxpayerFromArrayList(0);
			
	}

}
