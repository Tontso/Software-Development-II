package dataManagePackage;

import java.util.HashMap;
import java.util.Map;

public class TaxStats {
	
	String familyStatus;

	private Map<String, Double[][]> calculateTaxStatsMap = new HashMap<>();
	private int[] smallerThan = new int[4];
	
	
	public TaxStats (String familyStatus) {
		this.familyStatus = familyStatus;
		intitCalculateTaxStat(familyStatus);	
	}

	
	private void intitCalculateTaxStat(String familyStatus) {
		
		switch(familyStatus.toLowerCase()){
		case("married filing jointly"):
			smallerThan[0] = 36080;
			smallerThan[1] = 90000;
			smallerThan[2] = 143350;
			smallerThan[3] = 254240;
			calculateTaxStatsMap.put("married filing jointly", new Double[][]{
				{0.0, 5.35},
				{1930.28, 7.05},
				{5731.64, 7.05},
				{9492.82, 7.85},
				{18197.69, 9.85}});
			break;
			
		case("married filing separately"):
			smallerThan[0] = 18040;
			smallerThan[1] = 71680;
			smallerThan[2] = 90000;
			smallerThan[3] = 127120;
			calculateTaxStatsMap.put("married filing separately", new Double[][]{
				{0.0, 5.35},
				{965.14, 7.05},
				{4746.76, 7.85},
				{6184.88, 7.85},
				{9098.80, 9.85}});
			break;
			
		case("single"):
			smallerThan[0] = 24680;
			smallerThan[1] = 81080;
			smallerThan[2] = 90000;
			smallerThan[3] = 152540;
			calculateTaxStatsMap.put("single", new Double[][]{
				{0.0, 5.35},
				{1320.38, 7.05},
				{5296.58, 7.85},
				{5996.80, 7.85},
				{10906.19, 9.85}});
			break;
			
		case("head of household"):
			smallerThan[0] = 30390;
			smallerThan[1] = 90000;
			smallerThan[2] = 122110;
			smallerThan[3] = 203390;
			calculateTaxStatsMap.put("head of household", new Double[][]{
				{0.0, 5.35},
				{1625.87, 7.05},
				{5828.38, 7.05},
				{8092.13, 7.85},
				{14472.61, 9.85}});
			break;
		}
		
	}


	public Map<String, Double[][]> getCalculateTaxStatsMap() {
		return calculateTaxStatsMap;
	}


	public int[] getSmallerThan() {
		return smallerThan;
	}


	public String getFamilyStatus() {
		return familyStatus;
	}
	
	
}
