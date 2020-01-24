package com.app.pojos.owner;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "category")
public class Category {

	private Integer categoryId;
	private String categoryName;
	
	//One Category HAS-A Many Menu
	List<Menu> menuList = new ArrayList<>();
	
	
	public Category() {
		System.out.println("In Pojo :: Category :: ctor");
	}

	public Category(String categoryName) {
		super();
		this.categoryName = categoryName;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id", nullable = false)
	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	@Column(name="name", nullable = false)
	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) { 
		this.categoryName = categoryName;
	}
	
	@Override
	public String toString() {
		return "Category [categoryId=" + categoryId + ", categoryName=" + categoryName + "]";
	}

	//=================Category-Menu :: One Category HAS-A Many Menu================
	@OneToMany(mappedBy = "category", 
			cascade = CascadeType.ALL,
			orphanRemoval = true)
	@JsonIgnore
	public List<Menu> getMenuList() {
		return menuList;
	}

	public void setMenuList(List<Menu> menuList) {
		this.menuList = menuList;
	}
	
		
	
	
	
}
