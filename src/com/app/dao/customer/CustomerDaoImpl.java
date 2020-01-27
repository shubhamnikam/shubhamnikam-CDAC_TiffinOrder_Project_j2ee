package com.app.dao.customer;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.EnumType;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.app.pojos.common.User;
import com.app.pojos.customer.Address;
import com.app.pojos.customer.Cart;
import com.app.pojos.customer.CartItems;
import com.app.pojos.owner.DailyMenu;
import com.app.pojos.owner.DailyMenuType;
import com.app.pojos.owner.Menu;

@Repository
@Transactional
public class CustomerDaoImpl implements ICustomerDao {

	@Autowired
	private SessionFactory sf;

	@Override
	public List<Menu> showMenuItems(String dailyMenuTypeString) {

		
		LocalDate date = LocalDate.now();
		String todaysDate = date.toString();
		
		String jpql = "select m from Menu m where m.menuId "
				+ " = ANY (select d.menu from DailyMenu d"
				+ " where d.dailyMenuType='" + dailyMenuTypeString + "' and d.dailyMenuTypeDate='"+ todaysDate +"')";

	
		List<Menu> menu = sf.getCurrentSession().createQuery(jpql, Menu.class).getResultList();
		
		return menu;
	}

	@Override
	public void sendCartDataToDB(Cart cart) {
		sf.getCurrentSession().persist(cart);
	}
	
	@Override
	public void sendCartItemsDataToDB(CartItems cartItems) {
		sf.getCurrentSession().persist(cartItems);
	}

	@Override
	public Cart getCartById(int cartId) {
		String jpql = "select c from Cart c where c.cartId ="+cartId;

		return sf.getCurrentSession()
				.createQuery(jpql, Cart.class)
				.getSingleResult();
	}
	
	@Override
	public void addNewMenu(Menu menu) {
		sf.getCurrentSession().persist(menu);
	}

	@Override
	public void sendAddressDataToDB(Address address) {
		sf.getCurrentSession().persist(address);
	}


	
}
	