package Classes;

import java.util.Scanner;

public class ApplicationUI {

	public static void main(String[] args) {
		Scanner sc= new Scanner(System.in);
		ExpressionEvaluator ee = new ExpressionEvaluator();
		System.out.println("Hello to Expression Evaluator Application");
		System.out.println("Press any button to start");
		while (true) {
			sc.nextLine();
			System.out.println("Please enter expression Or type back to exit");  
			String expression;
			try {
				
				expression = sc.nextLine();
				if(expression == "\n") { sc.nextLine();}
				if (expression == "back") { break;}
			}
			catch(Exception e){
				System.out.println(e);
				continue;
			}
			try {
				System.out.println("postifix");	
				expression = ee.infixToPostfix(expression);
				System.out.println(expression);
			}
			catch (Exception e) {
				System.out.println("Invalid Expression Entered");
				continue;
			}
			for (int i = 0; i < expression.length(); i++) {
				if (Character.isLetter(expression.charAt(i))) {
					System.out.println("Please enter value of " + expression.charAt(i));
					int choice=0;
					try {
						choice = sc.nextInt();
						expression = expression.replaceAll(expression.charAt(i)+"", choice + "");
					}
					catch(Exception e){
						System.out.println("Invalid input");
						sc.next();
						continue;
					}
				}
			}
			try {				
				System.out.println("Result: " + ee.evaluate(expression));
			}
			catch (Exception e) {
				System.out.println("The Expression can not be evaluated!!");
				continue;
			}
		}
		sc.close();

	}

}
