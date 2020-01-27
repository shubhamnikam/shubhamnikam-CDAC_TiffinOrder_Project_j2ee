package com.app.dao.owner;

import java.util.List;
import java.util.Map;

import com.app.pojos.common.User;
import com.app.pojos.customer.Cart;
import com.app.pojos.customer.CartItems;
import com.app.pojos.owner.Category;
import com.app.pojos.owner.DailyMenu;
import com.app.pojos.owner.DailyMenuType;
import com.app.pojos.owner.Menu;

public interface IOwnerDao {

	
	Category getCategoryById(int categoryId);
	
	public void addNewMenu(Menu addNewMenu); 
	
	public List<User> getAllUsersList();
	
	public List<Cart> getAllOrdersList(int i); 
	
	public List<Menu> getAllMenuList();

	public Menu getMenuById(int menuId);
	public void deleteMenuById(Menu menu);
	
	
	public Map<String, Object> allOwnerBIValues();
	
	
	
}
