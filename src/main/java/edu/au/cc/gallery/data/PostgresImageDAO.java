package edu.au.cc.gallery.data;

import java.util.List;
import java.util.ArrayList;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PostgresImageDAO implements ImageDAO {


	private DB connection;
	
	public PostgresImageDAO() throws SQLException {
		connection = new DB();
		connection.connect();
	}

	public List<Image> getImages(User u) throws Exception {
		List<Image> result = new ArrayList<Image>();
		ResultSet rs = connection.query("select * from images where s3id=?", new String[] {String.valueOf(u.getS3id())});
		while(rs.next()) {
			result.add(new Image(rs.getInt(2), rs.getString(3))); 
		}
		rs.close();
		return result;
	}

	public void addImage(Image i) throws SQLException {
		

	}


}
