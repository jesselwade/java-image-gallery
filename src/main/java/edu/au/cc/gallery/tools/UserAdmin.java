package edu.au.cc.gallery;

import java.util.Scanner;

public class UserAdmin {


	public addUser() {
		
		private String username;
		private String password;
		private String fullName;
		private String response;
		Scanner sc = new Scanner(System.in);

		System.out.println("Enter username: ");
		username = sc.next();

		System.out.println("Enter password: ");
		password = sc.next();

		System.out.println("Enger Full Name: ");
		fullName = sc.nextLine();

		System.out.println("Adding user: " + username + " with password: " + password 
				+ " and full name: " +fullName;
		System.out.println("Is this correct? (Y/N)";
		response = sc.next();


	}

	public listUsers() {
		//TODO
	}

	public editUser() {
		//TODO
	}

	public deleteUser() {
		//TODO
	}
}
