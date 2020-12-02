package test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import dataManagePackage.Database;
import dataManagePackage.Taxpayer;

@TestMethodOrder(OrderAnnotation.class)
class DatabaseTest {

	Database database = Database.getDatabase();

	@Test
	@Order(1)
	void testAddTaxpayerToList() {
		Taxpayer taxpayer = new Taxpayer("testName", "testAFM", "single", "3168");
		database.addTaxpayerToList(taxpayer);
		Taxpayer gettedTaxpayer = database.getTaxpayerFromArrayList(0);
		assertEquals(taxpayer, gettedTaxpayer);
		database.removeTaxpayerFromArrayList(0);
	}

	@Test
	@Order(2)
	void testGetTaxpayersArrayListSize() {
		System.out.println("test 2");
		assertEquals(0, database.getTaxpayersArrayListSize());
		
		Taxpayer taxpayer = new Taxpayer("testName", "testAFM", "single", "3168");
		database.addTaxpayerToList(taxpayer);
		assertEquals(1, database.getTaxpayersArrayListSize());
		database.removeTaxpayerFromArrayList(0);
	}

	@Test
	@Order(3)
	void testGetTaxpayerFromArrayList() {
		System.out.println("test 3");
		assertEquals(0, database.getTaxpayersArrayListSize());
		Taxpayer firstTaxpayer = new Taxpayer("firstTestName", "testAFM", "single", "3168");
		Taxpayer secondTaxpayer = new Taxpayer("secondTestName", "testAFM", "single", "3168");
		Taxpayer thirdTaxpayer = new Taxpayer("thirdTestName", "testAFM", "single", "3168");
		database.addTaxpayerToList(firstTaxpayer);
		database.addTaxpayerToList(secondTaxpayer);
		database.addTaxpayerToList(thirdTaxpayer);
		assertEquals(firstTaxpayer, database.getTaxpayerFromArrayList(0));
		assertEquals(secondTaxpayer, database.getTaxpayerFromArrayList(1));
		assertEquals(thirdTaxpayer, database.getTaxpayerFromArrayList(2));
		database.removeTaxpayerFromArrayList(0);
		database.removeTaxpayerFromArrayList(0);
		database.removeTaxpayerFromArrayList(0);
		
	}

	@Test
	@Order(4)
	void testRemoveTaxpayerFromArrayList() {
		System.out.println("test 4");
		Taxpayer firstTaxpayer = new Taxpayer("firstTestName", "testAFM", "single", "3168");	
		database.addTaxpayerToList(firstTaxpayer);
		assertEquals(1, database.getTaxpayersArrayListSize());
		database.removeTaxpayerFromArrayList(0);
		assertEquals(0, database.getTaxpayersArrayListSize());
	
	}

	@Test
	@Order(5)
	void testGetTaxpayerNameAfmValuesPairList() {
		System.out.println("test 5");
		Taxpayer firstTaxpayer = new Taxpayer("firstTestName", "firstTestAFM", "single", "3168");
		Taxpayer secondTaxpayer = new Taxpayer("secondTestName", "testAFM", "single", "3168");
		Taxpayer thirdTaxpayer = new Taxpayer("thirdTestName", "thirdTestAFM", "single", "3168");
		
		database.addTaxpayerToList(firstTaxpayer);
		database.addTaxpayerToList(secondTaxpayer);
		database.addTaxpayerToList(thirdTaxpayer);
		
		Taxpayer tokenTaxpayer = database.getTaxpayerFromArrayList(2);
		assertEquals("thirdTestName | thirdTestAFM", tokenTaxpayer.getName() + " | " +tokenTaxpayer.getAFM());
		
		Taxpayer secondTokenTaxpayer = database.getTaxpayerFromArrayList(0);
		assertEquals("firstTestName | firstTestAFM", secondTokenTaxpayer.getName() + " | " +secondTokenTaxpayer.getAFM());
	}

	
	@Test
	@Order(6)
	void testGetTaxpayersNameAfmValuesPairList() {
		System.out.println("test 6");
		String[] testArray = new String[3];
		Taxpayer firstTaxpayer = new Taxpayer("firstTestName", "firstTestAFM", "single", "3168");
		Taxpayer secondTaxpayer = new Taxpayer("secondTestName", "testAFM", "single", "3168");
		Taxpayer thirdTaxpayer = new Taxpayer("thirdTestName", "thirdTestAFM", "single", "3168");
		
		String[] expectedArray = {"firstTestName | firstTestAFM", "secondTestName | testAFM","thirdTestName | thirdTestAFM"};
		database.addTaxpayerToList(firstTaxpayer);
		database.addTaxpayerToList(secondTaxpayer);
		database.addTaxpayerToList(thirdTaxpayer);
		
		for (int i = 0; i < testArray.length; i++) {
			testArray[i] = database.getTaxpayerFromArrayList(i).getName() +" | "+database.getTaxpayerFromArrayList(i).getAFM();
		}
		assertArrayEquals(expectedArray, testArray);
		database.removeTaxpayerFromArrayList(0);
		database.removeTaxpayerFromArrayList(0);
		database.removeTaxpayerFromArrayList(0);
	}

	

}
