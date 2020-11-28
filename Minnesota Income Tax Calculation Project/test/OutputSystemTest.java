package test;

import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

class OutputSystemTest {

	@Test
	@Order(1)
	void createTaxpayerIntoDatabase(){
		
	}
	@Order(2)
	void testSaveUpdatedTaxpayerTxtInputFile() {
		fail("Not yet implemented");
	}

	@Test
	void testSaveUpdatedTaxpayerXmlInputFile() {
		fail("Not yet implemented");
	}

	@Test
	void testSaveTaxpayerInfoToTxtLogFile() {
		fail("Not yet implemented");
	}

	@Test
	void testSaveTaxpayerInfoToXmlLogFile() {
		fail("Not yet implemented");
	}

	@Test
	void testCreateTaxpayerReceiptsPieJFreeChart() {
		fail("Not yet implemented");
	}

	@Test
	void testCreateTaxpayerTaxAnalysisBarJFreeChart() {
		fail("Not yet implemented");
	}

}
