package edu.au.cc.gallery;

import java.util.Scanner;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserAdmin {

	public static Scanner sc = new Scanner(System.in);

	public static void addUser() throws SQLException {
		
		String username;
		String password;
		String fullName;
		String response;
	

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
		
		if (response.equals("Y") || response.equals("y")) {
			DB db = new DB();
			db.connect();
			db.execute("insert into users (username, password, full_name) values (?, ?, ?)", new String[] {username, password, fullName});
			db.close();
		} else {
			System.out.println("Starting Over.");
			addUser();
		}

	}

	public static void listUsers() throws SQLException {

		DB db = new DB();
		db.connect();
		ResultSet rs = db.query("select * from users");
		System.out.println("username\tpassword\tfull name");
		System.out.println("------------------------------");
		while (rs.next()) {
			System.out.println(rs.getString(1)+"\t"+rs.getString(2)+"\t"+rs.getString(3));
		}
		System.out.println("");
	}

	public static void editUser() throws SQLException {
		
		String username;
		String password;
		String fullName;
		String response;
		Scanner sc = new Scanner(System.in);

		System.out.println("Enter username to edit: ");
		username = sc.nextLine();

		DB db = new DB();
		db.connect();
		ResultSet rs = db.query("select * from users where username='"+username+"'");
		rs.next();
		if (!username.equals(rs.getString(1))) {
			db.close();
			System.out.println("That user doesn't exist.");
			
		} else {
			System.out.println("New password (press enter to keep current): ");
			password = sc.nextLine();
			System.out.println("New full name (press enter to keep current): ");
			fullName = sc.nextLine();

			if (password.isEmpty() & fullName.isEmpty()) {

				System.out.println("No updates needed.");

			}
			else if (password.isEmpty()) {

				db.execute("update users set full_name=? where username=?", new String[] {fullName ,username});
				db.close();
			}
			else if (fullName.isEmpty()) {

				db.execute("update users set password=? where username=?", new String[] {password, username});
				db.close();
			} else {

				db.execute("update users set password=?, full_name=?, where username=?", new String[] {password, fullName, username});
				db.close();
			}
		}


	}

	public static void deleteUser() throws SQLException {
		String username;
		String response;
		Scanner sc = new Scanner(System.in);

		System.out.println("Enter a username to delete: ");
		username = sc.nextLine();

		System.out.println("Are you sure you want to delete " + username + "? (Y/N)");
		response = sc.nextLine();

		if (response.equals("Y") || response.equals("y")) {
			DB db = new DB();
			db.connect();
			db.execute("delete from users where username=?", new String[] {username});
			db.close();

			System.out.println("Deleted.");
		} else {
			System.out.println("Aborted.");
		}
	}
}
