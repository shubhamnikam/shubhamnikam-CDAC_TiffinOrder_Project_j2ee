package com.app.controller.customer;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.app.dao.common.IUserDao;
import com.app.dao.customer.ICustomerDao;
import com.app.pojos.common.User;
import com.app.pojos.customer.Cart;
import com.app.pojos.customer.CartItems;
import com.app.pojos.owner.Menu;

@RestController
@CrossOrigin // (origins="http://localhost:4200")
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	private ICustomerDao iCustomerDao;
	@Autowired
	private IUserDao iUserDao;
	
	@PostConstruct
	public void init() {
		System.out.println("In Controller :: CustomerController :: init()");
	}

	// ==========================give after signin navigation link=========
	@GetMapping("/home")
	public ResponseEntity<?> gotoCustomerMenuUIPage(Model map, HttpSession hs) {
		
		User currentUserDetails = (User) hs.getAttribute("userDetails");
		
		map.addAttribute("currentUserDetails", currentUserDetails);
		map.addAttribute("userType", "CUSTOMER");
		map.addAttribute("userURL", "/customer/menu");
		
		return new ResponseEntity<Model>(map, HttpStatus.OK);
	}
	
	// ===================give navigation link after registration===============
	@GetMapping("/redirectsignup")
	public ResponseEntity<?> gotoRedirectSigninPageFromSignUp(Model map) {
		map.addAttribute("userSigninURL", "/user/signin");
		return new ResponseEntity<Model>(map, HttpStatus.OK);
	}

	
	// ==========================give menu data=================
	@GetMapping("/menu/{dailyUserMenuType}")
	public ResponseEntity<?> processCustomerMenuUIPage(Model map,
			@PathVariable String dailyUserMenuType) {


		String tempDailyMenuType = dailyUserMenuType;

		if (dailyUserMenuType.isEmpty()) {
			tempDailyMenuType = "LUNCH";
		} else if (dailyUserMenuType.contains("LUNCH")) {
			tempDailyMenuType = "LUNCH";
		} else if (dailyUserMenuType.contains("DINNER")) {
			tempDailyMenuType = "DINNER";
		} else {
			tempDailyMenuType = "SNACK";
		}

		List<Menu> menuList = iCustomerDao.showMenuItems(tempDailyMenuType);

		map.addAttribute("userType", "CUSTOMER");
		map.addAttribute("dailyUserMenuType", dailyUserMenuType);
		map.addAttribute("menuList", menuList);

		return new ResponseEntity<Model>(map, HttpStatus.OK);
	}

	// ===================add data to cart==========================

	@PostMapping(value = "/cart/{userId}", consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ModelAndView processCartForm(@PathVariable String userId, HttpSession hs, @RequestBody Cart cart) {
		
		
		Cart tempCart = cart;
		
		User tempUser = iUserDao.getUserById(Integer.parseInt(userId));
		tempCart.setUser(tempUser);

		iCustomerDao.sendCartDataToDB(tempCart);
		
		//for getting cart Id
		hs.setAttribute("cartId_hs",tempCart.getCartId());

		System.out.println("====================");
		System.out.println(tempUser);
		System.out.println(tempCart);
		System.out.println(tempCart.getCartId());
		System.out.println("====================");
	
		return new ModelAndView("redirect:/cart/redirectcart");

	}



	// ===================add data to cartItems====================
	@PostMapping(value = "/cartitems/{cartId}", consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ModelAndView processCartItemsForm(@PathVariable String cartId, Model map, @RequestBody List<CartItems> cartList) {
		
		System.out.println("In Controller :: cartItems");

		
		Cart tempCart = iCustomerDao.getCartById(Integer.parseInt(cartId));	
		List<CartItems> tempCartItemsList = cartList;
				
		for (CartItems cartItems : tempCartItemsList) {

			CartItems items = new CartItems(
					cartItems.getMenuId(),
					cartItems.getCartItemsMenuName(),
					cartItems.getCartItemsPrice(),
					cartItems.getCartItemsQuantity(),
					cartItems.getCartItemsTotalPrice()
					);
			
			items.setCart(tempCart);
			
			System.out.println(items);
			
			iCustomerDao.sendCartItemsDataToDB(items);
		}
		
		return new ModelAndView("redirect:/cart/redirectcartitems");

	}
	
}
