package edu.au.cc.gallery;

import java.util.Scanner;
import java.sql.ResultSet;

public class UserAdmin {


	public static void addUser() {
		
		String username;
		String password;
		String fullName;
		String response;
		Scanner sc = new Scanner(System.in);

		System.out.println("Enter username: ");
		username = sc.nextLine();

		System.out.println("Enter password: ");
		password = sc.nextLine();

		System.out.println("Enger Full Name: ");
		fullName = sc.nextLine();

		System.out.println("Adding user: " + username + " with password: " + password 
				+ " and full name: " +fullName);
		System.out.println("Is this correct? (Y/N)");
		response = sc.nextLine();
		
		if (response == "Y" || response == "y") {
			DB db = new DB();
			db.connect();
			db.execute("insert into users (username, password, full_name) values ("+username+", "+password+", "+fullName")");
			db.close();
		} else {
			addUser();
		}

	}

	public static void listUsers() {

		ResultSet rs = new ResultSet();
		DB db = new DB();
		db.connect();
		rs = db.execute("select * from users");
		System.out.println("username\tpassword\t\full name");
		System.out.println("------------------------------");
		while (rs.next()) {
			System.out.println(rs.getString(1)+"\t"+rs.getString(2)+"\t"+rs.getString(3));
		}
	}

	public static void editUser() {
		
		String username;
		String password;
		String fullName;
		String response;
		Scanner sc = new Scanner(System.in);
		ResultSet rs = new ResultSet();

		System.out.println("Enter username to edit: ");
		username = sc.nextLine();

		DB db = new DB();
		db.connect();
		rs = db.execute("select * from users where username='"+username+"'");
		if (username != rs.getString(1)) {
			db.close();
			System.out.println("That user doesn't exist.");
			break;
		} else {
			System.out.println("New password (press enter to keep current): ");
			password = sc.nextLine();
			System.out.println("New full name (press enter to keep current): ");
			fullName = sc.nextLine();

			if (password == '' & fullName == '') {

				System.out.println("No updates needed.");

			}
			else if (password == '') {

				db.execute("update users set full_name='"+fullName+"' where username = '"+username"'");
				db.close();
			}
			else if (fullName == '') {

				db.execute("update users set password='"+password+"' where username = '"+username"'");
				db.close();
			} else {

				db.execute("update users set password='"+password+"', full_name='"+fullName+"' where username = '"+username"'");
				db.close();
			}


	}

	public static void deleteUser() {
		String username;
		String response;
		Scanner sc = new Scanner(System.in);

		System.out.println("Enter a username to delete: ");
		username = sc.nextLine();

		System.out.println("Are you sure you want to delete " + username + "? (Y/N)");
		response = sc.nextLine();

		if (response == "Y" || response == "y") {
			DB db = new DB();
			db.connect();
			db.execute("delete from users where username='"+username+"'");
			db.close();

			System.out.println("Deleted.");
		} else {
			System.out.println("Aborted.);
		}
	}
}
