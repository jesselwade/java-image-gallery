package edu.au.cc.gallery.aws;

import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.CreateBucketConfiguration;
import software.amazon.awssdk.services.s3.model.CreateBucketRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.model.GetObjectAclRequest;
import software.amazon.awssdk.services.s3.model.GetObjectAclResponse;
import software.amazon.awssdk.services.s3.model.ListObjectsRequest;
import software.amazon.awssdk.services.s3.model.ListObjectsResponse;

public class S3 {

	private static final Region region = Region.US_EAST_2;
	private S3Client client;


	public void connect() {
		
		client = S3Client.builder().region(region).build();
	}

	public void createBucket(String bucketName, Region regionOrNull) {
		
		if (regionOrNull == null) {
			regionOrNull = Region.US_EAST_2;
		}

		CreateBucketRequest createBucketRequest = CreateBucketRequest
			.builder()
			.bucket(bucketName)
			.createBucketConfiguration(CreateBucketConfiguration.builder()
					.locationConstraint(region.id())
					.build())
			.build();

		client.createBucket(createBucketRequest);
	}


	public void putObject(String bucketName, String key, String value) {
		
		PutObjectRequest por = PutObjectRequest.builder()
			.bucket(bucketName)
			.key(key)
			.build();
		client.putObject(por, RequestBody.fromString(value));
	}
	
	// Change bucket name
	public void setCurrentBucket(String bucket, String newBucket) {
		System.out.println("Not yet implemented.");
	}

	public String listKeys(String bucketName) {

		ListObjectsRequest lo = ListObjectsRequest.builder()
			.bucket(bucketName)
			.build();
		ListObjectsResponse lr = client.listObjects(lo);

		return lr.toString();		
	}

	public String showACL(String bucketName, String key) {
                
		GetObjectAclRequest gar = GetObjectAclRequest.builder()
				.bucket(bucketName)
				.key(key)
				.build();
		GetObjectAclResponse gresp = client.getObjectAcl(gar);

		return gresp.toString();
	}

	public void updateACL(String key) {
		//TODO
	}

	public static void demo() {
		String bucketName = "edu.au.cc.jzw.image-gallery";
		S3 s3 = new S3();
		s3.connect();
		//s3.createBucket(bucketName, region);
		s3.putObject(bucketName, "banana", "yellow");

	}
}
