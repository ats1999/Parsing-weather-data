import java.io.File;

import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import edu.duke.DirectoryResource;
import edu.duke.FileResource;

public class Coldest extends Humidity{

	/**
	 * This method find coldest hour in a file.
	 * @param parser
	 * @return record with coldest hour.
	 */
	public CSVRecord coldestHourInFile(CSVParser parser) {
		CSVRecord coldest=null;
		
		try {
			for(CSVRecord current:parser) {
				coldest=getColdestOfTwo(coldest,current);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return coldest;
	}
	/**
	 * This method compare temperature of two CSVRecord .
	 * @param coldest CSVRecord with coldest temperature
	 * @param current CSVRecord with current temperature
	 * @return CSVRecord with minimum temperature of both.
	 */
	public CSVRecord getColdestOfTwo(CSVRecord coldest,CSVRecord current) {
		// get the coldest of these in 'coldest'
		if(coldest==null) {
			coldest=current;
		}
		else {
			
			try {
				// get both temperature in coldOne and currOne
				double coldOne=Double.parseDouble(coldest.get("TemperatureF"));
				double currOne=Double.parseDouble(current.get("TemperatureF"));
				
				if(coldOne>currOne) {
					coldest=current;
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
			
		}
		return coldest;
	}
	/**
	 * This method test coldestHourInFile() method.
	 * This method also print the information of the CSVRecord returned by the coldestHourInFile().
	 */
	void testColdestHourInFile() {
		FileResource fr=new FileResource();
		try {
			CSVParser parser=fr.getCSVParser();
			CSVRecord report=coldestHourInFile(parser);
			printReport(report);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * This method print a CSVRecord.
	 * @param record which have to be printed.
	 */
	void printReport(CSVRecord record) {
		try {
			System.out.println("Time:"+record.get("TimeEDT")+" ,Temperature: "+record.get("TemperatureF")+ record.get("DateUTC"));
		}catch(Exception e) {
			
			e.printStackTrace();
		}
		System.out.print("error");
	}
	
	/**
	 * This method test multiple file for temperature.
	 * @return file name which have least temperature, means fle name which have coldest temperature ever. 
	 */
	public String fileWithColdestTemperature() {
		CSVRecord coldestSoFar=null,currentTemp;
		
		String fileWithColdest=null;	// contains file name which have coldest temperature
		try {
			DirectoryResource dr=new DirectoryResource();
			CSVParser parser;
			// iterate over the files
			for(File f:dr.selectedFiles()) {
				FileResource fr=new FileResource(f); // create a FileResource
				parser=fr.getCSVParser();// create a parser
				currentTemp=coldestHourInFile(parser);
				
				if(fileWithColdest==null) {
					coldestSoFar=currentTemp;
					fileWithColdest=f.getName();
				}else {
					if(testTwoRecord(coldestSoFar,currentTemp)) {
						fileWithColdest=f.getName();
						coldestSoFar=currentTemp;
					}
				}	
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return fileWithColdest;
	}
	
	/**
	 * This method test coldest temperature of the two CSVRecord.
	 * @param coldestSoFar CSVRecord with coldest temperature.
	 * @param currentFile CSVRecord with current temperature.
	 * @return a boolean value, true if coldestSoFar<currentFile false otherwise.
	 */
	boolean testTwoRecord(CSVRecord coldestSoFar,CSVRecord currentFile) {
		
		boolean condition=false;
			try {
				// get both temperature in coldOne and currOne
				double coldOne=Double.parseDouble(coldestSoFar.get("TemperatureF"));
				double currOne=Double.parseDouble(currentFile.get("TemperatureF"));
				
				if((currOne!=-9999)&&(coldOne>currOne)) {
					condition=true;
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
			return condition;
	}
	
	void coldestInYear() {
		String file=fileWithColdestTemperature();
		//printFileInfo(file);
		System.out.println(file);
	}
	/**
	 * This method prints the information of the selected file.
	 * @param File name of which information need to be printed.
	 */
	void printFileInfo(String fileName) {
		DirectoryResource dr=new DirectoryResource();
		CSVParser parser;
		try {
			for(File f:dr.selectedFiles()) {
				if(f.getName()==fileName) {
					FileResource fr=new FileResource(f);
					parser=fr.getCSVParser();
					for(CSVRecord record:parser) {
						System.out.println(record.get("DateUTC")+"  "+record.get("TimeEST")+"  "+record.get("TemperatureF"));
					}
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
