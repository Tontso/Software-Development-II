package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(OrderAnnotation.class)
class TaxpayerTest {


	@Test
	@Order(1)
	void testCalculateTaxForMarriedFilingSeparately() {
		int totalIncome;
		double tax;
		// totalIncome < 18040
		totalIncome = 16050;
		tax = (5.35/100) * totalIncome;
		assertEquals(858.675, tax);
		
		// 18040 <= totalIncome < 71680
		totalIncome = 68000;
		tax = 965.14 + ((7.05/100) * (totalIncome-18040));
		assertEquals(4487.32, tax);
		
		// 71680 <= totalIncome < 90000
		totalIncome = 81456;
		tax = 4746.76 + ((7.85/100) * (totalIncome-71680));
		assertEquals(5514.176, tax);
		
		// 90000 <= totalIncome < 127120
		totalIncome = 123678;
		tax = 6184.88 + ((7.85/100) * (totalIncome-90000));
		assertEquals(8828.603, tax);
		
		// totalIncome >= 127120
		totalIncome = 128000;
		tax = 9098.80 + ((9.85/100) * (totalIncome-127120));
		assertEquals(9185.48, tax);
	}
	
	@Test
	@Order(2)
	void testCalculateTaxForMarriedFilingJointlyTaxpayerFamilyStatus() {
		int totalIncome;
		double tax;
		// totalIncome < 36080
		totalIncome = 28000;
		tax = (5.35/100) * totalIncome;
		assertEquals(1498, tax);
		
		//36080 <= totalIncome < 90000
		totalIncome = 80000;
		tax = 1930.28 + ((7.05/100) * (totalIncome-36080));
		assertEquals(5026.639999999999, tax);
		
		// 90000 <= totalIncome < 143350
		totalIncome = 140000;
		tax = 5731.64 + ((7.05/100) * (totalIncome-90000));
		assertEquals(9256.64, tax);
		
		// 143350 <= totalIncome < 254240
		totalIncome = 238000;
		tax = 9492.82 + ((7.85/100) * (totalIncome-143350));
		assertEquals(16922.845, tax);
		
		// totalIncome >= 254240
		totalIncome = 280000;
		tax = 18197.69 + ((9.85/100) * (totalIncome-254240));
		assertEquals(20735.05, tax);
	}

	@Test
	@Order(3)
	void testCalculateTaxForSingles() {
		//MUST BT CHANGED
		int totalIncome;
		double tax;
		// totalIncome < 24680
		totalIncome = 17890;
		tax = (5.35/100) * totalIncome;
		assertEquals(957.115, tax);
		
		// 24680 <= totalIncome < 81080
		totalIncome = 79865;
		tax = 1320.38 + ((7.05/100) * (totalIncome-24680));
		assertEquals(5210.9225, tax);
		
		// 81080 <= totalIncome < 90000
		totalIncome = 85678;
		tax = 5296.58 + ((7.85/100) * (totalIncome-81080));
		assertEquals(5657.523, tax);
		
		// 90000 <= totalIncome < 152540
		totalIncome = 148756;
		tax = 5996.80 + ((7.85/100) * (totalIncome-90000));
		assertEquals(10609.146, tax);
		
		// totalIncome >= 152540
		totalIncome = 178906;
		tax = 10906.19 + ((9.85/100) * (totalIncome-152540));
		assertEquals(13503.241, tax);
	}

	@Test
	@Order(4)
	void testCalculateTaxForHeadOfHousehold() {
		int totalIncome;
		double tax;
		// totalIncome < 30390
		totalIncome = 27890;
		tax = (5.35/100) * totalIncome;
		assertEquals(1492.115, tax);
		
		// 30390 <= totalIncome < 90000
		totalIncome = 79865;
		tax = 1625.87 + ((7.05/100) * (totalIncome-30390));
		assertEquals(5113.8575, tax);
		
		// 90000 <= totalIncome < 122110
		totalIncome = 105678;
		tax = 5828.38 + ((7.05/100) * (totalIncome-90000));
		assertEquals(6933.679, tax);
		
		// 122110 <= totalIncome < 203390
		totalIncome = 148756;
		tax = 8092.13 + ((7.85/100) * (totalIncome-122110));
		assertEquals(10183.841, tax);
		
		// totalIncome >= 203390
		totalIncome = 278906;
		tax = 14472.61 + ((9.85/100) * (totalIncome-203390));
		assertEquals(21910.936, tax);
	}


	@Test
	void testGetBasicReceiptsTotalAmount() {
		fail("Not yet implemented");
	}

	@Test
	void testGetEntertainmentReceiptsTotalAmount() {
		fail("Not yet implemented");
	}

	@Test
	void testGetTravelReceiptsTotalAmount() {
		fail("Not yet implemented");
	}

	@Test
	void testGetHealthReceiptsTotalAmount() {
		fail("Not yet implemented");
	}

	@Test
	void testGetOtherReceiptsTotalAmount() {
		fail("Not yet implemented");
	}

	@Test
	void testGetTotalReceiptsAmount() {
		fail("Not yet implemented");
	}

	@Test
	void testAddReceiptToList() {
		fail("Not yet implemented");
	}

	@Test
	void testRemoveReceiptFromList() {
		fail("Not yet implemented");
	}

	@Test
	void testCalculateTaxpayerTaxIncreaseOrDecreaseBasedOnReceipts() {
		fail("Not yet implemented");
	}

}
