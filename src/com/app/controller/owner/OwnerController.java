package com.app.controller.owner;

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
@RequestMapping("/owner")
public class OwnerController {

	@Autowired
	private IUserDao iUserDao;

	@PostConstruct
	public void init() {
		System.out.println("In Controller :: OwnerController :: init()");
	}

	// ==========================owner : get=========================
	@GetMapping("/home")
	public ModelAndView gotoOwnerHomeUIPage() {
		System.out.println("In Controller :: UserController :: gotoOwnerHomeUIPage()");
		return new ModelAndView("/owner/home");
	}
	
}
