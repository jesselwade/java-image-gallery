package edu.au.cc.gallery.data;

import java.util.List;

public interface UserDAO {
	/**
	 * @return List of User objects (possibly empty)
	 */
	List<User> getUsers() throws Exception;

	/**
	 * @return User object with specified username.
	 */
	User getUserByUsername(String username) throws Exception;

	void addUser(User u) throws Exception;
}
