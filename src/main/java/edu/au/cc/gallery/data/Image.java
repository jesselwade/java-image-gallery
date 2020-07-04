package edu.au.cc.gallery.data;

public class Image {

	private String path;
	private int s3id;
	private String filename;
	private int size;
	private int[] dimensions[];


	public Image(int s3id, String filename) {
		this.s3id = s3id;
		this.filename = filename;
	}

	public int getS3id() { return s3id; }
	public void setS3id(int s) { s3id = s; }

	public String getFilename() { return filename; }
	public void setFilename(String f) { filename = f; }

	@Override
	public String toString() {
		return "S3 owner id: " + s3id + "\n" +
		       "Filename: " + filename + "\n";
	}
}
