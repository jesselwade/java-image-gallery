package edu.au.cc.gallery.data;

public class User {
	private String username;
	private String password;
	private String fullName;
	private int s3id;

	public User(String username, String password, String fullName, int s3id) {
		this.username = username;
		this.password = password;
		this.fullName = fullName;
		this.s3id = s3id;
	}

	public String getUsername() { return username; }
	public void setUsername(String u) { username = u; }
	
	public String getPassword() { return password; }
	public void setPassword(String p) { password = p;}

	public String getFullName() { return fullName; }
	public void setFullName(String n) { fullName = n; }

	public int getS3id() { return s3id; }
	public void setS3id(int s) { s3id = s; }

	@Override
	public String toString() {
	
		return "User with username: " + username + " password: " + password + " full name: " + fullName + " and s3id: " + s3id;
	}
}
