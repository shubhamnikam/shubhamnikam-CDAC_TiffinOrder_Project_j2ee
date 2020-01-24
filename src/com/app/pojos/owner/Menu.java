package com.app.pojos.owner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "menu")
public class Menu {

	private Integer menuId;
	private String menuName;
	private String menuDescription;
	private double menuPrice;
	private int menuQuantityAvailable;
	private boolean menuIsAvailable;
	
	//Many OrderItems HAS-A One Order
	Category category;
	
	//One Menu HAS-A Many DailyMenu
	List<DailyMenu> dailyMenuList = new ArrayList<>();
		
	public Menu() {
		System.out.println("In Pojo :: Menu :: ctor");
	}

	public Menu(int categoryId, String menuName, String menuDescription, double menuPrice, int menuQuantityAvailable,
			boolean menuIsAvailable, DailyMenuType menu) {
		super();
		this.menuName = menuName;
		this.menuDescription = menuDescription;
		this.menuPrice = menuPrice;
		this.menuQuantityAvailable = menuQuantityAvailable;
		this.menuIsAvailable = menuIsAvailable;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id", nullable = false)
	public Integer getMenuId() {
		return menuId;
	}

	public void setMenuId(Integer menuId) {
		this.menuId = menuId;
	}

	@Column(name="name", nullable = false)
	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	@Column(name="description", nullable = false)
	public String getMenuDescription() {
		return menuDescription;
	}

	public void setMenuDescription(String menuDescription) {
		this.menuDescription = menuDescription;
	}

	@Column(name="price", nullable = false, scale = 10, precision = 2)
	public double getMenuPrice() {
		return menuPrice;
	}

	public void setMenuPrice(double menuPrice) {
		this.menuPrice = menuPrice;
	}

	@Column(name="quantityavailable", nullable = false)
	public int getMenuQuantityAvailable() {
		return menuQuantityAvailable;
	}

	public void setMenuQuantityAvailable(int menuQuantityAvailable) {
		this.menuQuantityAvailable = menuQuantityAvailable;
	}

	@Column(name="isavailable", nullable = false)
	public boolean isMenuIsAvailable() {
		return menuIsAvailable;
	}

	public void setMenuIsAvailable(boolean menuIsAvailable) {
		this.menuIsAvailable = menuIsAvailable;
	}
	
	@Override
	public String toString() {
		return "Menu [menuId=" + menuId + ", menuName=" + menuName + ", menuDescription="
				+ menuDescription + ", menuPrice=" + menuPrice + ", menuQuantityAvailable=" + menuQuantityAvailable
				+ ", menuIsAvailable=" + menuIsAvailable + "]";
	}


	//================Menu-Category :: Many Menu HAS-A One Category================
		@ManyToOne
		@JoinColumn(name="categoryid")
		public Category getCategory() {
			return category;
		}
		
		public void setCategory(Category category) {
			this.category = category;
		}

	//================Menu-DailyMenu :: One Menu HAS-A Many DailyMenu================
		@OneToMany(mappedBy = "menu",cascade = CascadeType.ALL,orphanRemoval = true)
		@JsonIgnore
		public List<DailyMenu> getDailyMenuList() {
			return dailyMenuList;
		}

		public void setDailyMenuList(List<DailyMenu> dailyMenuList) {
			this.dailyMenuList = dailyMenuList;
		}
		
				
		

		
}
