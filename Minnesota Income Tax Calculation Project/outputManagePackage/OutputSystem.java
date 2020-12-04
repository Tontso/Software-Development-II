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
	

	
	public void saveTaxpayerInfoToTxtLogFile(String folderSavePath, int taxpayerIndex){
		Taxpayer taxpayer = Database.getDatabase().getTaxpayerFromArrayList(taxpayerIndex);
		
		PrintWriter outputStream = null;
		try
		{
			outputStream = new PrintWriter(new FileOutputStream(folderSavePath+"//"+taxpayer.getAFM()+"_LOG.txt"));
		}
		catch(FileNotFoundException e)
		{
			System.out.println("Problem opening: "+folderSavePath+"//"+taxpayer.getAFM()+"_LOG.txt");
		}
		
		outputStream.println("Name: "+taxpayer.getName());
		outputStream.println("AFM: "+taxpayer.getAFM());
		outputStream.println("Income: "+taxpayer.getIncome());
		outputStream.println("Basic Tax: "+taxpayer.getBasicTax());
		if (taxpayer.getTaxInxrease()!=0){
			outputStream.println("Tax Increase: "+taxpayer.getTaxInxrease());
		}else{
			outputStream.println("Tax Decrease: "+taxpayer.getTaxDecrease());
		}
		outputStream.println("Total Tax: "+taxpayer.getTotalTax());
		outputStream.println("Total Receipts Amount: "+taxpayer.getTotalReceiptsAmount());
		outputStream.println("Entertainment: "+taxpayer.getKindOfReceiptsTotalAmount("Entertainment"));
		outputStream.println("Basic: "+taxpayer.getKindOfReceiptsTotalAmount("Basic"));
		outputStream.println("Travel: "+taxpayer.getKindOfReceiptsTotalAmount("Travel"));
		outputStream.println("Health: "+taxpayer.getKindOfReceiptsTotalAmount("Health"));
		outputStream.println("Other: "+taxpayer.getKindOfReceiptsTotalAmount("Other"));
		
		outputStream.close();
		
		JOptionPane.showMessageDialog(null, "Η αποθήκευση ολοκληρώθηκε", "Μήνυμα", JOptionPane.INFORMATION_MESSAGE);
	}
	
	public void saveTaxpayerInfoToXmlLogFile(String folderSavePath, int taxpayerIndex){
		Taxpayer taxpayer = Database.getDatabase().getTaxpayerFromArrayList(taxpayerIndex);
		
		PrintWriter outputStream = null;
		try
		{
			outputStream = new PrintWriter(new FileOutputStream(folderSavePath+"//"+taxpayer.getAFM()+"_LOG.xml"));
		}
		catch(FileNotFoundException e)
		{
			System.out.println("Problem opening: "+folderSavePath+"//"+taxpayer.getAFM()+"_LOG.xml");
		}
		
		outputStream.println("<Name> "+taxpayer.getName()+" </Name>");
		outputStream.println("<AFM> "+taxpayer.getAFM()+" </AFM>");
		outputStream.println("<Income> "+taxpayer.getIncome()+" </Income>");
		outputStream.println("<BasicTax> "+taxpayer.getBasicTax()+" </BasicTax>");
		if (taxpayer.getTaxInxrease()!=0){
			outputStream.println("<TaxIncrease> "+taxpayer.getTaxInxrease()+" </TaxIncrease>");
		}else{
			outputStream.println("<TaxDecrease> "+taxpayer.getTaxDecrease()+" </TaxDecrease>");
		}
		outputStream.println("<TotalTax> "+taxpayer.getTotalTax()+" </TotalTax>");
		outputStream.println("<Receipts> "+taxpayer.getTotalReceiptsAmount()+" </Receipts>");
		outputStream.println("<Entertainment> "+taxpayer.getKindOfReceiptsTotalAmount("Entertainment")+" </Entertainment>");
		outputStream.println("<Basic> "+taxpayer.getKindOfReceiptsTotalAmount("Basic")+" </Basic>");
		outputStream.println("<Travel> "+taxpayer.getKindOfReceiptsTotalAmount("Travel")+" </Travel>");
		outputStream.println("<Health> "+taxpayer.getKindOfReceiptsTotalAmount("Health")+" </Health>");
		outputStream.println("<Other> "+taxpayer.getKindOfReceiptsTotalAmount("Other")+" </Other>");
		
		outputStream.close();
		
		JOptionPane.showMessageDialog(null, "Η αποθήκευση ολοκληρώθηκε", "Μήνυμα", JOptionPane.INFORMATION_MESSAGE);
	}
	
	
}
