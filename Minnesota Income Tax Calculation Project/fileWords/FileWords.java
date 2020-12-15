package fileWords;

public interface FileWords {

	
	public String[] getFirstWord();
	
	public String makeString(String value, String[] parsers);
	
	public String makeSaveString(int position, String value);
	
	public String makeUpdateString(int position, String value);
	
	public String getSaveAs();
	
	public String loadDataString(int position, String fileLine);
			
	
}
