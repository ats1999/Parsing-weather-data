import java.io.File;

import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import edu.duke.DirectoryResource;
import edu.duke.FileResource;

public class Humidity extends Temperature {

	void testLowestHumidityInManyFiles() {
		try {
			CSVRecord lowestSoFar=lowestHumidityInManyFiles();
			System.out.println("Lowest humidity in the file is at "+lowestSoFar.get("Humidity")+"Time is:"+lowestSoFar.get("DateUTC"));
		}catch(Exception e) {
			System.out.println("hi");
			e.printStackTrace();
		}
	}
	
	/**
	 * this method find the lowest humidity in a file.
	 * @return CSVRecod containing lowest humidity in a file.
	 */
	public CSVRecord lowestHumidityInManyFiles() {
		CSVRecord lowestSoFar=null,currentHum=null;
		DirectoryResource dr=new DirectoryResource();
		CSVParser parser;
		
		try {
			for(File f:dr.selectedFiles()) {
				FileResource fr=new FileResource(f);
				parser=fr.getCSVParser();
				currentHum=lowestHumidityInFile(parser);
				lowestSoFar=getLowestHumidityOfTwo(lowestSoFar,currentHum);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return lowestSoFar;
	}
	
	/**
	 * this method test lowestHumidityInFile() method.
	 */
	void testLowestHumidityInFile() {
		FileResource fr=new FileResource();
		CSVParser parser=fr.getCSVParser();
		CSVRecord report=lowestHumidityInFile(parser);
		
		try {
			System.out.println("Lowest humidity in the file is at "+report.get("Humidity")+"Time is:"+report.get("DateUTC"));
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * This method find lowest humidity from two CSVRecord.
	 * @param lowestHumSoFar CSVRecord with lowest humidity.
	 * @param lowestCurr CSVRecord with current humidity.
	 * @return
	 */
	public CSVRecord getLowestHumidityOfTwo(CSVRecord lowestHumSoFar,CSVRecord lowestCurr) {
		double lowestSoFar = 0,lowCurr = 0;
		// if lowestHumSoFar is null(first time)
		if(lowestHumSoFar==null) {
			lowestHumSoFar=lowestCurr;
		}
		else
		// check is temperature in both record not equals to "N/A"
		if((lowestCurr.get("Humidity").equals(("N/A")))) {
			
		}else {
			try {
				lowestSoFar=Double.parseDouble(lowestHumSoFar.get("Humidity"));
				lowCurr=Double.parseDouble(lowestCurr.get("Humidity"));
			}catch(Exception e) {
				e.printStackTrace();
			}
			
			// check maximum of these
			if(lowestSoFar>lowCurr)
				lowestHumSoFar=lowestCurr;
		}

		return lowestHumSoFar;
	}
	/**
	 * This method find the lowest humidity in the file.
	 * @param parser this helps in iterating over the records.
	 * @return return lowest humidity of the file.
	 */
	public CSVRecord lowestHumidityInFile(CSVParser parser) {
		CSVRecord lowestHumSoFar=null;
		for(CSVRecord record:parser) {
			lowestHumSoFar=getLowestHumidityOfTwo(lowestHumSoFar,record);
		}
		return lowestHumSoFar;
	}
	
}
