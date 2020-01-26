package com.app.dao.customer;

import java.util.List;

import com.app.pojos.common.User;
import com.app.pojos.customer.Cart;
import com.app.pojos.customer.CartItems;
import com.app.pojos.owner.DailyMenu;
import com.app.pojos.owner.DailyMenuType;
import com.app.pojos.owner.Menu;

public interface ICustomerDao {
	
	List<Menu> showMenuItems(String dailyMenuType);
	
	void sendCartDataToDB(Cart cart);
	
	void sendCartItemsDataToDB(CartItems cartItems);
	
	public Cart getCartById(int cartId);
	
	public void addNewMenu(Menu menu);
	

}
