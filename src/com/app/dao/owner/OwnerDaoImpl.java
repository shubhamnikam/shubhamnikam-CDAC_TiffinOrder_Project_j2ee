package com.app.dao.owner;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.app.pojos.owner.Category;
import com.app.pojos.owner.Menu;


@Repository
@Transactional
public class OwnerDaoImpl implements IOwnerDao{


	@Autowired
	SessionFactory sf;
			
	@Override
	public Category getCategoryById(int categoryId) {
		String jpql = "select c from Category c where c.categoryId ="+categoryId;

		return sf.getCurrentSession()
				.createQuery(jpql, Category.class)
				.getSingleResult();
	}

	@Override
	public void addNewMenu(Menu addNewMenu) {
		
		sf.getCurrentSession().persist(addNewMenu);
		
	}

	
}
