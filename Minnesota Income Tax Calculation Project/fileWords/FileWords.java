package fileWords;

import java.util.List;

public abstract class FileWords {

	protected List<String> firstWord;
	protected List<String> secondWord ;
	protected List<String> firstWordParsers ;
	protected List<String> secondWordParsers ;
	protected List<String> saveTaxpayerWordFirst;
	protected List<String> saveTaxpayerWordSecond;
	protected String saveAs;
	
	public List<String> getFirstWord() {
		return firstWord;
	}
			
	
	
	public String loadDataString(int position, String fileLine) {
		String firstWordData = firstWordParsers.get(0)+firstWord.get(position)+firstWordParsers.get(1);
		String secondWordData = secondWordParsers.get(0)+secondWord.get(position)+secondWordParsers.get(1);
		return getValue(fileLine, firstWordData, secondWordData);
	}
	
	
	private String getValue(String fileLine, String parameterStartField, String parameterEndField) {
		return fileLine.substring(parameterStartField.length(), fileLine.length()-parameterEndField.length());
	}
	
	
	public String makeSaveString(int position, String value) {
		return firstWordParsers.get(0)+saveTaxpayerWordFirst.get(position)+firstWordParsers.get(1)+value+secondWordParsers.get(0)+saveTaxpayerWordSecond.get(position)+secondWordParsers.get(1);
	}
	
	
	public String makeUpdateString(int position, String value) {
		return firstWordParsers.get(0)+firstWord.get(position)+firstWordParsers.get(1)+value+secondWordParsers.get(0)+secondWord.get(position)+secondWordParsers.get(1);
	}
	
	
	public String getSaveAs() {
		return saveAs;
	}
}
