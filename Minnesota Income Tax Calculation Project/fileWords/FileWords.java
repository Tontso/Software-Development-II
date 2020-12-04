package fileWords;

public interface FileWords {

	
	public String[] getFirstWord();

	public String[] getSecondWord();

	public String[] getFirstWordParsers();
	
	public String[] getSecondParsers();
	
	public String makeString(String value, String[] parsers);

}
