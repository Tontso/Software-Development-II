package fileWords;

import java.util.ArrayList;
import java.util.List;

public class XmlFileWords extends FileWords{
	
	
	private static XmlFileWords xmlFileWords = null;
	
	
	private  XmlFileWords() {
		setValues();
	}


	public static XmlFileWords getXmlFileWords() {
		if(xmlFileWords == null) {
			xmlFileWords = new XmlFileWords();
		}
		return xmlFileWords;
	}
	
	
	private void setValues() {
		firstWord = new ArrayList<>(List.of("Name", "AFM", "Status", "Income", "ReceiptID", "Date", "Kind", "Amount", "Company", "Country", "City", "Street", "Number", "<Receipts>", "</Receipts>"));
		secondWord = new ArrayList<>(List.of("Name", "AFM", "Status", "Income", "ReceiptID", "Date", "Kind", "Amount", "Company", "Country", "City", "Street", "Number", "<Receipts>", "</Receipts>"));
		firstWordParsers = new ArrayList<>(List.of("<", "> "));
		secondWordParsers = new ArrayList<>(List.of(" </", ">"));
		saveTaxpayerWordFirst = new ArrayList<>(List.of("Name", "AFM", "Income", "BasicTax", "TaxIncrease", "TaxDecrease", "TotalTax", "Receipts", "Entertainment", "Basic", "Travel", "Health", "Other"));
		saveTaxpayerWordSecond = new ArrayList<>(List.of("Name", "AFM", "Income", "BasicTax", "TaxIncrease", "TaxDecrease", "TotalTax", "Receipts", "Entertainment", "Basic", "Travel", "Health", "Other"));
		saveAs = "_LOG.xml";
		
	}
	
	
	

	
}
