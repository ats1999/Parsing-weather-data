import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import edu.duke.FileResource;

public class Temperature {

	/**
	 * This method calls averageTemperatureWithHighHumidityInFile() method to get average temperature in a file.
	 * after finding the average temperature, it just print it.
	 */
	void testAverageTemperatureWithHighHumidityInFile() {
		FileResource fr=new FileResource();
		CSVParser parser=null;
		try {
			parser=fr.getCSVParser();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		double avg=averageTemperatureWithHighHumidityInFile( parser,80);
		if(avg==-1) {
			System.out.println("No temperatures with that humidity");
		}else {
			System.out.println("Average Temp when high Humidity is:"+avg);
		}
	}

	/**
	 * This method find the average temperature with a humidity constraint.
	 * @param parser is a CSVParser.
	 * @param value temperature need to be considered only when 'humidity>=value'..
	 * @return return the average, In case when there is no such humidity greater than value return -1.
	 */
	double averageTemperatureWithHighHumidityInFile(CSVParser parser,int value) {
		double avg=-1;
		int count=0;
		for(CSVRecord record:parser) {
			// if Humidity is valid.
			count++;
			try {
				if(record.get("Humidity").equals("N/A")==false) {
					// if Humidity>=value
					if(Double.parseDouble(record.get("Humidity"))>=value) {
						// if so add it to the avg.						
						avg+=Double.parseDouble(record.get("TemperatureF"));
					}
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return avg/count;
	}
	
	/**
	 * This method call averageTemperatureInFile() method.
	 * This method print the average temperature in the file.
	 */
	void testAverageTemperatureInFile() {
		FileResource fr=new FileResource();
		CSVParser parser=null;
		try {
			parser=fr.getCSVParser();
		}catch(Exception e) {
			e.printStackTrace();
		}
		double avg=averageTemperatureInFile(parser);
		
		System.out.println("Average temperature in the file is "+avg);
	}
	
	/**
	 * This method find average temperature in a file.
	 * @param parser is a CSVParser.
	 * @return double value(Average Temperature)
	 */
	double averageTemperatureInFile(CSVParser parser) {
		double avgTemp=0;
		int count=0;
		for(CSVRecord record:parser) {
			try {
				count++;
				avgTemp+=Double.parseDouble(record.get("TemperatureF"));
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return (avgTemp/count);
	}
}
