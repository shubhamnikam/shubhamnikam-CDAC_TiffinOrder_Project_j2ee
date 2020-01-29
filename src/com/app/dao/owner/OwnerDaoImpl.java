package com.app.dao.owner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.app.pojos.common.User;
import com.app.pojos.customer.Cart;
import com.app.pojos.owner.Category;
import com.app.pojos.owner.Menu;

@Repository
@Transactional
public class OwnerDaoImpl implements IOwnerDao {

	@Autowired
	SessionFactory sf;

	
	@Override
	public Map<String, Object> allOwnerBIValues() {
		
		Map<String, Object> allValuedBIList = new HashMap<>();
		
		
		LocalDate date = LocalDate.now();
		String todaysDate = date.toString();
		
		//1 Todays order		
		//select count(id) from cart where date='2020-01-27';
		String jpql1 = "select count(c.cartId) from Cart c where c.cartDate='"+todaysDate+"'";
		Object s1 = sf.getCurrentSession()
				.createQuery(jpql1)
				.getSingleResult();
		allValuedBIList.put("todaysNumberOfOrders", s1);
		
		
		//2 Todays New Customer
		//select count(id) from users where joindate='2020-01-27';
		String jpql2 = "select count(u.userId) from User u where u.userJoinDate='"+todaysDate+"'";
		Object s2 =sf.getCurrentSession()
				.createQuery(jpql2)
				.getSingleResult();
		allValuedBIList.put("todaysNumberOfNewUsers",s2);
		
		
		//3 Lunch Order
		//select count(id) from cart where date='2020-01-27' and type='LUNCH';
		String jpql3 = "select count(c.cartId) from Cart c where c.cartDate='"+todaysDate+"' and c.orderType='LUNCH'";
		Object s3 = sf.getCurrentSession()
				.createQuery(jpql3)
				.getSingleResult();
		allValuedBIList.put("todaysNumberOfLunchOrders",s3);
		
		//4 Dinner Order
		//select count(id) from cart where date='2020-01-27' and type='DINNER';
		String jpql4 = "select count(c.cartId) from Cart c where c.cartDate='"+todaysDate+"' and c.orderType='DINNER'";
		Object s4 = sf.getCurrentSession()
				.createQuery(jpql4)
				.getSingleResult();
		allValuedBIList.put("todaysNumberOfDinnerOrders",s4);
		
		
		//5 Todays Total Money Order
		//select sum(price) from cart where date='2020-01-27';
		String jpql5 = "select sum(c.cartPrice) from Cart c where c.cartDate='"+todaysDate+"'";
		Object s5 =sf.getCurrentSession()
				.createQuery(jpql5)
				.getSingleResult();
		allValuedBIList.put("todaysTotalSumOfOrders",s5);
		
		return allValuedBIList;
	}
	
	
	@Override
	public Category getCategoryById(int categoryId) {
		String jpql = "select c from Category c where c.categoryId =" + categoryId;

		return sf.getCurrentSession().createQuery(jpql, Category.class).getSingleResult();
	}

	@Override
	public void addNewMenu(Menu addNewMenu) {

		sf.getCurrentSession().persist(addNewMenu);

	}

	@Override
	public List<User> getAllUsersList() {

		String jpql = "select u from User u";

		return sf.getCurrentSession().createQuery(jpql, User.class).getResultList();
	}

	@Override
	public List<Cart> getAllOrdersList(int i) {

		if (i == 1) {
			String jpql = "select c from Cart c where c.orderType = 'LUNCH' order by c.cartDate DESC";
			// String jpql = "select c from Cart c";

			return sf.getCurrentSession().createQuery(jpql, Cart.class).getResultList();
		} else {
			String jpql = "select c from Cart c where c.orderType = 'DINNER' order by c.cartDate DESC";

			return sf.getCurrentSession().createQuery(jpql, Cart.class).getResultList();
		}
	}

	@Override
	public Menu getMenuById(int menuId) {
		String jpql = "select m from Menu m where m.menuId = " + menuId;
		return sf.getCurrentSession().
				createQuery(jpql, Menu.class).getSingleResult();
	}
	
	@Override
	public void deleteMenuById(Menu menu) {
		sf.getCurrentSession().delete(menu);
	}

	@Override
	public List<Menu> getAllMenuList() {
		String jpql = "select m from Menu m";
		return sf.getCurrentSession().createQuery(jpql, Menu.class).getResultList();
	}

	

	

}
