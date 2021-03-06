package com.app.controller.owner;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.app.dao.common.IUserDao;
import com.app.dao.owner.IOwnerDao;
import com.app.pojos.common.User;
import com.app.pojos.common.UserRoleType;
import com.app.pojos.customer.Cart;
import com.app.pojos.owner.Category;
import com.app.pojos.owner.DailyMenuType;
import com.app.pojos.owner.Menu;

@RestController
@CrossOrigin // (origins="http://localhost:4200")
@RequestMapping("/owner")
public class OwnerController {

	@Autowired
	private IUserDao iUserDao;

	@Autowired
	private IOwnerDao iOwnerDao;

	@PostConstruct
	public void init() {
		System.out.println("In Controller :: OwnerController :: init()");
	}

	// ==========================give navigation link=========
	@GetMapping("/home")
	public ResponseEntity<?> gotoCustomerMenuUIPage(Model map, HttpSession hs) {

		//================needed data===============
		User currentUserDetails = (User) hs.getAttribute("userDetails");

		map.addAttribute("currentUserDetails", currentUserDetails);
		map.addAttribute("userType", "OWNER");
		map.addAttribute("userURL", "/owner/dashboard");
	
		return new ResponseEntity<Model>(map, HttpStatus.OK);
	}
	
	// ==========================get BI Data link=========
	@GetMapping("/getAllBIData")
	public ResponseEntity<?> getOwnerBIData(Model map, HttpSession hs) {

		//=============Owner Home :: Business Data===================
				
		Map<String, Object> allValuedBIList =iOwnerDao.allOwnerBIValues();
		map.addAttribute("allValuedBIList",allValuedBIList);
	
		return new ResponseEntity<Model>(map, HttpStatus.OK);

	}
		

	// ==========================addnewowner : post=========================
	@PostMapping("/addnewowner")
	public void processAddNewUser(Model map, @RequestBody User incommingUser) {
		System.out.println("In Controller :: OwnerController :: processAddNewUser()");

		User addNewOwner = new User(incommingUser.getUserName(), incommingUser.getUserEmail(),
				incommingUser.getUserPhone(), incommingUser.getUserPassword(), new Date(), UserRoleType.OWNER);

		iUserDao.addNewUser(addNewOwner);

	}

	// ==========================addnewowner : post=========================
	@PostMapping("/addnewmenu/{categoryId}")
	public void processAddNewMenu(Model map, @PathVariable String categoryId, @RequestBody Menu menu) {
		System.out.println("In Controller :: OwnerController :: processAddNewUser()");

		// get Menu object from post request body
		Menu tempAddNewMenu = new Menu(menu.getMenuName(), menu.getMenuDescription(), menu.getMenuPrice(), 100, true);

		// get category using categoryId
		Category tempCategory = iOwnerDao.getCategoryById(Integer.parseInt(categoryId));

		// assign to Menu
		tempAddNewMenu.setCategory(tempCategory);

		// goto dao for persist data to db
		iOwnerDao.addNewMenu(tempAddNewMenu);

	}

	// ===============================getAllUsersList===========
	@GetMapping("/getAllUsers")
	public ResponseEntity<?> processGetAllUsersList(Model map) {

		// request to dao to get userList
		List<User> tempAllUsersList = iOwnerDao.getAllUsersList();

		map.addAttribute("userList", tempAllUsersList);

		return new ResponseEntity<Model>(map, HttpStatus.OK);
	}

	// ======================getAllUsersList================
	@GetMapping("/getOrders")
	public ResponseEntity<?> processGetAllOrdersList(Model map) {

		System.out.println("=======In getLunchOrderList=======");

		List<Cart> tempLunchList = iOwnerDao.getAllOrdersList(1);
		map.addAttribute("orderLunchList", tempLunchList);

		System.out.println(tempLunchList);

		System.out.println("=======In getDinnerOrderList=======");

		List<Cart> tempDinnerList = iOwnerDao.getAllOrdersList(2);
		map.addAttribute("orderDinnerList", tempDinnerList);

		System.out.println(tempDinnerList);

		return new ResponseEntity<Model>(map, HttpStatus.OK);
	}

	// ======================get all menu================
	@GetMapping("/getAllMenu")
	public ResponseEntity<?> processGetAllMenu(Model map) {

		// request to dao to get userList
		List<Menu> tempAllMenuList = iOwnerDao.getAllMenuList();

		map.addAttribute("menuList", tempAllMenuList);

		return new ResponseEntity<Model>(map, HttpStatus.OK);
		}
	
	
	// ======================delete menu by id================
	@DeleteMapping("/deleteMenu/{menuId}")
	public void deleteEmpDetails(@PathVariable String menuId) {
		
		System.out.println("in delete emp " + menuId);
		
		Menu tempMenu = iOwnerDao.getMenuById(Integer.parseInt(menuId));
		
		iOwnerDao.deleteMenuById(tempMenu);
	}

}
