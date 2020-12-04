package outputManagePackage;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;

import javax.swing.JOptionPane;

import dataManagePackage.Database;
import dataManagePackage.Taxpayer;
import dataManagePackage.Receipt.Receipt;
import fileWords.FileWords;

public class OutputSystem {
	
	private static OutputSystem outputSystem = null;
	
	
	private OutputSystem() {
	
	}
	
	public static OutputSystem getOutputSystem() {
		if(outputSystem == null) {
			outputSystem = new OutputSystem();
		}
		return outputSystem;
	}
	

	public void saveUpdatedTaxpayerTxtInputFile(String filePath, int taxpayerIndex, FileWords fileWords){
		String[] parsersFirstWord = fileWords.getFirstWordParsers();
		String[] parsersSecondWord = fileWords.getSecondParsers();
		String[] secondWord = fileWords.getSecondWord(); 
		String[] firstWord = fileWords.getFirstWord();
		
		PrintWriter outputStream = openFile(filePath);
		
		Taxpayer taxpayer = Database.getDatabase().getTaxpayerFromArrayList(taxpayerIndex);
		outputStream.println(fileWords.makeString(firstWord[0], parsersFirstWord)+taxpayer.getName()+fileWords.makeString(secondWord[0],parsersSecondWord));
		outputStream.println(fileWords.makeString(firstWord[1], parsersFirstWord)+taxpayer.getAFM()+fileWords.makeString(secondWord[1],parsersSecondWord));
		outputStream.println(fileWords.makeString(firstWord[2], parsersFirstWord)+taxpayer.getFamilyStatus().getFamilyStatus()+fileWords.makeString(secondWord[2],parsersSecondWord));
		outputStream.println(fileWords.makeString(firstWord[3], parsersFirstWord)+taxpayer.getIncome()+fileWords.makeString(secondWord[3],parsersSecondWord));
		
		if (taxpayer.getReceiptsArrayList().size() > 0){
			outputStream.println();
			outputStream.println(firstWord[13]);
			outputStream.println();
			
			for (Receipt receipt : taxpayer.getReceiptsArrayList()){
				outputStream.println(fileWords.makeString(firstWord[4], parsersFirstWord)+receipt.getId()+fileWords.makeString(secondWord[4],parsersSecondWord));
				outputStream.println(fileWords.makeString(firstWord[5], parsersFirstWord)+receipt.getDate()+fileWords.makeString(secondWord[5],parsersSecondWord));
				outputStream.println(fileWords.makeString(firstWord[6], parsersFirstWord)+receipt.getKind()+fileWords.makeString(secondWord[6],parsersSecondWord));
				outputStream.println(fileWords.makeString(firstWord[7], parsersFirstWord)+receipt.getAmount()+fileWords.makeString(secondWord[7],parsersSecondWord));
				outputStream.println(fileWords.makeString(firstWord[8], parsersFirstWord)+receipt.getCompany().getName()+fileWords.makeString(secondWord[8],parsersSecondWord));
				outputStream.println(fileWords.makeString(firstWord[9], parsersFirstWord)+receipt.getCompany().getCountry()+fileWords.makeString(secondWord[9],parsersSecondWord));
				outputStream.println(fileWords.makeString(firstWord[10], parsersFirstWord)+receipt.getCompany().getCity()+fileWords.makeString(secondWord[10],parsersSecondWord));
				outputStream.println(fileWords.makeString(firstWord[11], parsersFirstWord)+receipt.getCompany().getStreet()+fileWords.makeString(secondWord[11],parsersSecondWord));
				outputStream.println(fileWords.makeString(firstWord[12], parsersFirstWord)+receipt.getCompany().getNumber()+fileWords.makeString(secondWord[12],parsersSecondWord));			
				outputStream.println();
			}
			
			outputStream.println(firstWord[14]);
		}
		
		outputStream.close();
	}
	
	
	private PrintWriter openFile(String filePath) {
		PrintWriter outputStream = null;
		try{
			outputStream = new PrintWriter(new FileOutputStream(filePath));
		}catch(FileNotFoundException e){
			System.out.println("Problem opening: "+filePath);
		}
		return outputStream;
	}
	

	
	public void saveTaxpayerInfoToLogFile(String folderSavePath, int taxpayerIndex, FileWords fileWords){
		
		
		Taxpayer taxpayer = Database.getDatabase().getTaxpayerFromArrayList(taxpayerIndex);
		
		PrintWriter outputStream = null;
		try
		{
			outputStream = new PrintWriter(new FileOutputStream(folderSavePath+"//"+taxpayer.getAFM()+fileWords.getSaveAs()));
		}
		catch(FileNotFoundException e)
		{
			System.out.println("Problem opening: "+folderSavePath+"//"+taxpayer.getAFM()+fileWords.getSaveAs());
		}
		
		outputStream.println(fileWords.makeSaveString(0, taxpayer.getName()));
		outputStream.println(fileWords.makeSaveString(1, taxpayer.getAFM()));
		outputStream.println(fileWords.makeSaveString(2, taxpayer.getIncome()+""));
		outputStream.println(fileWords.makeSaveString(3, taxpayer.getBasicTax()+""));
		if (taxpayer.getTaxInxrease()!=0){
			outputStream.println(fileWords.makeSaveString(4, taxpayer.getTaxInxrease()+""));
		}else{
			outputStream.println(fileWords.makeSaveString(5, taxpayer.getTaxDecrease()+""));
		}
		outputStream.println(fileWords.makeSaveString(6, taxpayer.getTotalTax()+""));
		outputStream.println(fileWords.makeSaveString(7, taxpayer.getTotalReceiptsAmount()+""));
		outputStream.println(fileWords.makeSaveString(8, taxpayer.getKindOfReceiptsTotalAmount("Entertainment")+""));
		outputStream.println(fileWords.makeSaveString(9, taxpayer.getKindOfReceiptsTotalAmount("Basic")+""));
		outputStream.println(fileWords.makeSaveString(10, taxpayer.getKindOfReceiptsTotalAmount("Travel")+""));
		outputStream.println(fileWords.makeSaveString(11, taxpayer.getKindOfReceiptsTotalAmount("Health")+""));
		outputStream.println(fileWords.makeSaveString(12, taxpayer.getKindOfReceiptsTotalAmount("Other")+""));
		
		outputStream.close();
		
		JOptionPane.showMessageDialog(null, "Η αποθήκευση ολοκληρώθηκε", "Μήνυμα", JOptionPane.INFORMATION_MESSAGE);
	}
	
}
