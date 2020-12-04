package fileWords;

public class TxtFileWords implements FileWords{
	
	private String[] firstWord = {"Name", "AFM", "Status", "Income", "Receipt ID", "Date", "Kind", "Amount", "Company", "Country", "City", "Street", "Number"};
	private String[] secondWord = {"", "", "", "", "", "", "", "", "", "", "", "", ""};
	private String[] firstWordParsers = {"", ": "};
	private String[] secondWordParsers = {"", ""};
	
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
}
