package fileWords;

public class XmlFileWords implements FileWords{
	
	private String[] firstAndSecondWord = {"Name", "AFM", "Status", "Income", "ReceiptID", "Date", "Kind", "Amount", "Company", "Country", "City", "Street", "Number", "<Receipts>", "</Receipts>"};
	private String[] firstWordParser = {"<", "> "};
	private String[] secondWordParser = {" </", ">"};
	private String[] saveTaxpayerWords = {"Name", "AFM", "Income", "BasicTax", "TaxIncrease", "TaxDecrease", "TotalTax", "Receipts", "Entertainment", "Basic", "Travel", "Health", "Other"};
	private String saveAs = "_LOG.xml";
	public  XmlFileWords() {
		
	}

	@Override
	public String[] getFirstWord() {
		return firstAndSecondWord;
	}

	@Override
	public String[] getSecondWord() {
		return firstAndSecondWord;
	}

	@Override
	public String[] getFirstWordParsers() {
		return firstWordParser;
	}

	@Override
	public String[] getSecondParsers() {
		return secondWordParser;
	}
	
	@Override
	public String makeString(String value, String[] parsers) {
		return parsers[0]+value+parsers[1];
	}

	@Override
	public String makeSaveString(int position, String value) {
		return firstWordParser[0]+saveTaxpayerWords[position]+firstWordParser[1]+value+secondWordParser[0]+saveTaxpayerWords[position]+secondWordParser[1];
	}

	@Override
	public String getSaveAs() {
		return saveAs;
	}

	
}
