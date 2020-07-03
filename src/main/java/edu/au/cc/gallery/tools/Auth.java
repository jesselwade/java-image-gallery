package edu.au.cc.gallery.tools;

import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.SQLException;
import spark.Request;
import spark.Response;

import edu.au.cc.gallery.data.User;
import edu.au.cc.gallery.data.Postgres;

public class Auth {

        public static String sessionDemo(Request req, Response resp) {
		if (req.session().isNew()) {
			req.session().attribute("value", 0);
		} else {
			req.session().attribute("value", (int)req.session().attribute("value")+1);
		}
		return "<h1>"+req.session().attribute("value")+"</h1>";
        }

	public static String debugSession(Request req, Response resp) {
		StringBuffer sb = new StringBuffer();
		for(String key: req.session().attributes()) {
			sb.append(key+": "+req.session().attribute(key)+"<br />");
		}
		return sb.toString();	
	}

	public static String login(Request req, Response resp) {
		try {
			String username = req.queryParams("username");
			User u = Postgres.getUserDAO().getUserByUsername(username);
			if (u == null || !u.getPassword().equals(req.queryParams("password"))) {
				System.out.println("invalid username or password. "+u);
				resp.redirect("/login");
				return "";
			}
			req.session().attribute("user", username);
			resp.redirect("/");
			return "";
		} catch (Exception ex) {
			return "Error: "+ex.getMessage();
		}
	}

	public static boolean isSessionValid(Request req, Response resp) {
		long epoch = System.currentTimeMillis();
		if (req.session().isNew() || (epoch - req.session().lastAccessedTime()) > (req.session().maxInactiveInterval() * 1000)) {
			return false;
		}
		return true;
	}

	public static String logout(Request req, Response resp) {
		req.session().invalidate();
		resp.redirect("/login");
		return "";
	}
}
