package edu.au.cc.gallery;

import java.util.Scanner;
import java.sql.SQLException;
import java.lang.NumberFormatException;
import java.util.InputMismatchException;

public class UI {

	public	Scanner sc = new Scanner(System.in);
	
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
			sc.useDelimiter(System.lineSeparator());

			try {
				response = Integer.parseInt(sc.nextLine());
			} catch (NumberFormatException ex) {
				System.out.println("Invalid input.");
			} catch (InputMismatchException ex) {	
				System.out.println("Invalid input.");
			}

			switch (response) {
				case 1: UserAdmin.listUsers();
					break;
				case 2: UserAdmin.addUser();
					break;
				case 3: UserAdmin.editUser();
					break;
				case 4: UserAdmin.deleteUser();
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
                        sc.useDelimiter(System.lineSeparator());

                        try {
                                response = Integer.parseInt(sc.nextLine());
                        } catch (NumberFormatException ex) {
                                System.out.println("Invalid input.");
                        } catch (InputMismatchException ex) {
                                System.out.println("Invalid input.");
                        }

                        switch (response) {
                                case 1: s3a.setBucket();
                                        break;
                                case 2: s3a.listKeys();
                                        break;
                                case 3: s3a.showACL();
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


	public void aclMenu() {


	}
}
