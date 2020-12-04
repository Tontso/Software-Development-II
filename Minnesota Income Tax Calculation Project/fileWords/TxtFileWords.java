package fileWords;

public class TxtFileWords implements FileWords{
	
	private String[] firstWord = {"Name", "AFM", "Status", "Income", "Receipt ID", "Date", "Kind", "Amount", "Company", "Country", "City", "Street", "Number", "Receipts:", ""};
	private String[] secondWord = {"", "", "", "", "", "", "", "", "", "", "", "", ""};
	private String[] firstWordParsers = {"", ": "};
	private String[] secondWordParsers = {"", ""};
	private String[] saveTaxpayerWords = {"Name", "AFM", "Income", "Basic Tax", "Tax Increase", "Tax Decrease", "Total Tax", "Total Receipts Amount", "Entertainment", "Basic", "Travel", "Health", "Other"};
	private String saveAs = "_LOG.txt";
	
	
	public TxtFileWords() {
		
	}

	@Override
	public String[] getFirstWord() {
		return firstWord;
	}

	@Override
	public String[] getSecondWord() {
		return secondWord;
	}

	@Override
	public String[] getFirstWordParsers() {
		return firstWordParsers;
	}

	@Override
	public String[] getSecondParsers() {
		return secondWordParsers;
	}
	
	
	@Override
	public String makeString(String value, String[] parsers) {
		return parsers[0]+value+parsers[1];
	}

	@Override
	public String makeSaveString(int position, String value) {
		return firstWordParsers[0]+saveTaxpayerWords[position]+firstWordParsers[1]+value+secondWordParsers[0]+secondWord[position]+secondWordParsers[1];
	}

	@Override
	public String getSaveAs() {
		return saveAs;
	}

	
}
