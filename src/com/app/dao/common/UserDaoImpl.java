package com.app.dao.common;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.app.pojos.common.User;

@Repository
@Transactional
public class UserDaoImpl implements IUserDao{

	@Autowired
	SessionFactory sf;
			
	@Override
	public User validateUser(String email, String password) {
		
		String jpql = "select u from User u where u.userEmail=:tempEmail and u.userPassword=:tempPassword";
		
		return sf.getCurrentSession()
				.createQuery(jpql, User.class)
				.setParameter("tempEmail", email)
				.setParameter("tempPassword", password)
				.getSingleResult();
	}

	@Override
	public void signUpUserWithDetails(User user) {
		sf.getCurrentSession().persist(user);
	}

	@Override
	public User getUserById(int userId) {
		String jpql = "select u from User u where u.userId ="+userId;

		return sf.getCurrentSession()
				.createQuery(jpql, User.class)
				.getSingleResult();
	}

	@Override
	public void addNewUser(User user) {
		
		sf.getCurrentSession().persist(user);
		
	}

}
