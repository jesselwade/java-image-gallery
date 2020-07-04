package edu.au.cc.gallery.tools;

import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.SQLException;

import spark.Request;
import spark.Response;
import static spark.Spark.*;

import edu.au.cc.gallery.data.DB;
import edu.au.cc.gallery.aws.S3;

public class UserAdmin {

        public void addUser(String username, String password, String fullName) throws SQLException {

		try {
                	DB db = new DB();
                	db.connect();
                	db.execute("insert into users (username, password, full_name) values (?, ?, ?)", new String[] {username, password, fullName});
                	db.close();
		} catch (SQLException e) {
			System.out.println("User exists.");
		}
        }

        public ArrayList<String> listUsersTable() throws SQLException {
		ArrayList<String> al = new ArrayList<>();
                DB db = new DB();
                db.connect();
                ResultSet rs = db.query("select * from users");

                while (rs.next()) {
                        al.add(rs.getString(1)+"\t"+rs.getString(2)+"\t"+rs.getString(3));
                }

		return al;

        }

	        public ArrayList<String> listUsersNames() throws SQLException {
                ArrayList<String> al = new ArrayList<>();
                DB db = new DB();
                db.connect();
                ResultSet rs = db.query("select * from users");

                while (rs.next()) {
                        al.add(rs.getString(1));
                }

                return al;

        }


        public boolean editUser(String username, String password, String fullName) throws SQLException {

             try {   
		DB db = new DB();
                db.connect();
                ResultSet rs = db.query("select * from users where username='"+username+"'");
                
                if (!rs.isBeforeFirst()) {
                        db.close();
			System.out.println("User doesn't exist.");
			return false;

                } else {
			// no update necessary so return true
                        if (password.isEmpty() && fullName.isEmpty()) {

                                return true;

                        }
                        else if (password.isEmpty()) {
                                db.execute("update users set full_name=? where username=?", new String[] {fullName, username});
                                db.close();
				return true;
                        }
                        else if (fullName.isEmpty()) {

                                db.execute("update users set password=? where username=?", new String[] {password, username});
                                db.close();
				return true;
                        } else {

                                db.execute("update users set password=? , full_name=? where username=?", new String[] {password, fullName, username});
                                db.close();
				return true;
                        }
                }
	     } catch (SQLException e) {
		System.out.println("Edited info bad.");
		return false;
	    }

        }

        public void deleteUser(String username) throws SQLException {

              DB db = new DB();
              db.connect();
              db.execute("delete from users where username=?", new String[] {username});
              db.close();
        }

	public void checkAdmin(Request req, Response resp) {
	
		if (!isAdmin(req.session().attribute("user"))) {
			resp.redirect("/login");
			halt();
		}
	}

	public boolean isAdmin(String username) {
		return username != null && username.equals("fred");
	}
}
