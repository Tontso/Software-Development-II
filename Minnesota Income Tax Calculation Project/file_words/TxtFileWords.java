package file_words;

import java.util.ArrayList;
import java.util.List;

public class TxtFileWords extends FileWords{
	
	private static TxtFileWords txtFileWords = null;
	
	
	
	private TxtFileWords() {
		setValues();
	}
	

	public static TxtFileWords getTxtFileWords() {
		if(txtFileWords == null) {
			txtFileWords = new TxtFileWords();
		}
		return txtFileWords;
	}
	
	
	private void setValues() {
		firstWord = new ArrayList<>(List.of("Name", "AFM", "Status", "Income", "Receipt ID", "Date", "Kind", "Amount", "Company", "Country", "City", "Street", "Number", "Receipts:", ""));
		secondWord = new ArrayList<>(List.of("", "", "", "", "", "", "", "", "", "", "", "", ""));
		firstWordParsers = new ArrayList<>(List.of("", ": "));
		secondWordParsers = new ArrayList<>(List.of("", ""));
		saveTaxpayerWordFirst = new ArrayList<>(List.of("Name", "AFM", "Income", "Basic Tax", "Tax Increase", "Tax Decrease", "Total Tax", "Total Receipts Amount", "Entertainment", "Basic", "Travel", "Health", "Other"));
		saveTaxpayerWordSecond = new ArrayList<>(List.of("", "", "", "", "", "", "", "", "", "", "", "", ""));
		saveAs = "_LOG.txt";
		
	}

}