package com.app.controller.common;

import java.util.Date;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.app.dao.common.IUserDao;
import com.app.pojos.common.User;
import com.app.pojos.common.UserRoleType;

@RestController
@CrossOrigin // (origins="http://localhost:4200")
@RequestMapping("/user")
public class UserController {

	@Autowired
	private IUserDao iUserDao;

	@PostConstruct
	public void init() {
		System.out.println("In Controller :: UserController :: init()");
	}

	// ==========================goto view :: /user/signin : get=========================
	@GetMapping("/signin")
	public ModelAndView showSignInForm() {
		System.out.println("In Controller :: UserController :: showSignInForm()");
		return new ModelAndView("/user/signin");
	}


	// ==========================signin : post=========================
	@PostMapping("/signin")
	public ModelAndView processSignInForm(Model map, HttpSession hs,
			@RequestParam String email, @RequestParam String password) {
		
		System.out.println("In Controller :: UserController :: processSignInForm()");
		
		User user = null;
		
		try {
			// get validated user record
			user = iUserDao.validateUser(email, password);

			System.out.println("User :: " + user);

			// httpsession :: loggedInUserDetails_httpSessionScope
			hs.setAttribute("loggedInUserDetails_httpSessionScope", user);

			// check for owner or customer
			if (user.getUserRole().equals(UserRoleType.OWNER)) {
				// go to owner home dashboard UI
				System.out.println("owner");
				return new ModelAndView("redirect:/owner/home");
			} else if (user.getUserRole().equals(UserRoleType.CUSTOMER)) {
				// go to customer menu UI
				System.out.println("customer");

				return new ModelAndView("redirect:/customer/menu");
			} else {
				return new ModelAndView("/error/errorpage");
			}

		} catch (RuntimeException e) {
			// validated user record not found
			if (user == null) {
				map.addAttribute("errorMsg_loginFailed_requestScopeSession",
						"Invalid credential, Please try again....");
				e.printStackTrace();
				return new ModelAndView("/user/signin");
			}
		}
		return null;
	}
	
	// ==========================goto view :: /user/signup : get=========================
	@GetMapping("/signup")
	public ModelAndView showSignUpForm() {
		System.out.println("In Controller :: UserController :: showSignUpForm()");
		return new ModelAndView("/user/signup");
	}
	
	
	// ==========================signup : post=========================
	@PostMapping("/signup")
	public ModelAndView processSignUpForm(Model map, 
			@RequestParam String name, @RequestParam String email, 
			@RequestParam String password, @RequestParam String phone) {
		System.out.println("In Controller :: UserController :: processSignUpForm()");

		User userSignUpDetails = new User(name, email,
				phone, password, new Date(), UserRoleType.CUSTOMER);
		
		iUserDao.signUpUserWithDetails(userSignUpDetails);
				
		//goto signin page
		return new ModelAndView("/user/signin");
		
	}
}



















