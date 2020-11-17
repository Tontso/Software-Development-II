package test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import dataManagePackage.Database;
import dataManagePackage.Taxpayer;

@TestMethodOrder(OrderAnnotation.class)
class DatabaseTest {

	

	@Test
	void testProccessTaxpayersDataFromFilesIntoDatabase() {
		fail("Not yet implemented");
	}

	@Test
	@Order(1)
	void testAddTaxpayerToList() {
		System.out.println("test 1");
		Taxpayer taxpayer = new Taxpayer("testName", "testAFM", "single", "3168");
		Database.addTaxpayerToList(taxpayer);
		Taxpayer gettedTaxpayer = Database.getTaxpayerFromArrayList(0);
		assertEquals(taxpayer, gettedTaxpayer);
		Database.removeTaxpayerFromArrayList(0);
	}

	@Test
	@Order(2)
	void testGetTaxpayersArrayListSize() {
		System.out.println("test 2");
		assertEquals(0, Database.getTaxpayersArrayListSize());
		
		Taxpayer taxpayer = new Taxpayer("testName", "testAFM", "single", "3168");
		Database.addTaxpayerToList(taxpayer);
		assertEquals(1, Database.getTaxpayersArrayListSize());
		Database.removeTaxpayerFromArrayList(0);
	}

	@Test
	@Order(3)
	void testGetTaxpayerFromArrayList() {
		System.out.println("test 3");
		assertEquals(0, Database.getTaxpayersArrayListSize());
		Taxpayer firstTaxpayer = new Taxpayer("firstTestName", "testAFM", "single", "3168");
		Taxpayer secondTaxpayer = new Taxpayer("secondTestName", "testAFM", "single", "3168");
		Taxpayer thirdTaxpayer = new Taxpayer("thirdTestName", "testAFM", "single", "3168");
		Database.addTaxpayerToList(firstTaxpayer);
		Database.addTaxpayerToList(secondTaxpayer);
		Database.addTaxpayerToList(thirdTaxpayer);
		assertEquals(firstTaxpayer, Database.getTaxpayerFromArrayList(0));
		assertEquals(secondTaxpayer, Database.getTaxpayerFromArrayList(1));
		assertEquals(thirdTaxpayer, Database.getTaxpayerFromArrayList(2));
		Database.removeTaxpayerFromArrayList(0);
		Database.removeTaxpayerFromArrayList(0);
		Database.removeTaxpayerFromArrayList(0);
		
	}

	@Test
	@Order(4)
	void testRemoveTaxpayerFromArrayList() {
		System.out.println("test 4");
		Taxpayer firstTaxpayer = new Taxpayer("firstTestName", "testAFM", "single", "3168");	
		Database.addTaxpayerToList(firstTaxpayer);
		assertEquals(1, Database.getTaxpayersArrayListSize());
		Database.removeTaxpayerFromArrayList(0);
		assertEquals(0, Database.getTaxpayersArrayListSize());
	
	}

	@Test
	@Order(5)
	void testGetTaxpayerNameAfmValuesPairList() {
		System.out.println("test 5");
		Taxpayer firstTaxpayer = new Taxpayer("firstTestName", "firstTestAFM", "single", "3168");
		Taxpayer secondTaxpayer = new Taxpayer("secondTestName", "testAFM", "single", "3168");
		Taxpayer thirdTaxpayer = new Taxpayer("thirdTestName", "thirdTestAFM", "single", "3168");
		
		Database.addTaxpayerToList(firstTaxpayer);
		Database.addTaxpayerToList(secondTaxpayer);
		Database.addTaxpayerToList(thirdTaxpayer);
		
		Taxpayer tokenTaxpayer = Database.getTaxpayerFromArrayList(2);
		assertEquals("thirdTestName | thirdTestAFM", tokenTaxpayer.getName() + " | " +tokenTaxpayer.getAFM());
		
		Taxpayer secondTokenTaxpayer = Database.getTaxpayerFromArrayList(0);
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
		Database.addTaxpayerToList(firstTaxpayer);
		Database.addTaxpayerToList(secondTaxpayer);
		Database.addTaxpayerToList(thirdTaxpayer);
		
		for (int i = 0; i < testArray.length; i++) {
			testArray[i] = Database.getTaxpayerFromArrayList(i).getName() +" | "+Database.getTaxpayerFromArrayList(i).getAFM();
		}
		assertArrayEquals(expectedArray, testArray);
		Database.removeTaxpayerFromArrayList(0);
		Database.removeTaxpayerFromArrayList(0);
		Database.removeTaxpayerFromArrayList(0);
	}

	
	@Test
	void testUpdateTaxpayerInputFile() {
		fail("Not yet implemented");
	}

}
