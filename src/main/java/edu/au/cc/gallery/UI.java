package edu.au.cc.gallery;

import java.util.Scanner;

public class UI {

	public static void main(String[] args) {

		mainMenu();
	}
	
	public static void mainMenu() {

		int response;
		Scanner sc = new Scanner(System.in);

		System.out.println("1) List Users");
		System.out.println("2) Add User");
		System.out.println("3) Edit Users");
		System.out.println("4) Delete User");
		System.out.println("5) Quit");
		System.out.println("Enter Command>");
		response = sc.nextInt();

		switch (response) {
			case 1: UserAdmin.listUsers();
				mainMenu();
				break;
			case 2: UserAdmin.addUser();
				mainMenu();
				break;
			case 3: UserAdmin.editUser();
				mainMenu();
				break;
			case 4: UserAdmin.deleteUser();
				mainMenu();
				break;
			case 5: System.out.println("Bye.");
				System.exit(1);
				break;
			default : System.out.println("Invalid Selection.");
				  mainMenu();
		}
	}
	
}
