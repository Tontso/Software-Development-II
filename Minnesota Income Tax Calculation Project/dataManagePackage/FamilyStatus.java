package dataManagePackage;

public class FamilyStatus {

	public final static FamilyStatus SINGLE = new FamilyStatus("single");
	public final static FamilyStatus HEAD_OF_HOUSEHOLD = new FamilyStatus("head of household");
	public final static FamilyStatus MARRIED_FILLING_JOINTLY = new FamilyStatus("married filing jointly");
	public final static FamilyStatus MARRIED_FILLING_SEPARATELY = new FamilyStatus("married filing separately");
	private static FamilyStatus[] allStatus = {SINGLE, HEAD_OF_HOUSEHOLD, MARRIED_FILLING_JOINTLY, MARRIED_FILLING_SEPARATELY};
		
	
	private String familyStatus;
	private TaxStats taxStats;
	
	
	private FamilyStatus(String familyStatus) {
		this.familyStatus = familyStatus;
		this.taxStats = new TaxStats(familyStatus);
	}
	

	public static FamilyStatus getFamilyStatus(String familyStatus) {
		for (FamilyStatus stat : allStatus) {
			if(familyStatus.toLowerCase().equals(stat.getFamilyStatus())) {
				return stat;
			}
		}
		//Does not exist this kind of family status
		System.out.println("Does not exist this kind of family status:"+familyStatus);
		System.exit(0);
		return null;
	}

	
	public String getFamilyStatus() {
		return this.familyStatus;
	}
	
	
	public TaxStats getTaxStats() {
		return taxStats;
	}


	public void setFamilyStatus(String familyStatus) {
		this.familyStatus = familyStatus;
	}


	
}
