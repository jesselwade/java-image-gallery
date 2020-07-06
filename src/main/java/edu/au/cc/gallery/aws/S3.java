package edu.au.cc.gallery.aws;

import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.CreateBucketConfiguration;
import software.amazon.awssdk.services.s3.model.CreateBucketRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.core.sync.ResponseTransformer;
import software.amazon.awssdk.services.s3.model.GetObjectAclRequest;
import software.amazon.awssdk.services.s3.model.GetObjectAclResponse;
import software.amazon.awssdk.services.s3.model.ListObjectsRequest;
import software.amazon.awssdk.services.s3.model.ListObjectsResponse;

import java.nio.ByteBuffer;
import java.nio.file.Paths;

public class S3 {

	private static final Region region = Region.US_EAST_2;
	private S3Client client;
	private String bucketName = "edu.au.cc.jzw.image-gallery";

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


	public void putObject(String key, ByteBuffer buffer) {
		
		PutObjectRequest por = PutObjectRequest.builder()
			.bucket(this.bucketName)
			.key(key)
			.acl("public-read")
			.build();
		client.putObject(por, RequestBody.fromByteBuffer(buffer));
	}
	
	// Change bucket name
	public void setBucketName(String bucket) {
		bucketName = bucket;
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
		

	}
}
