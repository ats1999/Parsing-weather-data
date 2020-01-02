import java.io.File;

import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import edu.duke.DirectoryResource;
import edu.duke.FileResource;

public class Hottest extends Coldest {

	/**
	 * This method return a CSVRecord which contains maximum temperature.
	 * @param parser
	 * @return the record have maximum temperature.
	 */
	public CSVRecord hottestHourInFile(CSVParser parser) {
		//start largestSoFar as nothing
		CSVRecord largestSoFar=null;
		// for each row(current row) in CSV file
		for(CSVRecord currentRow:parser) {
			largestSoFar=getLargestOfTwo(largestSoFar,currentRow);
		}
		return largestSoFar;
	}
	
	/**
	 * This method takes to CSVRecord parameter and calculate maximum temperature from both records.
	 * @param largestSoFar CSVRecord with largest temperature
	 * @param current CSVRecord with current temperature
	 * @return largestSoFar CSVrecord with maximum temperature. 
	 */
	public CSVRecord getLargestOfTwo(CSVRecord largestSoFar,CSVRecord current) {
		
		double currentTemp=0,largestTemp=0;
		if(largestSoFar==null) {
			largestSoFar=current;
		}
		else {
			try {

				 currentTemp=Double.parseDouble(largestSoFar.get("TemperatureF"));
				 largestTemp=Double.parseDouble(current.get("TemperatureF"));
			}catch(Exception e) {
				System.out.println("Not parsing from string to double\n");
				e.printStackTrace();
			}
			
			//if currentRow's temperature > largestTemp
			if(currentTemp>largestTemp) {
				// if so update largestSoFar to currentRow
				largestSoFar=current;
			}
		}

		return largestSoFar;
	}
	
	/**
	 * This method call hottestHourInFile() method to find hottest hour in a file.
	 * This method does not take any parameter and does not return anything.
	 */
	void testHottestInDay() {
				CSVParser parser=null;
				FileResource fr=new FileResource();
				try {
					 parser=fr.getCSVParser();
				}catch(Exception e) {
					e.printStackTrace();
				}				
				// create a CSVRecord to store largestTemp
				CSVRecord largest=hottestHourInFile(parser);
				try {
					// print the output
					System.out.println("Hottest temperature is "+largest.get("TemperatureF")+" Time is "+largest.get("TimeEST")+"PM");
				}catch(Exception e) {
					e.printStackTrace();
				}

	}
	
	/**
	 * This method find the file with largest temperature.
	 * This method call hottestHourInFile() method to find hottest hour in a particular file.
	 * @return a CSVRecord containing hottest temperature in that file.
	 */
	public CSVRecord hottestDayInYear() {
		CSVRecord largestSoFar=null;
		try {
			DirectoryResource dr=new DirectoryResource();
			// iterate over files
			for(File f:dr.selectedFiles()) {
				FileResource fr=new FileResource(f);
				//get the hottest hour of the file
				CSVRecord current=hottestHourInFile(fr.getCSVParser());
				largestSoFar=getLargestOfTwo(largestSoFar,current);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return largestSoFar;
	}
	
	/**
	 * This method call hottestDayInYear() method to find the hottest day in a year.
	 */
	
	void testhottestInManyDays() {
		// create a CSVRecord and and store returned CSVRecord into it.
		CSVRecord report=hottestDayInYear();
		try {
			// print the result
			System.out.println("Hottest temperature is "+report.get("TemperatureF")+" Time is "+report.get("DateUTC")+"PM");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
