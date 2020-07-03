package edu.au.cc.gallery.tools;

import java.util.Scanner;
import java.sql.ResultSet;
import java.util.InputMismatchException;

import edu.au.cc.gallery.aws.S3;

public class S3ACLAdmin {


	private String currentBucket;
	private boolean activeBucket = false;
			
			

	public void setBucket(String bucket) {
		
		activeBucket = true;
		this.currentBucket = bucket;
	}

	public String listKeys() {
		if (!activeBucket) {
			return "Set a bucket to use first. (Option 1)";
		}

		S3 s3 = new S3();
		s3.connect();
		return s3.listKeys(currentBucket);
		}
	
	public String showACL(String key) {
		if (!activeBucket) {
			return "Set a bucket to use first. (Option 1)";
		}

		S3 s3 = new S3();
		s3.connect();

		//return s3.showACL(currentBucket, key).toString();
		return "Not yet implemented.";
	}

}
