package train;

import java.util.Scanner;

public class TryCatch {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		try {
			
			String userInput = sc.nextLine();
			Integer intTransform = Integer.parseInt(userInput);
			int result = 10/intTransform;
			
		}catch(NumberFormatException e) {
			System.out.println("Please put a valid number");
		}catch(ArithmeticException e) {
			System.out.println("Please dont divide by 0");
		}catch(Exception e) {
			System.out.println("Something unexpected occured: " + e.getMessage());
		}
		
		System.out.println("Program still works");
		
	}
	
	

}
