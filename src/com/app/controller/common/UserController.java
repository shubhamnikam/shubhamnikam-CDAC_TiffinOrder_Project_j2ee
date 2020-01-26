package com.app.controller.common;

import java.util.Date;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
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

	// ==========================signin : post=========================
	@PostMapping(value = "/signin", consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ModelAndView processSignInForm(Model map, HttpSession hs, @RequestBody User incommingUser) {

		System.out.println("In Controller :: UserController :: processSignInForm()");

		
		try {
			User user = iUserDao.validateUser(incommingUser.getUserEmail(), incommingUser.getUserPassword());
			
			hs.setAttribute("userDetails", user);
		
			User u = (User) hs.getAttribute("userDetails");

			if (user.getUserRole().equals(UserRoleType.OWNER)) {
				return new ModelAndView("redirect:/owner/home");
			} else if (user.getUserRole().equals(UserRoleType.CUSTOMER)) {
				return new ModelAndView("redirect:/customer/home");
			} else {
				return new ModelAndView("/error/errorpage");
			}

		} catch (RuntimeException e) {
			// validated user record not found
				map.addAttribute("errorMsg_loginFailed_requestScopeSession",
						"Invalid credential, Please try again....");
				e.printStackTrace();
				return new ModelAndView("/user/signin");
			
		}
		
	}


	// ==========================signup : post=========================
	@PostMapping("/signup")
	public ModelAndView processSignUpForm(Model map, @RequestBody User incommingUser) {
		System.out.println("In Controller :: UserController :: processSignUpForm()");

		User userSignUpDetails = new User(incommingUser.getUserName(), incommingUser.getUserEmail(),
				incommingUser.getUserPhone(), incommingUser.getUserPassword(), new Date(), UserRoleType.CUSTOMER);

		iUserDao.signUpUserWithDetails(userSignUpDetails);

		// goto signin page
		return new ModelAndView("redirect:/customer/redirectsignup");

	}

	// ============================logout :: get=======================
	@GetMapping("/signout")
	public void processSignOut(HttpSession hs) {
		
		System.out.println("User signout success");
		hs.removeAttribute("loggedInUserDetails_hs");
		hs.invalidate();

	}
}
