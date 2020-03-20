package Classes;

import Interfaces.IPolynomialSolver;

public class PolynomialSolver implements IPolynomialSolver {

	public singleLList A = new singleLList();
	public singleLList B = new singleLList();
	public singleLList C = new singleLList();
	private singleLList R = new singleLList();
	
	
	@Override
	public void setPolynomial(char poly, int[][] terms) {
		singleLList list = null ;
		list = arrayToLinkedList(terms);
		if (poly == 'A' || poly == 'a') 
		{
			A = list;
		}
		else if (poly == 'B' || poly == 'b') 
		{
			B = list;
		}
		else if (poly == 'C' || poly == 'c') 
		{
			C = list;
		}
		else {
			throw new RuntimeException();
		}
	}
	
	
	
	@Override
	public String print(char poly) {
		singleLList list;
		if (poly == 'A' || poly == 'a') {
			list = A;
		}
		else if (poly == 'B' || poly == 'b') {
			list = B;
		}
		else if (poly == 'C' || poly == 'c') {
			list = C;
		}
		else if (poly == 'R' || poly == 'r') {
			list = R;
		}
		else { throw new RuntimeException();}
		
		String polynomials = "";
		for(int i = 0; i < list.size(); i++)
		{
			int[] term = (int[])list.get(i); 
			String coff = term[0]+"";
			if (term[0] == 1) {
				coff = "";
			}
			if (term[1] != 0) {
				polynomials += coff + "X^" + term[1];
			}
			else {
				polynomials += coff;
			}
			if(i != list.size()-1) {
				polynomials += " + ";
			}
		}
		if(list.size()==0) { polynomials = "0";}
		System.out.println(polynomials);
		return polynomials;
	}

	@Override
	public void clearPolynomial(char poly) {
		if (poly == 'A' || poly == 'a') {
			A.clear();
		}
		else if (poly == 'B' || poly == 'b') {
			B.clear();
		}
		else if (poly == 'C' || poly == 'c') {
			C.clear();
		}
		else {
			throw new RuntimeException();
		}
	}

	@Override
	public float evaluatePolynomial(char poly, float value) {
		singleLList list = null ;
		float sum = 0;
		if (poly == 'A' || poly == 'a') 
		{
			list = A;
		}
		else if (poly == 'B' || poly == 'b') 
		{
			list = B;
		}
		else if (poly == 'C' || poly == 'c') 
		{
			list = C;
		}
		else {
			throw new RuntimeException();
		}
		for (int i = 0 ; i < list.size() ; i++) 
		{
			int[] term = (int[])list.get(i);
			if(value == 0 && term[1] < 0) { continue;}
			sum = (float) (sum + term[0] * Math.pow(value, term[1]));
		}
		
		return sum;
	}

	@Override
	public int[][] add(char poly1, char poly2) {
		singleLList firstOperand = null;
		singleLList secondOperand = null;
		R = new singleLList();
		// Determining first operand
		if (poly1 == 'A' || poly1 == 'a') 
		{
			firstOperand = A;
		}
		else if (poly1 == 'B' || poly1 == 'b') 
		{
			firstOperand = B;
		}
		else if (poly1 == 'C' || poly1 == 'c') 
		{
			firstOperand = C;
		}
		else 
		{ 
			throw new RuntimeException();
		}
		if(firstOperand.isEmpty()) {
			throw new RuntimeException();
		}
		
		// Determining second operand
		if (poly2 == 'A' || poly2 == 'a') 
		{
			secondOperand = A;
		}
		else if (poly2 == 'B' || poly2 == 'b') 
		{
			secondOperand = B;
		}
		else if (poly2 == 'C' || poly2 == 'c') 
		{
			secondOperand = C;
		}
		else 
		{ 
			throw new RuntimeException();
		}
		if(secondOperand.isEmpty()) {
			throw new RuntimeException();
		}
		for (int i = 0 ; i < firstOperand.size() ; i++) 
		{
			int[] term = new int[] {((int[])firstOperand.get(i))[0],((int[])firstOperand.get(i))[1]};
			R.add(term);
		}
		
		for (int j = 0 ; j < secondOperand.size() ; j++) 
		{
			int[] term1 = (int[])secondOperand.get(j);
			
			for(int k = 0 ; k < R.size() ; k++) 
			{
				int[] term2 = (int[])R.get(k);
				if (term1[1] == term2[1]) 
				{
					term2[0] = term1[0] + term2[0];
					break;
				}
				else if (term2[1] < term1[1]) 
				{
					int[] term3 = new int[] {term1[0], term1[1]};
					R.add(k, term3);
					break;
				}
				else if ( k == (secondOperand.size()-1)) 
				{
					R.add(term1);
					break;
				}
			}
		
		}
		for (int i = 0; i <R.size(); i++) {
			if(((int[])R.get(i))[0] == 0) {
				R.remove(i);
				i--;
			}
		}
		int[][] arr = linkedListToArray(R);
		print('R');
		return arr;
	}

	@Override
	public int[][] subtract(char poly1, char poly2) {
		singleLList firstOperand = null;
		singleLList secondOperand = null;
		R = new singleLList();
		// Determining first operand
		if (poly1 == 'A' || poly1 == 'a') 
		{
			firstOperand = A;
		}
		else if (poly1 == 'B' || poly1 == 'b') 
		{
			firstOperand = B;
		}
		else if (poly1 == 'C' || poly1 == 'c') 
		{
			firstOperand = C;
		}
		else 
		{ 
			throw new RuntimeException();
		}
		if(firstOperand.isEmpty()) {
			throw new RuntimeException();
		}
		// Determining second operand
		if (poly2 == 'A' || poly2 == 'a') 
		{
			secondOperand = A;
		}
		else if (poly2 == 'B' || poly2 == 'b') 
		{
			secondOperand = B;
		}
		else if (poly2 == 'C' || poly2 == 'c') 
		{
			secondOperand = C;
		}
		else 
		{ 
			throw new RuntimeException();
		}
		if(secondOperand.isEmpty()) {
			throw new RuntimeException();
		}
		
		for (int i = 0 ; i < firstOperand.size() ; i++) 
		{
			int[] term = new int[] {((int[])firstOperand.get(i))[0],((int[])firstOperand.get(i))[1]};
			R.add(term);
		}
		
		for (int j = 0 ; j < secondOperand.size() ; j++) 
		{
			int[] term1 = (int[])secondOperand.get(j);
			
			for(int k = 0 ; k < R.size() ; k++) 
			{
				int[] term2 = (int[])R.get(k);
				if (term1[1] == term2[1]) 
				{
					term2[0] = (-(term1[0]) + term2[0]);
					break;
				}
				else if (term2[1] < term1[1]) 
				{
					int[] term3 = new int[] {-(term1[0]), term1[1]};
					R.add(k, term3);
					break;
				}
				else if ( k == (secondOperand.size()-1)) 
				{
					R.add(new int[] {-term1[0], term1[1]});
					break;
				}
			} 
		}
		
		for (int i = 0; i <R.size(); i++) {
			if(((int[])R.get(i))[0] == 0) {
				R.remove(i);
				i--;
			}
		}
		
		
		int[] [] arr = linkedListToArray(R);
		print('R');
		return arr;
	}

	@Override
	public int[][] multiply(char poly1, char poly2) {
		
		singleLList firstOperand = null;
		singleLList secondOperand = null;
		R = new singleLList();
		// Determining first operand
		if (poly1 == 'A' || poly1 == 'a') {
			firstOperand = A;
		}
		else if (poly1 == 'B' || poly1 == 'b') {
			firstOperand = B;
		}
		else if (poly1 == 'C' || poly1 == 'c') {
			firstOperand = C;
		}
		else 
		{ 
			throw new RuntimeException();
		}
		if(firstOperand.isEmpty()) {
			throw new RuntimeException();
		}
		// Determining second operand
		if (poly2 == 'A' || poly2 == 'a') {
			secondOperand = A;
		}
		else if (poly2 == 'B' || poly2 == 'b') {
			secondOperand = B;
		}
		else if (poly2 == 'C' || poly2 == 'c') {
			secondOperand = C;
		}
		else 
		{ 
			throw new RuntimeException();
		}
		if(secondOperand.isEmpty()) {
			throw new RuntimeException();
		}
		
		// Operating the two polynomials
		for (int i = 0; i < firstOperand.size(); i++) {
			// Fetching the term in the first list
			int[] term1 = (int[])firstOperand.get(i);
			for (int j = 0; j < secondOperand.size(); j++) {
				// Fetching the term in the second list
				int[] term2 = (int[])secondOperand.get(j);
				// Multiply the two terms
				int exp = term1[1] + term2[1];
				int coff = term1[0] * term2[0];
				// Storing the result in R
				for (int k = 0; k < R.size(); k++) {
					int[] temp = (int[])R.get(k);
					// Check if the result term exponent already exists
					if(exp == temp[1]) {
						temp[0] = temp[0] + coff;
						break;
					}
					// Check if we reach a term of less degree than the result term
					// Then we insert our result term in this index
					else if (exp > temp[1]) {
						R.add(k, new int[] {coff, exp});
						break;
					}
					// Check if we reached the last node without finding our exponent
					// Then we add last in the list
					else if (k == R.size()-1) {
						R.add(new int[] {coff, exp});
						break;
					}
				}
				// If size was 0 It will not enter the loop
				if (R.size() == 0) {
					R.add(new int[] {coff, exp});
				}
			}
		}
		
		int [][] terms = linkedListToArray(R);
		print('R');
		return terms;
	}
	
	public int[][] linkedListToArray(singleLList list) {
		int n = list.size();
		int [][] terms = new int [n][2];
			for (int i = 0; i < n; i++) {
				int[] term = (int[])list.get(i);
				terms[i][0] = term[0];
				terms[i][1] = term[1];
			}
		return terms;
	}
	
	public boolean isSet(char poly) {
		if (poly == 'A' || poly == 'a') {
			return !A.isEmpty();
		}
		else if (poly == 'B' || poly == 'b') {
			return !B.isEmpty();
		}
		else if (poly == 'C' || poly == 'c') {
			return !C.isEmpty();
		}
		return false;
	}
	
	
	public singleLList arrayToLinkedList(int [][] terms) 
	{
		singleLList list1 = new singleLList();	
			for (int i = 0 ; i < terms.length ; i++) 
			{
				int[] newElement = new int[]{terms[i][0], terms[i][1]} ;
				list1.add(newElement);
			}
		return list1;
	}
}
