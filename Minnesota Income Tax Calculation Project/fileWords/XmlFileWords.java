package fileWords;

public class XmlFileWords implements FileWords{
	
	
	private static XmlFileWords xmlFileWords = null;
	
	
	private String[] firstAndSecondWord = {"Name", "AFM", "Status", "Income", "ReceiptID", "Date", "Kind", "Amount", "Company", "Country", "City", "Street", "Number", "<Receipts>", "</Receipts>"};
	private String[] firstWordParser = {"<", "> "};
	private String[] secondWordParser = {" </", ">"};
	private String[] saveTaxpayerWords = {"Name", "AFM", "Income", "BasicTax", "TaxIncrease", "TaxDecrease", "TotalTax", "Receipts", "Entertainment", "Basic", "Travel", "Health", "Other"};
	private String saveAs = "_LOG.xml";
	
	
	private  XmlFileWords() {
		
	}
	
	
	public static XmlFileWords getXmlFileWords() {
		if(xmlFileWords == null) {
			xmlFileWords = new XmlFileWords();
		}
		return xmlFileWords;
	}
	
	
	public String loadDataString(int position, String fileLine) {
		String firstWordData = firstWordParser[0]+firstAndSecondWord[position]+firstWordParser[1];
		String secondWordData = secondWordParser[0]+firstAndSecondWord[position]+secondWordParser[1];
		return getValue(fileLine, firstWordData, secondWordData);
	}
	
	
	private String getValue(String fileLine, String parameterStartField, String parameterEndField) {
		return fileLine.substring(parameterStartField.length(), fileLine.length()-parameterEndField.length());
	}

	
	@Override
	public String[] getFirstWord() {
		return firstAndSecondWord;
	}

	
	@Override
	public String makeSaveString(int position, String value) {
		return firstWordParser[0]+saveTaxpayerWords[position]+firstWordParser[1]+value+secondWordParser[0]+saveTaxpayerWords[position]+secondWordParser[1];
	}

	
	@Override
	public String getSaveAs() {
		return saveAs;
	}

	
	@Override
	public String makeUpdateString(int position, String value) {
		return firstWordParser[0]+firstAndSecondWord[position]+firstWordParser[1]+value+secondWordParser[0]+firstAndSecondWord[position]+secondWordParser[1];
	}

	
}
