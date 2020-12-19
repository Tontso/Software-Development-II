package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import data_manage_package.Taxpayer;
import data_manage_package.receipt.Receipt;

@TestMethodOrder(OrderAnnotation.class)
class TaxpayerTest {
	
	Taxpayer taxpayer = new Taxpayer("TestName", "testAFM", "single", "13567");

	@Test
	@Order(1)
	void testCalculateTaxForMarriedFilingSeparately() {
		
		taxpayer = new Taxpayer("TestName", "testAFM", "married filing separately", "13567");
		int totalIncome;
		double tax;
		// totalIncome < 18040
		totalIncome = 17876;
		tax = taxpayer.calculateTax(totalIncome);
		assertEquals(956.366, tax);
		// 18040 <= totalIncome < 71680
		totalIncome = 68000;
		tax = taxpayer.calculateTax(totalIncome);
		assertEquals(4487.32, tax);
		
		// 71680 <= totalIncome < 90000
		totalIncome = 81456;
		tax = taxpayer.calculateTax(totalIncome);
		assertEquals(5514.176, tax);
		
		// 90000 <= totalIncome < 127120
		totalIncome = 123678;
		tax = taxpayer.calculateTax(totalIncome);
		assertEquals(8828.603, tax);
		
		// totalIncome >= 127120
		totalIncome = 128000;
		tax = taxpayer.calculateTax(totalIncome);
		System.out.println(tax);
		assertEquals(9185.48, tax);
	}
	
	@Test
	@Order(2)
	void testCalculateTaxForMarriedFilingJointlyTaxpayerFamilyStatus() {
		
		taxpayer = new Taxpayer("TestName", "testAFM", "married filing jointly", "13567");
		int totalIncome;
		double tax;
		// totalIncome < 36080
		totalIncome = 28000;
		tax = taxpayer.calculateTax(totalIncome);
		assertEquals(1498, tax);
		
		//36080 <= totalIncome < 90000
		totalIncome = 80000;
		tax = taxpayer.calculateTax(totalIncome);
		assertEquals(5026.639999999999, tax);
		
		// 90000 <= totalIncome < 143350
		totalIncome = 140000;
		tax = taxpayer.calculateTax(totalIncome);
		assertEquals(9256.64, tax);
		
		// 143350 <= totalIncome < 254240
		totalIncome = 238000;
		tax = taxpayer.calculateTax(totalIncome);
		assertEquals(16922.845, tax);
		
		// totalIncome >= 254240
		totalIncome = 280000;
		tax = taxpayer.calculateTax(totalIncome);
		assertEquals(20735.05, tax);
	}

	@Test
	@Order(3)
	void testCalculateTaxForSingles() {
		//MUST BT CHANGED
		taxpayer = new Taxpayer("TestName", "testAFM", "single", "13567");

		int totalIncome;
		double tax;
		
		// totalIncome < 24680
		totalIncome = 17890;
		tax = taxpayer.calculateTax(totalIncome);
		assertEquals(957.115, tax);
		
		// 24680 <= totalIncome < 81080
		totalIncome = 79865;
		tax = taxpayer.calculateTax(totalIncome);
		assertEquals(5210.9225, tax);
		
		// 81080 <= totalIncome < 90000
		totalIncome = 85678;
		tax = taxpayer.calculateTax(totalIncome);
		assertEquals(5657.523, tax);
		
		// 90000 <= totalIncome < 152540
		totalIncome = 148756;
		tax = taxpayer.calculateTax(totalIncome);
		assertEquals(10609.146, tax);
		
		// totalIncome >= 152540
		totalIncome = 178906;
		tax = taxpayer.calculateTax(totalIncome);
		assertEquals(13503.241, tax);
	}

	@Test
	@Order(4)
	void testCalculateTaxForHeadOfHousehold() {
		
		taxpayer = new Taxpayer("TestName", "testAFM", "head of household", "13567");
		int totalIncome;
		double tax;
		
		// totalIncome < 30390
		totalIncome = 27890;
		tax = taxpayer.calculateTax(totalIncome);
		assertEquals(1492.115, tax);
		
		// 30390 <= totalIncome < 90000
		totalIncome = 79865;
		tax = taxpayer.calculateTax(totalIncome);
		assertEquals(5113.8575, tax);
		
		// 90000 <= totalIncome < 122110
		totalIncome = 105678;
		tax = taxpayer.calculateTax(totalIncome);
		assertEquals(6933.679, tax);
		
		// 122110 <= totalIncome < 203390
		totalIncome = 148756;
		tax = taxpayer.calculateTax(totalIncome);
		assertEquals(10183.841, tax);
		
		// totalIncome >= 203390
		totalIncome = 278906;
		tax = taxpayer.calculateTax(totalIncome);
		assertEquals(21910.936, tax);
	}


	@Test
	@Order(5)
	void testGetBasicReceiptsTotalAmount() {
		Receipt firstTestBasicReceipt = new Receipt("Basic","testId", "15/11/2020", "20500", "testCompanyName", "testCountryname", "testCityName", "testStreetName", "31");
		Receipt secondTestBasicReceipt = new Receipt("Basic","testId", "15/11/2020", "19500", "testCompanyName", "testCountryname", "testCityName", "testStreetName", "31");
		Receipt thirdTestBasicReceipt = new Receipt("Basic","testId", "15/11/2020", "21000", "testCompanyName", "testCountryname", "testCityName", "testStreetName", "31");
		taxpayer.addReceiptToList(firstTestBasicReceipt);
		taxpayer.addReceiptToList(secondTestBasicReceipt);
		taxpayer.addReceiptToList(thirdTestBasicReceipt);
		double totalAmount = taxpayer.getKindOfReceiptsTotalAmount("Basic");
		assertEquals(61000, totalAmount);
	}

	@Test
	@Order(6)
	void testGetEntertainmentReceiptsTotalAmount() {
		Receipt firstTestBasicReceipt = new Receipt("Entertainment","testId", "15/11/2020", "21500", "testCompanyName", "testCountryname", "testCityName", "testStreetName", "31");
		Receipt secondTestBasicReceipt = new Receipt("Entertainment","testId", "15/11/2020", "20500", "testCompanyName", "testCountryname", "testCityName", "testStreetName", "31");
		Receipt thirdTestBasicReceipt = new Receipt("Entertainment","testId", "15/11/2020", "22000", "testCompanyName", "testCountryname", "testCityName", "testStreetName", "31");
		taxpayer.addReceiptToList(firstTestBasicReceipt);
		taxpayer.addReceiptToList(secondTestBasicReceipt);
		taxpayer.addReceiptToList(thirdTestBasicReceipt);
		double totalAmount = taxpayer.getKindOfReceiptsTotalAmount("Entertainment");
		assertEquals(64000, totalAmount);
	}

	@Test
	@Order(7)
	void testGetTravelReceiptsTotalAmount() {
		Receipt firstTestBasicReceipt = new Receipt("Travel","testId", "15/11/2020", "22500", "testCompanyName", "testCountryname", "testCityName", "testStreetName", "31");
		Receipt secondTestBasicReceipt = new Receipt("Travel","testId", "15/11/2020", "21500", "testCompanyName", "testCountryname", "testCityName", "testStreetName", "31");
		Receipt thirdTestBasicReceipt = new Receipt("Travel","testId", "15/11/2020", "23000", "testCompanyName", "testCountryname", "testCityName", "testStreetName", "31");
		taxpayer.addReceiptToList(firstTestBasicReceipt);
		taxpayer.addReceiptToList(secondTestBasicReceipt);
		taxpayer.addReceiptToList(thirdTestBasicReceipt);
		double totalAmount = taxpayer.getKindOfReceiptsTotalAmount("Travel");
		assertEquals(67000, totalAmount);
	}

	@Test
	@Order(8)
	void testGetHealthReceiptsTotalAmount() {
		Receipt firstTestBasicReceipt = new Receipt("Health","testId", "15/11/2020", "23500", "testCompanyName", "testCountryname", "testCityName", "testStreetName", "31");
		Receipt secondTestBasicReceipt = new Receipt("Health","testId", "15/11/2020", "22500", "testCompanyName", "testCountryname", "testCityName", "testStreetName", "31");
		Receipt thirdTestBasicReceipt = new Receipt("Health","testId", "15/11/2020", "24000", "testCompanyName", "testCountryname", "testCityName", "testStreetName", "31");
		taxpayer.addReceiptToList(firstTestBasicReceipt);
		taxpayer.addReceiptToList(secondTestBasicReceipt);
		taxpayer.addReceiptToList(thirdTestBasicReceipt);
		double totalAmount = taxpayer.getKindOfReceiptsTotalAmount("Health");
		assertEquals(70000, totalAmount);
	}

	@Test
	@Order(9)
	void testGetOtherReceiptsTotalAmount() {
		Receipt firstTestBasicReceipt = new Receipt("Other","testId", "15/11/2020", "24500", "testCompanyName", "testCountryname", "testCityName", "testStreetName", "31");
		Receipt secondTestBasicReceipt = new Receipt("Other","testId", "15/11/2020", "23500", "testCompanyName", "testCountryname", "testCityName", "testStreetName", "31");
		Receipt thirdTestBasicReceipt = new Receipt("Other","testId", "15/11/2020", "25000", "testCompanyName", "testCountryname", "testCityName", "testStreetName", "31");
		taxpayer.addReceiptToList(firstTestBasicReceipt);
		taxpayer.addReceiptToList(secondTestBasicReceipt);
		taxpayer.addReceiptToList(thirdTestBasicReceipt);
		double totalAmount = taxpayer.getKindOfReceiptsTotalAmount("Other");
		assertEquals(73000, totalAmount);
	}

	@Test
	@Order(10)
	void testGetTotalReceiptsAmount() {
		Receipt firstTestBasicReceipt = new Receipt("Basic","testId", "15/11/2020", "20500", "testCompanyName", "testCountryname", "testCityName", "testStreetName", "31");
		Receipt secondTestBasicReceipt = new Receipt("Entertainment","testId", "15/11/2020", "20500", "testCompanyName", "testCountryname", "testCityName", "testStreetName", "31");
		Receipt thirdTestBasicReceipt = new Receipt("Travel","testId", "15/11/2020", "23000", "testCompanyName", "testCountryname", "testCityName", "testStreetName", "31");
		Receipt fourthTestBasicReceipt = new Receipt("Health","testId", "15/11/2020", "24000", "testCompanyName", "testCountryname", "testCityName", "testStreetName", "31");
		Receipt fifthTestBasicReceipt = new Receipt("Other","testId", "15/11/2020", "25000", "testCompanyName", "testCountryname", "testCityName", "testStreetName", "31");
		taxpayer.addReceiptToList(firstTestBasicReceipt);
		taxpayer.addReceiptToList(secondTestBasicReceipt);
		taxpayer.addReceiptToList(thirdTestBasicReceipt);
		taxpayer.addReceiptToList(fourthTestBasicReceipt);
		taxpayer.addReceiptToList(fifthTestBasicReceipt);
		double totaAmount = taxpayer.getTotalReceiptsAmount();
		assertEquals(113000, totaAmount);
		
	}

	@Test
	@Order(11)
	void testAddReceiptToList() {
		assertEquals(0, taxpayer.getReceiptsArrayList().size());
		Receipt firstTestBasicReceipt = new Receipt("Basic","testId", "15/11/2020", "20500", "testCompanyName", "testCountryname", "testCityName", "testStreetName", "31");
		taxpayer.addReceiptToList(firstTestBasicReceipt);
		assertEquals(1, taxpayer.getReceiptsArrayList().size());
		
	}

	@Test
	@Order(12)
	void testRemoveReceiptFromList() {
		assertEquals(0, taxpayer.getReceiptsArrayList().size());
		Receipt firstTestBasicReceipt = new Receipt("Basic","testId", "15/11/2020", "20500", "testCompanyName", "testCountryname", "testCityName", "testStreetName", "31");
		Receipt secondTestBasicReceipt = new Receipt("Entertainment","testId", "15/11/2020", "20500", "testCompanyName", "testCountryname", "testCityName", "testStreetName", "31");
		taxpayer.addReceiptToList(firstTestBasicReceipt);
		taxpayer.addReceiptToList(secondTestBasicReceipt);
		assertEquals(2, taxpayer.getReceiptsArrayList().size());
		taxpayer.removeReceiptFromList(0);
		assertEquals(1, taxpayer.getReceiptsArrayList().size());
		taxpayer.removeReceiptFromList(0);
		assertEquals(0, taxpayer.getReceiptsArrayList().size());
	}

	@Test
	@Order(13)
	void testCalculateTaxpayerTaxIncreaseOrDecreaseBasedOnReceipts() {
		Receipt firstTestBasicReceipt = new Receipt("Basic","testId", "15/11/2020", "20500", "testCompanyName", "testCountryname", "testCityName", "testStreetName", "31");
		Receipt secondTestBasicReceipt = new Receipt("Entertainment","testId", "15/11/2020", "20500", "testCompanyName", "testCountryname", "testCityName", "testStreetName", "31");
		Receipt thirdTestBasicReceipt = new Receipt("Travel","testId", "15/11/2020", "23000", "testCompanyName", "testCountryname", "testCityName", "testStreetName", "31");
		Receipt fourthTestBasicReceipt = new Receipt("Health","testId", "15/11/2020", "24000", "testCompanyName", "testCountryname", "testCityName", "testStreetName", "31");
		Receipt fifthTestBasicReceipt = new Receipt("Other","testId", "15/11/2020", "25000", "testCompanyName", "testCountryname", "testCityName", "testStreetName", "31");
		taxpayer.addReceiptToList(firstTestBasicReceipt);
		taxpayer.addReceiptToList(secondTestBasicReceipt);
		taxpayer.addReceiptToList(thirdTestBasicReceipt);
		taxpayer.addReceiptToList(fourthTestBasicReceipt);
		taxpayer.addReceiptToList(fifthTestBasicReceipt);
		taxpayer.calculateTaxpayerTaxIncreaseOrDecreaseBasedOnReceipts();
		assertEquals(0, taxpayer.getTaxInxrease());
		assertEquals(217.75, taxpayer.getTaxDecrease());
		assertEquals(508.08, taxpayer.getTotalTax());
		
		
		
	}

}
