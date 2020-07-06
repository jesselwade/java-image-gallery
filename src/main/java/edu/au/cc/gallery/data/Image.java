package edu.au.cc.gallery.data;

import java.nio.ByteBuffer;

public class Image {

	private int s3id;
	private String filename;
	private ByteBuffer data;

	public Image(int s3id, String filename) {
		this.s3id = s3id;
		this.filename = filename;
	}

	public int getS3id() { return s3id; }
	public void setS3id(int s) { s3id = s; }

	public String getFilename() { return filename; }
	public void setFilename(String f) { filename = f; }

	public ByteBuffer getData() { return data; }
	public void setData(ByteBuffer b) { data = b; }

	@Override
	public String toString() {
		return s3id + "/" + filename;
	}
}
