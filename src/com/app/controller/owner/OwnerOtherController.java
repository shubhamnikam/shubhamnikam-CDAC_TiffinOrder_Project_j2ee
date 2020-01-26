package com.app.controller.owner;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dao.common.IUserDao;

@RestController
@CrossOrigin // (origins="http://localhost:4200")
@RequestMapping("/ownerother")
public class OwnerOtherController {
	
	@Autowired
	private IUserDao iUserDao;

	@PostConstruct
	public void init() {
		
	}

	
	// ===================give navigation link after registration===============
		@GetMapping("/redirectaddnewowner")
		public void usingRedirectAddNewOwner(Model map) {
		}
	
}
