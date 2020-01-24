package com.app.pojos.owner;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "dailymenu")
public class DailyMenu {

	private Integer dailyMenuId;
	private String dailyMenuName;
	private DailyMenuType dailyMenuType;
	private Date dailyMenuTypeDate;
	//Many DailyMenu Has-A  One Menu
	private Menu menu;
	
	public DailyMenu() {
		
	}
	
	public DailyMenu(String dailyMenuName, DailyMenuType dailyMenuType, Date dailyMenuTypeDate) {
		super();
		this.dailyMenuName = dailyMenuName;
		this.dailyMenuType = dailyMenuType;
		this.dailyMenuTypeDate = dailyMenuTypeDate;
	}



	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getDailyMenuId() {
		return dailyMenuId;
	}

	public void setDailyMenuId(Integer dailyMenuId) {
		this.dailyMenuId = dailyMenuId;
	}

	@Column(name = "name")
	public String getDailyMenuName() {
		return dailyMenuName;
	}

	public void setDailyMenuName(String dailyMenuName) {
		this.dailyMenuName = dailyMenuName;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "dailymenutype")
	public DailyMenuType getDailyMenuType() {
		return dailyMenuType;
	}

	public void setDailyMenuType(DailyMenuType dailyMenuType) {
		this.dailyMenuType = dailyMenuType;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "date")
	public Date getDailyMenuTypeDate() {
		return dailyMenuTypeDate;
	}

	public void setDailyMenuTypeDate(Date dailyMenuTypeDate) {
		this.dailyMenuTypeDate = dailyMenuTypeDate;
	}

	@Override
	public String toString() {
		return "DailyMenu [dailyMenuId=" + dailyMenuId + ", dailyMenuType=" + dailyMenuType + ", dailyMenuTypeDate=" 
	+ dailyMenuTypeDate
				+ "]";
	}
	
	
	//================DailyMenu-Menu :: Many DailyMenu HAS-A One Menu================

	@ManyToOne
	@JoinColumn(name = "menuid")
	public Menu getMenu() {
		return menu;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}
}

