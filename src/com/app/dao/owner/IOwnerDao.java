package com.app.dao.owner;

import java.util.List;

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
	
}
