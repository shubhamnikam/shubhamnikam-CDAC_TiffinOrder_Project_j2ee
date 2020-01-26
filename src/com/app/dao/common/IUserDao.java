package com.app.dao.common;

import com.app.pojos.common.User;
import com.app.pojos.owner.Menu;

public interface IUserDao {

	public User validateUser(String email, String password);
	
	public void signUpUserWithDetails(User user); 
	
	public User getUserById(int userId);
	
	public void addNewUser(User user);

		
}
