package com.app.dao.common;

import com.app.pojos.common.User;

public interface IUserDao {

	public User validateUser(String email, String password);
	
	public void signUpUserWithDetails(User user); 
	
}
