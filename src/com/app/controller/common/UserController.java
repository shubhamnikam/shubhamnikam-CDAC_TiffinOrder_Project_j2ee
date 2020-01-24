package com.app.controller.common;

import java.util.Date;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
	
//	 @GetMapping("/signin") public ModelAndView showSignInForm() {
//	  System.out.println("In Controller :: UserController :: showSignInForm()");
//	  return new ModelAndView("/user/signin");
//	  }
//	 


	// ==========================signin : post=========================
	 @PostMapping(value = "/signin", consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
		public ModelAndView processSignInForm(Model map, HttpSession hs, @RequestBody User incommingUser) {

			System.out.println("In Controller :: UserController :: processSignInForm()");

			User user = null;

			try {
				user = iUserDao.validateUser(incommingUser.getUserEmail(), incommingUser.getUserPassword());
				
				
				System.out.println("++++++++++User :: " + user);
				
				
				hs.setAttribute("loggedInUserDetails_hs", user);
				User u = (User) hs.getAttribute("loggedInUserDetails_hs");
				

				System.out.println("------------User :: " + u);
				
				
				// check for owner or customer
				if (user.getUserRole().equals(UserRoleType.OWNER)) {
					// go to owner home dashboard UI
					System.out.println("owner");
					return new ModelAndView("redirect:/owner/home");
				} else if (user.getUserRole().equals(UserRoleType.CUSTOMER)) {
					// go to customer menu UI
					System.out.println("customer 1");
					return new ModelAndView("redirect:/customer/home");
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
//	@GetMapping("/signup")
//	public ModelAndView showSignUpForm() {
//		System.out.println("In Controller :: UserController :: showSignUpForm()");
//		return new ModelAndView("/user/signup");
//	}
	
	
	// ==========================signup : post=========================
	@PostMapping("/signup")
	public ModelAndView processSignUpForm(Model map, 
			@RequestBody User incommingUser) {
		System.out.println("In Controller :: UserController :: processSignUpForm()");

		User userSignUpDetails = new User(
				incommingUser.getUserName(), incommingUser.getUserEmail(),
				incommingUser.getUserPhone(), incommingUser.getUserPassword(),
				new Date(), UserRoleType.CUSTOMER);
		
		iUserDao.signUpUserWithDetails(userSignUpDetails);
				
		//goto signin page
		return new ModelAndView("redirect:/customer/redirectsignup");
		
	}
}



















