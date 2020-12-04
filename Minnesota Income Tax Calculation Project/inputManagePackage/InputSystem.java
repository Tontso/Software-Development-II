package inputManagePackage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

import dataManagePackage.Database;
import dataManagePackage.Taxpayer;
import dataManagePackage.Receipt.Receipt;
import fileWords.FileWords;
import fileWords.TxtFileWords;
import fileWords.XmlFileWords;

public class InputSystem {
	
	private static InputSystem inputSystem = null;
	
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
				FileWords fileWords = new TxtFileWords();
				loadTaxpayerDataFromFileIntoDatabase(afmInfoFilesFolderPath, afmInfoFile, fileWords);
			}
			else if (afmInfoFile.endsWith(".xml")){
				FileWords fileWords = new XmlFileWords();
				loadTaxpayerDataFromFileIntoDatabase(afmInfoFilesFolderPath, afmInfoFile, fileWords);
			}
		}
	}
	
	private void loadTaxpayerDataFromFileIntoDatabase(String afmInfoFileFolderPath, String afmInfoFile, FileWords fileWords){
		String[] parsersFirstWord = fileWords.getFirstWordParsers();
		String[] parsersSecondWord = fileWords.getSecondParsers();
		String[] secondWord = fileWords.getSecondWord(); 
		String[] firstWord = fileWords.getFirstWord();

		Scanner inputStream = openFile(afmInfoFileFolderPath+"\\"+afmInfoFile);	
		String taxpayerName = getParameterValueFromXmlFileLine(inputStream.nextLine(), fileWords.makeString(firstWord[0], parsersFirstWord), fileWords.makeString(secondWord[0], parsersSecondWord));
		String taxpayerAFM = getParameterValueFromXmlFileLine(inputStream.nextLine(), fileWords.makeString(firstWord[1], parsersFirstWord),  fileWords.makeString(secondWord[1], parsersSecondWord));
		
		String taxpayerStatus = getParameterValueFromXmlFileLine(inputStream.nextLine(),  fileWords.makeString(firstWord[2], parsersFirstWord), fileWords.makeString(secondWord[2], parsersSecondWord));
		String taxpayerIncome = getParameterValueFromXmlFileLine(inputStream.nextLine(),  fileWords.makeString(firstWord[3], parsersFirstWord), fileWords.makeString(secondWord[3], parsersSecondWord));
		Taxpayer newTaxpayer = new Taxpayer(taxpayerName, taxpayerAFM, taxpayerStatus, taxpayerIncome);
		
		String fileLine;
		while (inputStream.hasNextLine()){
			fileLine = inputStream.nextLine();
			if (fileLine.equals("")) continue;
			if (fileLine.indexOf("Receipts:")!=-1) continue;
			if (fileLine.indexOf("<Receipts>")!=-1) continue;
			if (fileLine.indexOf("</Receipts>")!=-1) break;
			
			String receiptID = getParameterValueFromXmlFileLine(fileLine, fileWords.makeString(firstWord[4], parsersFirstWord), fileWords.makeString(secondWord[4], parsersSecondWord));
			String receiptDate = getParameterValueFromXmlFileLine(inputStream.nextLine(), fileWords.makeString(firstWord[5], parsersFirstWord), fileWords.makeString(secondWord[5], parsersSecondWord));
			String receiptKind = getParameterValueFromXmlFileLine(inputStream.nextLine(), fileWords.makeString(firstWord[6], parsersFirstWord), fileWords.makeString(secondWord[6], parsersSecondWord));
			String receiptAmount = getParameterValueFromXmlFileLine(inputStream.nextLine(), fileWords.makeString(firstWord[7], parsersFirstWord), fileWords.makeString(secondWord[7], parsersSecondWord));
			String receiptCompany = getParameterValueFromXmlFileLine(inputStream.nextLine(), fileWords.makeString(firstWord[8], parsersFirstWord), fileWords.makeString(secondWord[8], parsersSecondWord));
			String receiptCountry = getParameterValueFromXmlFileLine(inputStream.nextLine(), fileWords.makeString(firstWord[9], parsersFirstWord), fileWords.makeString(secondWord[9], parsersSecondWord));
			String receiptCity = getParameterValueFromXmlFileLine(inputStream.nextLine(), fileWords.makeString(firstWord[10], parsersFirstWord), fileWords.makeString(secondWord[10], parsersSecondWord));
			String receiptStreet = getParameterValueFromXmlFileLine(inputStream.nextLine(), fileWords.makeString(firstWord[11], parsersFirstWord), fileWords.makeString(secondWord[11], parsersSecondWord));
			String receiptNumber = getParameterValueFromXmlFileLine(inputStream.nextLine(),  fileWords.makeString(firstWord[12], parsersFirstWord), fileWords.makeString(secondWord[12], parsersSecondWord));
			Receipt newReceipt = new Receipt(receiptKind, receiptID, receiptDate, receiptAmount, receiptCompany, receiptCountry, receiptCity, receiptStreet, receiptNumber);
			
			newTaxpayer.addReceiptToList(newReceipt);
		}
		
		Database.getDatabase().addTaxpayerToList(newTaxpayer);
	}
	

	private Scanner openFile(String filePath) {
		Scanner inputStream = null;
		try{
			return inputStream = new Scanner(new FileInputStream(filePath));
		}catch(FileNotFoundException e){
			System.out.println("Problem with opening the file.");
			System.exit(0);
			return null;
		}
	}
	
	private String getParameterValueFromXmlFileLine(String fileLine, String parameterStartField, String parameterEndField){
		return fileLine.substring(parameterStartField.length(), fileLine.length()-parameterEndField.length());
	}
}