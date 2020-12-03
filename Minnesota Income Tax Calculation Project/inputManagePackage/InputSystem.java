package inputManagePackage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

import dataManagePackage.Database;
import dataManagePackage.Taxpayer;
import dataManagePackage.Receipt.Receipt;

public class InputSystem {
	
	private static InputSystem inputSystem = null;
	private String[] wordsForTxt = {"Name", "AFM", "Status", "Income", "Receipt ID", "Date", "Kind", "Amount", "Company", "Country", "City", "Street", "Number"};
	private String[] wordsForXml = {"Name", "AFM", "Status", "Income", "ReceiptID", "Date", "Kind", "Amount", "Company", "Country", "City", "Street", "Number"};
	private String[] emptyStringArray = {"", "", "", "", "", "", "", "", "", "", "", "", ""};
	private String[] firstWordTxt = {"", ": "};
	private String[] secondWordTxt = {"", ""};
	private String[] firstWordXml = {"<", "> "};
	private String[] secondWordXml = {" </", ">"};
	private InputSystem () {
		
	}
	
	public static InputSystem getInputSystem() {
		if(inputSystem == null) {
			inputSystem = new InputSystem();
		}
		return inputSystem;
	}
	
	public void addTaxpayersDataFromFilesIntoDatabase(String afmInfoFilesFolderPath, List<String> taxpayersAfmInfoFiles){
		for (String afmInfoFile : taxpayersAfmInfoFiles)
		{		
			if (afmInfoFile.endsWith(".txt")){
				loadTaxpayerDataFromFileIntoDatabase(afmInfoFilesFolderPath, afmInfoFile, firstWordTxt, secondWordTxt, emptyStringArray, wordsForTxt);
			}
			else if (afmInfoFile.endsWith(".xml")){
				loadTaxpayerDataFromFileIntoDatabase(afmInfoFilesFolderPath, afmInfoFile, firstWordXml, secondWordXml, wordsForXml,wordsForXml);
			}
		}
	}
	
	private void loadTaxpayerDataFromFileIntoDatabase(String afmInfoFileFolderPath, String afmInfoFile, String[] parsersFirstWord, String[] parsersSecondWord, String[] secondWord, String[] words){

		Scanner inputStream = openFile(afmInfoFileFolderPath+"\\"+afmInfoFile);		
		
		String taxpayerName = getParameterValueFromXmlFileLine(inputStream.nextLine(), makeString(words[0], parsersFirstWord), makeString(secondWord[0], parsersSecondWord));
		String taxpayerAFM = getParameterValueFromXmlFileLine(inputStream.nextLine(), makeString(words[1], parsersFirstWord),  makeString(secondWord[1], parsersSecondWord));
		String taxpayerStatus = getParameterValueFromXmlFileLine(inputStream.nextLine(),  makeString(words[2], parsersFirstWord), makeString(secondWord[2], parsersSecondWord));
		String taxpayerIncome = getParameterValueFromXmlFileLine(inputStream.nextLine(),  makeString(words[3], parsersFirstWord), makeString(secondWord[3], parsersSecondWord));
		Taxpayer newTaxpayer = new Taxpayer(taxpayerName, taxpayerAFM, taxpayerStatus, taxpayerIncome);
		
		String fileLine;
		while (inputStream.hasNextLine()){
			fileLine = inputStream.nextLine();
			if (fileLine.equals("")) continue;
			if (fileLine.indexOf("Receipts:")!=-1) continue;
			if (fileLine.indexOf("<Receipts>")!=-1) continue;
			if (fileLine.indexOf("</Receipts>")!=-1) break;
			
			String receiptID = getParameterValueFromXmlFileLine(fileLine, makeString(words[4], parsersFirstWord), makeString(secondWord[4], parsersSecondWord));
			String receiptDate = getParameterValueFromXmlFileLine(inputStream.nextLine(), makeString(words[5], parsersFirstWord), makeString(secondWord[5], parsersSecondWord));
			String receiptKind = getParameterValueFromXmlFileLine(inputStream.nextLine(), makeString(words[6], parsersFirstWord), makeString(secondWord[6], parsersSecondWord));
			String receiptAmount = getParameterValueFromXmlFileLine(inputStream.nextLine(), makeString(words[7], parsersFirstWord), makeString(secondWord[7], parsersSecondWord));
			String receiptCompany = getParameterValueFromXmlFileLine(inputStream.nextLine(), makeString(words[8], parsersFirstWord), makeString(secondWord[8], parsersSecondWord));
			String receiptCountry = getParameterValueFromXmlFileLine(inputStream.nextLine(), makeString(words[9], parsersFirstWord), makeString(secondWord[9], parsersSecondWord));
			String receiptCity = getParameterValueFromXmlFileLine(inputStream.nextLine(), makeString(words[10], parsersFirstWord), makeString(secondWord[10], parsersSecondWord));
			String receiptStreet = getParameterValueFromXmlFileLine(inputStream.nextLine(), makeString(words[11], parsersFirstWord), makeString(secondWord[11], parsersSecondWord));
			String receiptNumber = getParameterValueFromXmlFileLine(inputStream.nextLine(),  makeString(words[12], parsersFirstWord), makeString(secondWord[12], parsersSecondWord));
			Receipt newReceipt = new Receipt(receiptKind, receiptID, receiptDate, receiptAmount, receiptCompany, receiptCountry, receiptCity, receiptStreet, receiptNumber);
			
			newTaxpayer.addReceiptToList(newReceipt);
		}
		
		Database.getDatabase().addTaxpayerToList(newTaxpayer);
	}
	
	
	private String makeString(String value, String[] parsers) {
		return parsers[0]+value+parsers[1];
	}

	private Scanner openFile(String filePath) {
		Scanner inputStream = null;
		try{
			return inputStream = new Scanner(new FileInputStream(filePath));
		}catch(FileNotFoundException e){
			System.out.println("Problem opening the file.");
			System.exit(0);
			return null;
		}
	}
	
	private String getParameterValueFromXmlFileLine(String fileLine, String parameterStartField, String parameterEndField){
		return fileLine.substring(parameterStartField.length(), fileLine.length()-parameterEndField.length());
	}
}