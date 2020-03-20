package Classes;

import java.util.Scanner;

public class UIApplication {
	
	public static boolean isNumeric(String str) {
		return str != null && str.matches("[-+]?\\d*\\.?\\d+");
	}
	// Check for parenthesis matching and detect characters other than digits
	static boolean checkInput(String s) {
		int counter = 0;
		for(int i = 0; i < s.length(); i++) {
			if(s.charAt(i) == '(') { counter++;}
			else if(s.charAt(i) == ')') {
				counter --;
				if(counter<0) { return false;}
			}
			else if(!isNumeric(Character.toString(s.charAt(i))) 
					&& s.charAt(i) != ',' && s.charAt(i) != ' '&& s.charAt(i) != '-') {
				return false;
			}
		}
		if(counter == 0) {
			return true;
		}
		return false;
	}

	public static String readFromUser(Scanner sc) {  
		System.out.println("Insert the polynomial terms in the form:\r\n" + 
				"(coeff1, exponent1), (coeff2, exponent2), ..: ");  
		String input;
		sc.nextLine();
		//if(!sc.hasNextLine()) {sc.close(); return "";}
		input = sc.nextLine();
		while(!checkInput(input)) {
			System.out.println("Enter it in the correct format, please: ");
			input = sc.nextLine();
		}
		return input;
	}
	
	public static int [][] readPolynomial(Scanner sc)
	{
		String input = readFromUser(sc);
		if(input == "") {return null;}
		String[] filteringTerms = input.split(",");
		// Splitting --> Replacing --> Trim --> Parsing
		while(filteringTerms.length%2 != 0) {
			input = readFromUser(sc);
			if(input == "") {return null;}
			filteringTerms = input.split(",");
		}
		int terms[][] = new int[filteringTerms.length/2][2];
		for (int i = 0; i < filteringTerms.length; i+=2) {
			filteringTerms[i] = filteringTerms[i].replace('(', ' ');
			filteringTerms[i] = filteringTerms[i].replace(')', ' ');
			filteringTerms[i] = filteringTerms[i].trim();
			terms[i/2][0] = Integer.parseInt(filteringTerms[i]);
			
			filteringTerms[i+1] = filteringTerms[i+1].replace('(', ' ');
			filteringTerms[i+1] = filteringTerms[i+1].replace(')', ' ');
			filteringTerms[i+1] = filteringTerms[i+1].trim();
			terms[i/2][1] = Integer.parseInt(filteringTerms[i+1]);
		}
		
		// Sorting
		int n = terms.length; 
        for (int i = 1; i < n; ++i) { 
        	int tmpCoff = terms[i][0]; 
        	int tmpExp = terms[i][1];
            int j = i - 1; 
            
            while (j >= 0 && terms[j][1] < tmpExp) { 
                terms[j + 1][0] = terms[j][0];
                terms[j + 1][1] = terms[j][1];
                j = j - 1; 
            } 
            terms[j + 1][0] = tmpCoff;
            terms[j + 1][1] = tmpExp;
        }
        return terms;
	}
	
	public static boolean checkChar(char c) {
		if(c == 'A' || c == 'a' || c == 'B' || c == 'b' || c == 'C' || c == 'c') { return true;}
		return false;
	}
	
	public static void main(String[] args) {
		PolynomialSolver ps = new PolynomialSolver();
		Scanner sc= new Scanner(System.in);
		boolean out = false;
		while (!out) {
			System.out.println(
					"Please choose an action\r\n" + 
					"-----------------------\r\n" + 
					"1- Set a polynomial variable\r\n" + 
					"2- Print the value of a polynomial variable\r\n" + 
					"3- Add two polynomials\r\n" + 
					"4- Subtract two polynomials\r\n" + 
					"5- Multiply two polynomials\r\n"+
					"6- Evaluate a polynomial at some point\r\n" + 
					"7- Clear a polynomial variable\r\n"+
					"8- Exit");  
			int choice=0;
			try {
				choice = sc.nextInt();
				
			}
			catch(Exception e){
				System.out.println("Invalid input");
				sc.next();
				continue;
			}
			if(choice == 1) {
				System.out.println("Insert the variable name: A, B or C");
				char poly = sc.next().charAt(0);
				while(!checkChar(poly)) {
					System.out.println("Variable not found\r\nInsert the variable name: A, B or C");
					poly = sc.next().charAt(0);
				}
				int[][] terms = readPolynomial(sc);
				ps.setPolynomial(poly, terms);
				System.out.println("Polynomial "+Character.toUpperCase(poly)+" is set");
			}
			else if (choice == 2) {
				System.out.println("Insert the variable name: A, B or C");
				char poly = sc.next().charAt(0);
				while(!ps.isSet(poly) || ((!checkChar(poly) && poly != 'R' && poly != 'r'))) {
					System.out.println("Variable not set\r\nInsert the variable name: A, B or C");
					poly = sc.next().charAt(0);
				}
				ps.print(poly);
			}
			else if (choice == 3) {
				System.out.println("Insert first operand variable name: A, B or C");
				char poly1 = sc.next().charAt(0);
				while(!ps.isSet(poly1) || !checkChar(poly1)) {
					System.out.println("Variable not set\r\nInsert first operand variable name: A, B or C");
					poly1 = sc.next().charAt(0);
				}
				System.out.println("Insert the second operand variable name: A, B or C");
				char poly2 = sc.next().charAt(0);
				while(!ps.isSet(poly2) || !checkChar(poly2)) {
					System.out.println("Variable not set\r\nInsert the second operand variable name: A, B or C");
					poly2 = sc.next().charAt(0);
				}
				int[][] terms = ps.add(poly1, poly2);
				String result = "Result set in R: ";
				for (int i = 0; i < terms.length; i++) {
					result += "("+terms[i][0] + ", " + terms[i][1] +"),";
				}
				result+="\b";
				System.out.println(result);
			}
			else if (choice == 4) {
				System.out.println("Insert first operand variable name: A, B or C");
				char poly1 = sc.next().charAt(0);
				while(!ps.isSet(poly1) || !checkChar(poly1)) {
					System.out.println("Variable not set\r\nInsert first operand variable name: A, B or C");
					poly1 = sc.next().charAt(0);
				}
				System.out.println("Insert the second operand variable name: A, B or C");
				char poly2 = sc.next().charAt(0);
				while(!ps.isSet(poly2) || !checkChar(poly2)) {
					System.out.println("Variable not set\r\nInsert the second operand variable name: A, B or C");
					poly2 = sc.next().charAt(0);
				}
				int[][] terms = ps.subtract(poly1, poly2);
				String result = "Result set in R: ";
				for (int i = 0; i < terms.length; i++) {
					result += "("+terms[i][0] + ", " + terms[i][1] +"),";
				}
				result+="\b";
				System.out.println(result);
			}
			else if (choice == 5) {
				System.out.println("Insert first operand variable name: A, B or C");
				char poly1 = sc.next().charAt(0);
				while(!ps.isSet(poly1) || !checkChar(poly1)) {
					System.out.println("Variable not set\r\nInsert first operand variable name: A, B or C");
					poly1 = sc.next().charAt(0);
				}
				System.out.println("Insert the second operand variable name: A, B or C");
				char poly2 = sc.next().charAt(0);
				while(!ps.isSet(poly2) || !checkChar(poly2)) {
					System.out.println("Variable not set\r\nInsert the second operand variable name: A, B or C");
					poly2 = sc.next().charAt(0);
				}
				int[][] terms = ps.multiply(poly1, poly2);
				String result = "Result set in R: ";
				for (int i = 0; i < terms.length; i++) {
					result += "("+terms[i][0] + ", " + terms[i][1] +"),";
				}
				result+="\b";
				System.out.println(result);
			}
			else if(choice == 6) {
				System.out.println("Insert the variable name: A, B or C");
				char poly = sc.next().charAt(0);
				while(!checkChar(poly)) {
					System.out.println("Variable not found\r\nInsert the variable name: A, B or C");
					poly = sc.next().charAt(0);
				}
				float value = 0;
				try {
					 value = sc.nextFloat();
				}
				catch (Exception e) {
					System.out.println("Invalid number !!");
					continue;
				}
				float result = ps.evaluatePolynomial(poly, value);
				System.out.println(result);
			}
			else if(choice == 7) {
				System.out.println("Insert the variable name: A, B or C");
				char poly = sc.next().charAt(0);
				while(!ps.isSet(poly) || !checkChar(poly)) {
					System.out.println("Variable not set\r\nInsert the variable name: A, B or C");
					poly = sc.next().charAt(0);
				}
				ps.clearPolynomial(poly);
			}
			else if(choice == 8) {
				out = true;
			}
		}
		sc.close();
		
	}

}
