package edu.au.cc.gallery.data;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.json.JSONArray;
import org.json.JSONObject;

import edu.au.cc.gallery.aws.Secret;

public class DB {
	
//	private static final String dbUrl = "jdbc:postgresql://image-gallery.cpbmo65tqben.us-east-2.rds.amazonaws.com/image_gallery";
	private static final String dbUrl = "jdbc:postgresql://172.17.0.2/image_gallery";
	private Connection connection;


	private JSONObject getSecret() {
		String s = Secret.getSecret();
		return new JSONObject(s);
	}

	private String getPassword(JSONObject secret) {
		return secret.getString("password");

	}

	public void connect() throws SQLException {
		try {
			Class.forName("org.postgresql.Driver");
			JSONObject secret = getSecret();
			connection = DriverManager.getConnection(dbUrl, "image_gallery", getPassword(secret));
		}
		catch (ClassNotFoundException ex) {
			ex.printStackTrace();
			System.exit(1);
		}
	}	
	// Use to INSERT or UPDATE 
	public void execute(String query, String[] values) throws SQLException {
		PreparedStatement stmt = connection.prepareStatement(query);
		for (int i=0; i < values.length; i++) {
			stmt.setString(i+1, values[i]);
		}
		stmt.executeUpdate();
	}
	// Use to SELECT where QUERY expects results
	public ResultSet query(String query) throws SQLException {
		PreparedStatement stmt = connection.prepareStatement(query);
		ResultSet rs = stmt.executeQuery();
		return rs;
	}

        // Use to SELECT with protected values
        public ResultSet query(String query, String[] values) throws SQLException {
                PreparedStatement stmt = connection.prepareStatement(query);
                for (int i=0; i < values.length; i++) {
                        stmt.setString(i+1, values[i]);
                }
               return  stmt.executeQuery();
        }


	public void close() throws SQLException {
		connection.close();
	}

	public static void demo() throws Exception {
		DB db = new DB();
		db.connect();
		db.execute("update users set password=? where username=?",
				new String[] {"monkey", "fred"});
		ResultSet rs = db.query("select username,password,full_name from users");
		while (rs.next()) {
			System.out.println(rs.getString(1)+","+rs.getString(2)+","+rs.getString(3)+"\n");
		}
		rs.close();
		db.close();
	}
}
