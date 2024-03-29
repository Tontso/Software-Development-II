package data_manage_package;

import java.util.HashMap;
import java.util.Map;

public class TaxStats {
	
	String familyStatus;

	private Map<String, Double[][]> calculateTaxStatsMap = new HashMap<>();
	private int[] incomeLimits = new int[4];
	
	
	public TaxStats (String familyStatus) {
		this.familyStatus = familyStatus;
		intitCalculateTaxStat(familyStatus);	
	}

	
	private void intitCalculateTaxStat(String familyStatus) {
		
		switch(familyStatus.toLowerCase()){
		case("married filing jointly"):
			incomeLimits[0] = 36080;
			incomeLimits[1] = 90000;
			incomeLimits[2] = 143350;
			incomeLimits[3] = 254240;
			calculateTaxStatsMap.put("married filing jointly", new Double[][]{
				{0.0, 5.35},
				{1930.28, 7.05},
				{5731.64, 7.05},
				{9492.82, 7.85},
				{18197.69, 9.85}});
			break;
			
		case("married filing separately"):
			incomeLimits[0] = 18040;
			incomeLimits[1] = 71680;
			incomeLimits[2] = 90000;
			incomeLimits[3] = 127120;
			calculateTaxStatsMap.put("married filing separately", new Double[][]{
				{0.0, 5.35},
				{965.14, 7.05},
				{4746.76, 7.85},
				{6184.88, 7.85},
				{9098.80, 9.85}});
			break;
			
		case("single"):
			incomeLimits[0] = 24680;
			incomeLimits[1] = 81080;
			incomeLimits[2] = 90000;
			incomeLimits[3] = 152540;
			calculateTaxStatsMap.put("single", new Double[][]{
				{0.0, 5.35},
				{1320.38, 7.05},
				{5296.58, 7.85},
				{5996.80, 7.85},
				{10906.19, 9.85}});
			break;
			
		case("head of household"):
			incomeLimits[0] = 30390;
			incomeLimits[1] = 90000;
			incomeLimits[2] = 122110;
			incomeLimits[3] = 203390;
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


	public int[] getIncomeLimits() {
		return incomeLimits;
	}


	
}
