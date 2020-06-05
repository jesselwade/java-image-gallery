package edu.au.cc.gallery;

import java.util.Scanner;
import java.sql.ResultSet;
import java.util.InputMismatchException;

public class S3ACLAdmin {


	private String currentBucket;
	private boolean activeBucket = false;
			
			

	public void setBucket() {
		
		System.out.println("Enter the bucket name to use: ");
		currentBucket = sc.nextLine();
		activeBucket = true;
		System.out.println("Active S3 Bucket: " + currentBucket);
	}

	public void listKeys() {
		if (!activeBucket) {
			System.out.println("Set a bucket to use first. (Option 1)");
		} else {
			S3 s3 = new S3();
			s3.connect();
			System.out.println(s3.listKeys(currentBucket));
		}
	}

	public void showACL() {
		S3 s3 = new S3();
		s3.connect();

		System.out.println("Enter a key: ");
		String key = sc.nextLine();
		System.out.println(s3.showACL(currentBucket, key).toString());
	}

}
