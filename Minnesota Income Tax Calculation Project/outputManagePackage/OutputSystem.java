package outputManagePackage;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;

import javax.swing.JOptionPane;

import dataManagePackage.Database;
import dataManagePackage.Taxpayer;
import dataManagePackage.Receipt.Receipt;

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
	

	public void saveUpdatedTaxpayerTxtInputFile(String filePath, int taxpayerIndex){
		PrintWriter outputStream = null;
		try
		{
			outputStream = new PrintWriter(new FileOutputStream(filePath));
		}
		catch(FileNotFoundException e)
		{
			System.out.println("Problem opening: "+filePath);
		}
		
		Taxpayer taxpayer = Database.getDatabase().getTaxpayerFromArrayList(taxpayerIndex);
		outputStream.println("Name: "+taxpayer.getName());
		outputStream.println("AFM: "+taxpayer.getAFM());
		outputStream.println("Status: "+taxpayer.getFamilyStatus().getFamilyStatus());
		outputStream.println("Income: "+taxpayer.getIncome());
		
		if (taxpayer.getReceiptsArrayList().size() > 0){
			outputStream.println();
			outputStream.println("Receipts:");
			outputStream.println();
			
			for (Receipt receipt : taxpayer.getReceiptsArrayList()){
				outputStream.println("Receipt ID: "+receipt.getId());
				outputStream.println("Date: "+receipt.getDate());
				outputStream.println("Kind: "+receipt.getKind());
				outputStream.println("Amount: "+receipt.getAmount());
				outputStream.println("Company: "+receipt.getCompany().getName());
				outputStream.println("Country: "+receipt.getCompany().getCountry());
				outputStream.println("City: "+receipt.getCompany().getCity());
				outputStream.println("Street: "+receipt.getCompany().getStreet());
				outputStream.println("Number: "+receipt.getCompany().getNumber());
				outputStream.println();
			}
		}
		
		outputStream.close();
	}
	
	public void saveUpdatedTaxpayerXmlInputFile(String filePath, int taxpayerIndex){
		PrintWriter outputStream = null;
		try
		{
			outputStream = new PrintWriter(new FileOutputStream(filePath));
		}
		catch(FileNotFoundException e)
		{
			System.out.println("Problem opening: "+filePath);
		}
		
		Taxpayer taxpayer = Database.getDatabase().getTaxpayerFromArrayList(taxpayerIndex);
		outputStream.println("<Name> "+taxpayer.getName()+" </Name>");
		outputStream.println("<AFM> "+taxpayer.getAFM()+" </AFM>");
		outputStream.println("<Status> "+taxpayer.getFamilyStatus().getFamilyStatus()+" </Status>");
		outputStream.println("<Income> "+taxpayer.getIncome()+" </Income>");
		
		if (taxpayer.getReceiptsArrayList().size() > 0){
			outputStream.println();
			outputStream.println("<Receipts>");
			outputStream.println();
			
			for (Receipt receipt : taxpayer.getReceiptsArrayList()){
				outputStream.println("<ReceiptID> "+receipt.getId()+" </ReceiptID>");
				outputStream.println("<Date> "+receipt.getDate()+" </Date>");
				outputStream.println("<Kind> "+receipt.getKind()+" </Kind>");
				outputStream.println("<Amount> "+receipt.getAmount()+" </Amount>");
				outputStream.println("<Company> "+receipt.getCompany().getName()+" </Company>");
				outputStream.println("<Country> "+receipt.getCompany().getCountry()+" </Country>");
				outputStream.println("<City> "+receipt.getCompany().getCity()+" </City>");
				outputStream.println("<Street> "+receipt.getCompany().getStreet()+" </Street>");
				outputStream.println("<Number> "+receipt.getCompany().getNumber()+" </Number>");
				outputStream.println();
			}
			
			outputStream.println("</Receipts>");
		}
		
		outputStream.close();
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
