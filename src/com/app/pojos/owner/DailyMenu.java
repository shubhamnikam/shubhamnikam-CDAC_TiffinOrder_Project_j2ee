package com.app.pojos.owner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

//@Entity
//@Table(name="dailymenu")
public class DailyMenu {

//	private Integer dailyMenuId;
//	private MenuType dailyMenuType;
//	private Date dailyMenuDate;
//	
//	//one dailyMenu HAS-A Many menuitems
//	private List<Menu> menuList = new ArrayList<>();
//	
//	public DailyMenu() {
//	}
//	
//	public DailyMenu(MenuType dailyMenuType, Date dailyMenuDate) {
//		super();
//		this.dailyMenuType = dailyMenuType;
//		this.dailyMenuDate = dailyMenuDate;
//	}
//
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	@Column(name = "id")
//	public Integer getDailyMenuId() {
//		return dailyMenuId;
//	}
//
//	public void setDailyMenuId(Integer dailyMenuId) {
//		this.dailyMenuId = dailyMenuId;
//	}
//
//	@Enumerated(EnumType.STRING)
//	@Column(name = "type")
//	public MenuType getDailyMenuType() {
//		return dailyMenuType;
//	}
//
//	public void setDailyMenuType(MenuType dailyMenuType) {
//		this.dailyMenuType = dailyMenuType;
//	}
//
//	@Temporal(TemporalType.DATE)
//	@Column(name = "date")
//	public Date getDailyMenuDate() {
//		return dailyMenuDate;
//	}
//
//	public void setDailyMenuDate(Date dailyMenuDate) {
//		this.dailyMenuDate = dailyMenuDate;
//	}
//
//	@Override
//	public String toString() {
//		return "DailyMenu [dailyMenuId=" + dailyMenuId + ", dailyMenuType=" + dailyMenuType
//				+ ", dailyMenuDate=" + dailyMenuDate + "]";
//	}
//
//	//================DailyMenu-Menu :: One DailyMenu HAS-A Many Menu================
//	@OneToMany(mappedBy = "dailyMenu", cascade = CascadeType.ALL, orphanRemoval = true)
//	public List<Menu> getMenuList() {
//		return menuList;
//	}
//
//	public void setMenuList(List<Menu> menuList) {
//		this.menuList = menuList;
//	}	
}
