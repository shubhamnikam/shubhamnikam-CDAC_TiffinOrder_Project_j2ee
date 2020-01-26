package com.app.controller.customer;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.app.dao.customer.ICustomerDao;


@RestController
@CrossOrigin // (origins="http://localhost:4200")
@RequestMapping("/cart")
public class CartController {

	@Autowired
	private ICustomerDao iCustomerDao;

	@PostConstruct
	public void init() {
		System.out.println("In Controller :: CartController :: init()");
	}
	
	@GetMapping("/redirectcart")
	public ResponseEntity<?> processRedirectSigninPageFromSignUp(
			RedirectAttributes flashMap, Model map, HttpSession hs) {
		// goto cart page	
		Integer cartId = (Integer) hs.getAttribute("cartId_hs");
		map.addAttribute("cartId", cartId);
		
		return new ResponseEntity<Model>(map, HttpStatus.OK);
	}
	
	@GetMapping("/redirectcartitems")
	public ResponseEntity<?> processRedirectCartPageFromSignUp(Model map) {
		map.addAttribute("cartURL", "/cart/cartitems");
		return new ResponseEntity<Model>(map, HttpStatus.OK);
	}
	
}
