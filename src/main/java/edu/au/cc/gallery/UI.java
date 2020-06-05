package edu.au.cc.gallery;

import java.util.Scanner;
import java.sql.SQLException;
import java.lang.NumberFormatException;
import java.util.InputMismatchException;
import java.util.ArrayList;

public class UI {

	public	Scanner sc = new Scanner(System.in);
	UserAdmin ua = new UserAdmin();
	
	public String getInput(String question) {
	
		System.out.println(question +": ");
		return sc.nextLine();
	}

	public void getMainMenu() throws SQLException {
		boolean active = true;
		do {
	
			System.out.println("1) List Users");
			System.out.println("2) Add User");
			System.out.println("3) Edit Users");
			System.out.println("4) Delete User");
			System.out.println("5) S3 Bucket Menu");
			System.out.println("6) Quit");
			System.out.print("Enter Command> ");
		
			int response = 0;
			
			try {
				response = Integer.parseInt(sc.nextLine());
			} catch (NumberFormatException ex) {
				System.out.println("Invalid input.");
			} catch (InputMismatchException ex) {	
				System.out.println("Invalid input.");
			}

			switch (response) {
				case 1: list();
					break;
				case 2: add();
					break;
				case 3: edit();
					break;
				case 4: delete();
					break;
				case 5: bucketMenu();
					break;
				case 6: System.out.println("Bye.");
					active = false;
					System.exit(0);
					break;
				default : System.out.println("Invalid Selection.");
			}
		} while (active);
	}

	public void list() throws SQLException {
		ArrayList<String> al = new ArrayList<>();
		al = ua.listUsers();
		System.out.println("username\tpassword\tfull name");
		System.out.println("-----------------------------");
		al.forEach((s) -> System.out.println(s));
	}

	public void add() throws SQLException {

		//Always send all 3 parameters, even if user doesnt want to change one. useradmin will handle empty strings
		String username;
		String password;
		String fullName;

		username = getInput("What username to add?");
		password = getInput("What password to use?");
		fullName = getInput("What is the full name?");
		ua.addUser(username, password, fullName);
	}

	public void edit() throws SQLException{

                String username;
                String password;
                String fullName;

                username = getInput("What username to edit?");
                password = getInput("What password to edit? [press enter for no change]");
                fullName = getInput("What is the new full name?[press enter for no change]");
		ua.editUser(username, password, fullName);

	}

	public void delete() throws SQLException {
		String username;

		username = getInput("What username to delete?");
		ua.deleteUser(username);
	}


	        public void bucketMenu() {

                boolean active = true;
		S3ACLAdmin s3a = new S3ACLAdmin();

                do {
                        System.out.println("All operations rely on Setting a Bucket name (option 1) first.");
                        System.out.println("------------------------");
                        System.out.println("1) Set Current Bucket");
                        System.out.println("2) List Keys in Bucket");
                        System.out.println("3) Show ACL by Key");
                        System.out.println("4) Modify ACL Menu");
                        System.out.println("5) Quit");
                        System.out.println("Enter Command>");

                        int response = 0;
                        
                        try {
                                response = Integer.parseInt(sc.nextLine());
                        } catch (NumberFormatException ex) {
                                System.out.println("Invalid input.");
                        } catch (InputMismatchException ex) {
                                System.out.println("Invalid input.");
                        }

                        switch (response) {
                                case 1: bucket(s3a);
                                        break;
                                case 2: list(s3a);
                                        break;
                                case 3: showACL(s3a);
                                        break;
                                case 4: aclMenu();
                                        break;
                                case 5: System.out.println("Bye.");
                                        active = false;
                                        System.exit(0);
                                        break;
                                default : System.out.println("Invalid Selection.");
                        }
                } while (active);
        }

	public void bucket(S3ACLAdmin s3a) {
	
		String bucketName;
		bucketName = getInput("What bucket to work with?");
		s3a.setBucket(bucketName);
	}

	public void list(S3ACLAdmin s3a) {

		System.out.println(s3a.listKeys());
	}

	public void showACL(S3ACLAdmin s3a) {

		String key;
		key = getInput("What key to check ACL of?");
		System.out.println(s3a.showACL(key));
	}


	public void aclMenu() {

		System.out.println("Modify ACL Menu (Not Implemented)");
		System.out.println("---------------");
                System.out.println("1) Enable/Disable Public Access");
                System.out.println("2) Add User Access Rights");
                System.out.println("3) Modify User Access Rights");
                System.out.println("4) Quit");
                System.out.println("Enter Command>");

	}
}
