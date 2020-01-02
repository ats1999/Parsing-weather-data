
import edu.duke.*;

import java.io.File;
import java.util.Scanner;

import org.apache.commons.csv.*;
/**
 * This class find maximum temperature in a day.
 * This class find maximum temperature in a file(CSV).
 * This class find maximum temperature in a year. 
 * @author Rahul kumar
 *
 */
public class ParsingWeatherData extends Hottest{
	/**
	 * This is the main() method. It is not performing any task of it's own.
	 * It is used here to call testing method.
	 * @param args unused
	 */
	public static void main(String[] args) {
		ParsingWeatherData operation=new ParsingWeatherData();
		
		operation.test();
	}
	/**
	 * This method is not a functional method.
	 * It is used for user interaction.
	 * This method ask user to enter a choice each time user want to perform some task.
	 * According to the user input this method call operation() method to call respective method to perform task. 
	 */
	@SuppressWarnings("resource")
	void test() {
		int option=10;
		Scanner scn=new Scanner(System.in);
		
		while(option!=0) {
			System.out.println("Enter you choice...");
			System.out.println("Enter 1 for hottest");
			System.out.println("Enter 2 for coldest");
			System.out.println("Enter 3 for humidity");
			System.out.println("Enter 4 temperature");
			System.out.println("Enter 0 to Quit.");
			option=scn.nextInt();
			System.out.flush();
			//call respective method
			switch(option) {
			case 1:testHottest();
				break;
			case 2:testColdest();
				break;
			case 3:testhumidity();
				break;
			case 4:testTemperature();
				break;
			case 0: System.exit(0);
			default:System.out.println("Kindly enter correct answer");
			}
		}
	}
	
	/**
	 * This is a utility function for user interaction.
	 */
	void testHottest() {
		// clear console
		
		System.out.flush();
		int option=10;
		Scanner scn=new Scanner(System.in);
		while(option!=0) {
			System.out.println("Enter your choice...");
			System.out.println("1 for hottest hour in a day");
			System.out.println("2 for hottest hour in a year");
			System.out.println("0 fro Quit...");
			
			option=scn.nextInt();
			System.out.flush();
			switch(option) {
			case 1:testHottestInDay();
				break;
			case 2:testhottestInManyDays();
				break;
			default:System.out.println("Kindly enter correct answer");
			}
		}
		
	}
	
	
	void testColdest() {
		// clear console
		ParsingWeatherData operation=new ParsingWeatherData();
				int option=10;
				Scanner scn=new Scanner(System.in);
				while(option!=0) {
					System.out.println("Enter your choice...");
					System.out.println("1 for coldest hour in a day");
					System.out.println("2 for coldest in a year");
					System.out.println("0 fro Quit...");
					
					option=scn.nextInt();
					System.out.flush();
					switch(option) {
					case 1:testColdestHourInFile();
						break;
					case 2:operation.coldestInYear();
						break;
					default:System.out.println("Kindly enter correct answer");
					}
				}
	}
	
	void testhumidity() {
		// clear console
				System.out.flush();
				int option=10;
				Scanner scn=new Scanner(System.in);
				while(option!=0) {
					System.out.println("Enter your choice...");
					System.out.println("1 for lowest humidity  in a day");
					System.out.println("2 for lowest humidity in a year");
					System.out.println("0 fro Quit...");
					
					option=scn.nextInt();
					System.out.flush();
					switch(option) {
					case 1:testLowestHumidityInFile();
						break;
					case 2:testLowestHumidityInManyFiles();
						break;
					default:System.out.println("Kindly enter correct answer");
					}
				}
	}
	
	void testTemperature() {
		// clear console
				int option=10;
				Scanner scn=new Scanner(System.in);
				while(option!=0) {
					System.out.println("Enter your choice...");
					System.out.println("1 for average temperature with high humidity  in a day");
					System.out.println("2 for average temperature in a day");
					System.out.println("0 fro Quit...");
					
					option=scn.nextInt();
					System.out.flush();
					switch(option) {
					case 1:testAverageTemperatureWithHighHumidityInFile();
						break;
					case 2:testAverageTemperatureInFile();
						break;
					default:System.out.println("Kindly enter correct answer");
					}
				}
	}
}
