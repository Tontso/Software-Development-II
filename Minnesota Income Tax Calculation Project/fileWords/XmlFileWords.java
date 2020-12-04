package fileWords;

public class XmlFileWords implements FileWords{
	
	private String[] firstAndSecondWord = {"Name", "AFM", "Status", "Income", "ReceiptID", "Date", "Kind", "Amount", "Company", "Country", "City", "Street", "Number"};
	private String[] firstWordParser = {"<", "> "};
	private String[] secondWordParser = {" </", ">"};
	
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
}
