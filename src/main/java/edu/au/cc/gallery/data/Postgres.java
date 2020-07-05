package edu.au.cc.gallery.data;

import java.sql.SQLException;

public class Postgres {

	public static UserDAO getUserDAO() throws SQLException { return new PostgresUserDAO(); }

	public static ImageDAO getImageDAO() throws SQLException { return new PostgresImageDAO(); }
}
