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
		
		PrintWriter outputStream = openFile(filePath);
		
		Taxpayer taxpayer = Database.getDatabase().getTaxpayerFromArrayList(taxpayerIndex);
		saveUpdatedTaxpayerData(fileWords, outputStream, taxpayer);
		saveUpdatedTaxpayersReceiptsData(fileWords, outputStream, taxpayer);
		
		outputStream.close();
	}
	
	
	private void saveUpdatedTaxpayerData(FileWords fileWords, PrintWriter outputStream, Taxpayer taxpayer) {
		outputStream.println(fileWords.makeUpdateString(0, taxpayer.getName()));
		outputStream.println(fileWords.makeUpdateString(1, taxpayer.getAFM()));
		outputStream.println(fileWords.makeUpdateString(2, taxpayer.getFamilyStatus().getFamilyStatus()));
		outputStream.println(fileWords.makeUpdateString(3, taxpayer.getIncome()+""));
		
	}
	
	
	private void saveUpdatedTaxpayersReceiptsData(FileWords fileWords, PrintWriter outputStream, Taxpayer taxpayer) {
		if (taxpayer.getReceiptsArrayList().size() > 0){
			outputStream.println();
			outputStream.println(fileWords.getFirstWord()[13]);
			outputStream.println();
			
			for (Receipt receipt : taxpayer.getReceiptsArrayList()){
				outputStream.println(fileWords.makeUpdateString(4, receipt.getId()));
				outputStream.println(fileWords.makeUpdateString(5, receipt.getDate()));
				outputStream.println(fileWords.makeUpdateString(6, receipt.getKind()));
				outputStream.println(fileWords.makeUpdateString(7, receipt.getAmount()+""));
				outputStream.println(fileWords.makeUpdateString(8, receipt.getCompany().getName()));
				outputStream.println(fileWords.makeUpdateString(9, receipt.getCompany().getCountry()));
				outputStream.println(fileWords.makeUpdateString(10, receipt.getCompany().getCity()));
				outputStream.println(fileWords.makeUpdateString(11, receipt.getCompany().getStreet()));
				outputStream.println(fileWords.makeUpdateString(12, receipt.getCompany().getNumber()));			
				outputStream.println();
			}
			
			outputStream.println(fileWords.getFirstWord()[14]);
		}
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
		
		PrintWriter outputStream = openFile(folderSavePath+"//"+taxpayer.getAFM()+fileWords.getSaveAs());
		
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
