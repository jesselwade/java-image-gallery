package edu.au.cc.gallery.data;

import java.util.List;
import java.util.ArrayList;

import java.nio.ByteBuffer;

import java.sql.ResultSet;
import java.sql.SQLException;

import edu.au.cc.gallery.aws.S3;

public class PostgresImageDAO implements ImageDAO {


	private DB connection;
	
	public PostgresImageDAO() throws SQLException {
		connection = new DB();
		connection.connect();
	}

	public List<Image> getImages(User u) throws SQLException {
		List<Image> result = new ArrayList<Image>();
		ResultSet rs = connection.query("select * from images where s3id=(cast(? as integer))", new String[] {String.valueOf(u.getS3id())});
		while(rs.next()) {
			result.add(new Image(rs.getInt(2), rs.getString(3))); 
		}
		rs.close();
		return result;
	}

	public void addImage(Image i) throws SQLException {
		String id = String.valueOf(i.getS3id());
		String path = i.getFilename();
		connection.execute("insert into images(s3id, path) values(cast(? as integer),?)", new String[] {id, path});

		S3 s3 = new S3();
		s3.connect();
		s3.putObject(id + "/" + path, i.getData());

	}


}
