package Classes;

import java.util.Scanner;

import Classes.Stack;

public class StackUI {

	public static void main(String[] args) {
		Scanner sc= new Scanner(System.in);
		Stack stk = new Stack();
		while (true) {
			System.out.println(
					"Please choose an action to perform on the stack\r\n" + 
					"-----------------------\r\n" + 
					"1- Push\r\n" + 
					"2- Print\r\n" + 
					"3- Pop\r\n" + 
					"4- Size\r\n" +
					"5- Peek\r\n" +
					"6- Exit\r\n");  
			int choice=0;
			try {
				choice = sc.nextInt();
			}
			catch(Exception e){
				System.out.println("Invalid input");
				sc.next();
				continue;
			}
			if (choice == 1) {
				System.out.println("Enter element to be pushed");
				sc.nextLine();
				stk.push(sc.nextLine());
				stk.print();
			}
			else if (choice == 2) {
				stk.print();
			}
			else if (choice == 3) {
				try {
					System.out.println(stk.pop());
					stk.print();
				}
				catch(Exception e)
				{
					System.out.println("Stack is Empty !!");
				}
				
			}
			else if (choice == 4) {
				System.out.println(stk.size());
			}
			else if (choice == 5) {
				try {
					System.out.println(stk.peek());
					stk.print();
				}
				catch(Exception e)
				{
					System.out.println("Stack is Empty !!");
				}
			}
			else if (choice == 6) {
				break;
			}
			else {
				System.out.println("Invalid Input !!");
			}
		}
		sc.close();

	}

}
