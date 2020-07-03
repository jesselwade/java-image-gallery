package edu.au.cc.gallery.data;

import java.util.List;
import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PostgresUserDAO implements UserDAO {

	private DB connection;

	public PostgresUserDAO() throws SQLException {
		connection = new DB();
		connection.connect();
	}

	public List<User> getUsers() throws SQLException {
		List<User> result = new ArrayList<User>();
		ResultSet rs = connection.query("select username, password, full_name, s3id from users");
		while(rs.next()) {
			result.add(new User(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4)));
		}
		rs.close();
		return result;
	}

        public User getUserByUsername(String username) throws SQLException {
                ResultSet rs = connection.query("select username, password, full_name, s3id from users where username=?", new String[] {username});
                if(rs.next()) {
                        return new User(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4));
                }
		return null;
        }

	public void addUser(User u) throws SQLException {
		connection.execute("insert into users(username, password, full_name values(?,?,?)",
				new String[] {u.getUsername(), u.getPassword(), u.getFullName()});
	}
}
