package input_manage_package;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

import data_manage_package.Database;
import data_manage_package.Taxpayer;
import data_manage_package.receipt.Receipt;
import file_words.FileWords;
import file_words.TxtFileWords;
import file_words.XmlFileWords;

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
				loadTaxpayerDataFromFileIntoDatabase(afmInfoFilesFolderPath, afmInfoFile, TxtFileWords.getTxtFileWords());
			}
			else if (afmInfoFile.endsWith(".xml")){
				loadTaxpayerDataFromFileIntoDatabase(afmInfoFilesFolderPath, afmInfoFile, XmlFileWords.getXmlFileWords());
			}
		}
	}
	
	
	private void loadTaxpayerDataFromFileIntoDatabase(String afmInfoFileFolderPath, String afmInfoFile, FileWords fileWords){

		Scanner inputStream = openFile(afmInfoFileFolderPath+"\\"+afmInfoFile);
		
		Taxpayer newTaxpayer = laodTaxpayer(fileWords, inputStream);	
		loadReceipts(fileWords, newTaxpayer, inputStream);
		
		Database.getDatabase().addTaxpayerToList(newTaxpayer);
	}
	
	
	private Taxpayer laodTaxpayer(FileWords fileWords, Scanner inputStream) {		
		String taxpayerName = fileWords.loadDataString(0,  inputStream.nextLine());
		String taxpayerAFM = fileWords.loadDataString(1,  inputStream.nextLine());	
		String taxpayerStatus = fileWords.loadDataString(2,  inputStream.nextLine());
		String taxpayerIncome = fileWords.loadDataString(3,  inputStream.nextLine());
		
		return new Taxpayer(taxpayerName, taxpayerAFM, taxpayerStatus, taxpayerIncome);
	}
	
	
	private void loadReceipts(FileWords fileWords, Taxpayer taxpayer,  Scanner inputStream) {
		String fileLine;
		while (inputStream.hasNextLine()){
			fileLine = inputStream.nextLine();
			if (fileLine.equals("")) continue;
			if (fileLine.indexOf("Receipts:")!=-1) continue;
			if (fileLine.indexOf("<Receipts>")!=-1) continue;
			if (fileLine.indexOf("</Receipts>")!=-1) break;
			
			String receiptID = fileWords.loadDataString(4,  fileLine);
			String receiptDate = fileWords.loadDataString(5,  inputStream.nextLine());
			String receiptKind = fileWords.loadDataString(6,  inputStream.nextLine());
			String receiptAmount = fileWords.loadDataString(7,  inputStream.nextLine());
			String receiptCompany = fileWords.loadDataString(8,  inputStream.nextLine());
			String receiptCountry = fileWords.loadDataString(9,  inputStream.nextLine());
			String receiptCity = fileWords.loadDataString(10,  inputStream.nextLine());
			String receiptStreet = fileWords.loadDataString(11,  inputStream.nextLine());
			String receiptNumber = fileWords.loadDataString(12,  inputStream.nextLine());
			Receipt newReceipt = new Receipt(receiptKind, receiptID, receiptDate, receiptAmount, receiptCompany, receiptCountry, receiptCity, receiptStreet, receiptNumber);
			
			taxpayer.addReceiptToList(newReceipt);
		}
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
	
}