package data_manage_package;
import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.List;

import file_words.TxtFileWords;
import file_words.XmlFileWords;
import input_manage_package.InputSystem;
import output_manage_package.OutputSystem;

public class Database {
	
	private static Database databaseInstance = null;
	
	private  String taxpayersInfoFilesPath;
	private  ArrayList<Taxpayer> taxpayersArrayList = new ArrayList<Taxpayer>();
	
	
	private Database() {
	}
	
	
	public static Database getDatabase() {
		if(databaseInstance == null) {
			databaseInstance = new Database();
		}
		return databaseInstance;
	}
	
	
	public void setTaxpayersInfoFilesPath(String taxpayersInfoFilesPath){
		this.taxpayersInfoFilesPath = taxpayersInfoFilesPath;
	}
	
	
	public String getTaxpayersInfoFilesPath(){
		return taxpayersInfoFilesPath;
	}
	
	
	public void proccessTaxpayersDataFromFilesIntoDatabase(String afmInfoFilesFolderPath, List<String> taxpayersAfmInfoFiles){
		InputSystem.getInputSystem().addTaxpayersDataFromFilesIntoDatabase(afmInfoFilesFolderPath, taxpayersAfmInfoFiles);
	}
	
	
	public void addTaxpayerToList(Taxpayer taxpayer){
		taxpayersArrayList.add(taxpayer);
	}
	
	
	public int getTaxpayersArrayListSize(){
		return taxpayersArrayList.size();
	}
	
	
	public Taxpayer getTaxpayerFromArrayList(int index){
		return taxpayersArrayList.get(index);
	}
	
	
	public void removeTaxpayerFromArrayList(int index){
		taxpayersArrayList.remove(index);
	}
	
	
	public String getTaxpayerNameAfmValuesPairList(int index){
		Taxpayer taxpayer = taxpayersArrayList.get(index);
		return taxpayer.getName() + " | " + taxpayer.getAFM();
	}
	
	
	public String[] getTaxpayersNameAfmValuesPairList(){
		String[] taxpayersNameAfmValuesPairList = new String[taxpayersArrayList.size()];
		
		int c = 0;
		for (Taxpayer taxpayer : taxpayersArrayList){
			taxpayersNameAfmValuesPairList[c++] = taxpayer.getName() + " | " + taxpayer.getAFM();
		}
		
		return taxpayersNameAfmValuesPairList;
	}
	
	
	public void updateTaxpayerInputFile(int index){
		File taxpayersInfoFilesPathFileObject = new File(taxpayersInfoFilesPath);
		FilenameFilter fileNameFilter = new FilenameFilter(){
            public boolean accept(File dir, String name) {
               return (name.toLowerCase().endsWith("_info.txt") || name.toLowerCase().endsWith("_info.xml"));
            }
         };
		
		for (File file : taxpayersInfoFilesPathFileObject.listFiles(fileNameFilter)){
			if (!file.getName().contains(taxpayersArrayList.get(index).getAFM())) continue;
			
			if (file.getName().toLowerCase().endsWith(".txt")){
				OutputSystem.getOutputSystem().saveUpdatedTaxpayerInputFile(file.getAbsolutePath(), index, TxtFileWords.getTxtFileWords());
			}
			if (file.getName().toLowerCase().endsWith(".xml")){
				OutputSystem.getOutputSystem().saveUpdatedTaxpayerInputFile(file.getAbsolutePath(), index, XmlFileWords.getXmlFileWords());
			}
			break;
		}
	}	
}