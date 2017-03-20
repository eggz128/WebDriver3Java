package chapter3;

import java.util.Date;
import java.text.SimpleDateFormat;

public class HelloWorld {

	static String myName = "John Doe"; // String variable (field)
	static final int TIMES_TO_PRINT = 10; // constant

	public static void main(String[] args) { 
		// Code inside the main() method is executed first
		System.out.println("Hello World!");
		for (int i = 0; i < TIMES_TO_PRINT; i++) { // loop
			printGreeting(); // call another method
			if (i > 3) { 	// if for loop's i is greater than 3
				break; 		// break out of the for loop early
			} 				// else continue with next loop iteration
		}
	}

	private static void printGreeting() { //void = does not return anything
		System.out.println("And specifically " + myName +
				" on this fine " + getDay() + " morning");
	}

	private static String getDay() { //String = this method will return a string
		Date now = new Date();
		SimpleDateFormat simpleDateformat = new SimpleDateFormat("EEEE");
		String todaysDay = simpleDateformat.format(now);
		return todaysDay;
	}
}
