package dataManagePackage;
import java.math.BigDecimal;
import java.util.ArrayList;

import dataManagePackage.Receipt.Receipt;


public class Taxpayer {
	private String name;
	private String afm;
	private FamilyStatus familyStatus;
	private double income;
	private double basicTax;
	private double taxIncrease;
	private double taxDecrease;
	private double totalTax;
	private ArrayList<Receipt> receipts;
	
	public Taxpayer(String name, String afm, String familyStatus, String income){
		this.name = name;
		this.afm = afm;
		this.familyStatus = FamilyStatus.getFamilyStatus(familyStatus.toLowerCase());
		this.income = Double.parseDouble(income);
		setBasicTaxBasedOnFamilyStatus();
		taxIncrease = 0;
		taxDecrease = 0;
		
		receipts = new ArrayList<Receipt>();
	}
	
	private void setBasicTaxBasedOnFamilyStatus(){
		
		basicTax = calculateTax(income);
		
		
		totalTax = basicTax;
	}
	
	public double calculateTax(double totalIncome) {
		
		double tax;
		int[]incomeLimits = familyStatus.getTaxStats().	getIncomeLimits();
		Double[][] taxRates = familyStatus.getTaxStats().getCalculateTaxStatsMap().get(familyStatus.getFamilyStatus());
		
		if (totalIncome < incomeLimits[0]){
			tax = (taxRates[0][1]/100) * totalIncome;
		}
		else if (totalIncome < incomeLimits[1]){
			tax = taxRates[1][0] + ((taxRates[1][1]/100) * (totalIncome-incomeLimits[0]));
		}
		else if (totalIncome < incomeLimits[2]){
			tax = taxRates[2][0] + ((taxRates[2][1]/100) * (totalIncome-incomeLimits[1]));
		}
		else if (totalIncome < incomeLimits[3]){
			tax = taxRates[3][0] + ((taxRates[3][1]/100) * (totalIncome-incomeLimits[2]));
		}
		else{
			tax = taxRates[4][0] + ((taxRates[4][1]/100) * (totalIncome-incomeLimits[3]));
		}
		
		return tax;
	}
	
	
	
	public String toString(){
		return "Name: "+name
				+"\nAFM: "+afm
				+"\nStatus: "+familyStatus.getFamilyStatus()
				+"\nIncome: "+String.format("%.2f", income)
				+"\nBasicTax: "+String.format("%.2f", basicTax)
				+"\nTaxIncrease: "+String.format("%.2f", taxIncrease)
				+"\nTaxDecrease: "+String.format("%.2f", taxDecrease);
	}
	
	public Receipt getReceipt(int receiptID){
		return receipts.get(receiptID);
	}
	
	public ArrayList<Receipt> getReceiptsArrayList(){
		return receipts;
	}
	
	public String[] getReceiptsList(){
		String[] receiptsList = new String[receipts.size()];
		
		int c = 0;
		for (Receipt receipt : receipts){
			receiptsList[c++] = receipt.getId() + " | " + receipt.getDate() + " | " + receipt.getAmount();
		}	
		return receiptsList;
	}
	
	
	public double getKindOfReceiptsTotalAmount(String kindOfReceipt){
		double kindOfReceiptsTotalAmount = 0;
		
		for (Receipt receipt : receipts){
			if (receipt.getKind().equals(kindOfReceipt)){
				kindOfReceiptsTotalAmount += receipt.getAmount();
			}
		}
		return (new BigDecimal(kindOfReceiptsTotalAmount).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
	}
	
	
	public double getTotalReceiptsAmount(){
		double totalReceiptsAmount = 0;
		
		for (Receipt receipt : receipts){
			totalReceiptsAmount += receipt.getAmount();
		}
		
		return (new BigDecimal(totalReceiptsAmount).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
	}
	
	public String getName(){
		return name;
	}
	
	public String getAFM(){
		return afm;
	}
	
	public FamilyStatus getFamilyStatus(){
		return familyStatus;
	}
	
	public double getIncome(){
		return (new BigDecimal(income).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
	}
	
	public double getBasicTax(){
		return (new BigDecimal(basicTax).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
	}
	
	public double getTaxInxrease(){
		return (new BigDecimal(taxIncrease).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
	}
	
	public double getTaxDecrease(){
		return (new BigDecimal(taxDecrease).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
	}
	
	public double getTotalTax(){
		return (new BigDecimal(totalTax).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
	}
	
	public void addReceiptToList(Receipt receipt){
		receipts.add(receipt);
		
		calculateTaxpayerTaxIncreaseOrDecreaseBasedOnReceipts();
	}
	
	public void removeReceiptFromList(int index){
		receipts.remove(index);
		
		calculateTaxpayerTaxIncreaseOrDecreaseBasedOnReceipts();
	}
	
	public void calculateTaxpayerTaxIncreaseOrDecreaseBasedOnReceipts(){
		double totalReceiptsAmount = 0;
		for (Receipt receipt : receipts){
			totalReceiptsAmount += receipt.getAmount();
		}
		
		taxIncrease = 0;
		taxDecrease = 0;
		if ((totalReceiptsAmount/(double)income) < 0.2){
			taxIncrease = basicTax * 0.08;
		}
		else if ((totalReceiptsAmount/(double)income) < 0.4){
			taxIncrease = basicTax * 0.04;
		}
		else if ((totalReceiptsAmount/(double)income) < 0.6){
			taxDecrease = basicTax * 0.15;
		}
		else{
			taxDecrease = basicTax * 0.30;
		}
		
		totalTax = basicTax + taxIncrease - taxDecrease;
	}
}