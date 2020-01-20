package com.app.controller.customer;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.app.dao.common.IUserDao;

@RestController
@CrossOrigin // (origins="http://localhost:4200")
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	private IUserDao iUserDao;

	@PostConstruct
	public void init() {
		System.out.println("In Controller :: CustomerController :: init()");
	}

	// ==========================goto view :: /customer/menu : get=========================
	@GetMapping("/menu")
	public ModelAndView gotoCustomerMenuUIPage() {
		System.out.println("In Controller :: CustomerController :: gotoCustomerMenuUIPage()");
		return new ModelAndView("/customer/menu");
	}
	
	
	
}
