package com.app.pojos.customer;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "orderitems")
public class OrderItems {

	private Integer orderItemsId;
	private int menuId;
	private String orderItemsMenuName;
	private double orderItemsPrice;
	private int orderItemsQuantity;
	private double orderItemsTotalPrice; 
	

	//Many OrderItems HAS-A One Order
	Order order;
	
	
	public OrderItems() {
		System.out.println("In Pojos :: OrderItems :: ctor");
	}

	public OrderItems(int menuId, String orderItemsMenuName, double orderItemsPrice,
			int orderItemsQuantity, double orderItemsTotalPrice) {
		super();
		this.menuId = menuId;
		this.orderItemsMenuName = orderItemsMenuName;
		this.orderItemsPrice = orderItemsPrice;
		this.orderItemsQuantity = orderItemsQuantity;
		this.orderItemsTotalPrice = orderItemsTotalPrice;
	}

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id", nullable = false)
	public Integer getOrderItemsId() {
		return orderItemsId;
	}


	public void setOrderItemsId(Integer orderItemsId) {
		this.orderItemsId = orderItemsId;
	}

	public int getMenuId() {
		return menuId;
	}

	public void setMenuId(int menuId) {
		this.menuId = menuId;
	}

	@Column(name="menuname", nullable = false)
	public String getOrderItemsMenuName() {
		return orderItemsMenuName;
	}

	public void setOrderItemsMenuName(String orderItemsMenuName) {
		this.orderItemsMenuName = orderItemsMenuName;
	}

	@Column(name="price", scale=10, precision = 2)
	public double getOrderItemsPrice() {
		return orderItemsPrice;
	}

	public void setOrderItemsPrice(double orderItemsPrice) {
		this.orderItemsPrice = orderItemsPrice;
	}

	@Column(name="quantity")
	public int getOrderItemsQuantity() {
		return orderItemsQuantity;
	}

	public void setOrderItemsQuantity(int orderItemsQuantity) {
		this.orderItemsQuantity = orderItemsQuantity;
	}

	@Column(name="totalprice", scale=10, precision = 2)
	public double getOrderItemsTotalPrice() {
		return orderItemsTotalPrice;
	}

	public void setOrderItemsTotalPrice(double orderItemsTotalPrice) {
		this.orderItemsTotalPrice = orderItemsTotalPrice;
	}
	
	@Override
	public String toString() {
		return "OrderItems [orderItemsId=" + orderItemsId  + ", menuId=" + menuId
				+ ", orderItemsMenuName=" + orderItemsMenuName + ", orderItemsPrice=" + orderItemsPrice
				+ ", orderItemsQuantity=" + orderItemsQuantity + ", orderItemsTotalPrice=" + orderItemsTotalPrice + "]";
	}
	
	//================OrderItems-Order :: Many OrderItems HAS-A One Order================
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name="orderid")
	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	
	
}
