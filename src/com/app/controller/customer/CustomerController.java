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

	@PostConstruct
	public void init() {
		System.out.println("In Controller :: CustomerController :: init()");
	}

	// ==========================give navigation link=========
	@GetMapping("/home")
	public ResponseEntity<?> gotoCustomerMenuUIPage(Model map) {
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


	// ==========================give menu data=========
	@GetMapping("/menu/{dailyUserMenuType}")
	public ResponseEntity<?> processCustomerMenuUIPage(Model map, 
			HttpSession hs, @PathVariable String dailyUserMenuType) {

		System.out.println("In Controller :: CustomerController :: gotoCustomerMenuUIPage()");

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


		User u = (User) hs.getAttribute("loggedInUserDetails_hs");
		System.out.println("<============>"+u);

		List<Menu> menuList = iCustomerDao.showMenuItems(tempDailyMenuType);

		map.addAttribute("userType", "CUSTOMER");
		map.addAttribute("dailyUserMenuType", dailyUserMenuType);
		map.addAttribute("menuList", menuList);

		return new ResponseEntity<Model>(map, HttpStatus.OK);
	}
	
	//===================add data to cart====================

	@PostMapping(value="/cart",
			consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ModelAndView processCartItemsForm(RedirectAttributes flashMap,
			@RequestBody Cart cart, HttpSession hs) {
		System.out.println("In Controller :: cart");
		
		System.out.println(hs.getId()); 

		User u = (User) hs.getAttribute("loggedInUserDetails_hs");
		System.out.println("============>"+ u);
		
		Cart tempCart = new Cart(cart.getCartPrice(),cart.getCartDate(),
				cart.getCartTime(), cart.getOrderType());
		
		//u.setCart(tempCart);
		tempCart.setUser(u);
		
		//tempCart.getUser().setUserId(2);
		//System.out.println(tempCart.getUser().getUserId());
		
		//persist using dao
		iCustomerDao.sendCartDataToDB(tempCart);
		
		flashMap.addFlashAttribute("cartId", tempCart.getCartId());
		
		
		System.out.println("====================");
		System.out.println(tempCart);
		System.out.println(u);	
		System.out.println(tempCart.getCartId());
		System.out.println("====================");
		
		return new ModelAndView("redirect:/customer/redirectcart");
		
	}
	
	@GetMapping("/redirectcart")
	public ResponseEntity<?> processRedirectSigninPageFromSignUp(RedirectAttributes flashMap,Model map) {
		//goto cart page
		map.addAttribute("cartId",flashMap.getAttribute("cartId"));
		return new ResponseEntity<Model>(map, HttpStatus.OK);
	}
	
	
	//===================add data to cartItems====================
	@PostMapping(value = "/cartitems", consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<?> processCartItemsForm(Model map, 
			@RequestBody CartItems cartItems) {
		System.out.println("In Controller :: cartItems");

					
		CartItems tempCartItems = new CartItems(
				cartItems.getCartId(),cartItems.getMenuId(),
				cartItems.getCartItemsMenuName(), cartItems.getCartItemsPrice(),
				cartItems.getCartItemsQuantity(), cartItems.getCartItemsTotalPrice());
		
		iCustomerDao.sendCartItemsDataToDB(tempCartItems);
				
				
		//goto cart page
		map.addAttribute("cartURL", "/customer/cart");
		return new ResponseEntity<Model>(map, HttpStatus.OK);
		
	}


}
