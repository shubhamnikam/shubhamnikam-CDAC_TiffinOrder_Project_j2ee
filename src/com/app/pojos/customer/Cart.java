package com.app.pojos.customer;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import com.app.pojos.common.User;

@Entity
@Table(name = "cart")
public class Cart {

	private Integer cartId;
	private double cartPrice;
	private Date cartDate;
	private Date cartTime;
	private OrderType orderType;

	// One Cart HAS-A Many CartItems
	List<CartItems> cartItemsList = new ArrayList<>();

	// One Cart HAS-A One User
	User user;

	public Cart() {
		System.out.println("In Pojos :: Cart :: ctor");
	}

	public Cart( double cartPrice, Date cartDate, Date cartTime, OrderType orderType) {
		super();
		this.cartPrice = cartPrice;
		this.cartDate = cartDate;
		this.cartTime = cartTime;
		this.orderType = orderType;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	public Integer getCartId() {
		return cartId;
	}

	public void setCartId(Integer cartId) {
		this.cartId = cartId;
	}


	@Column(name = "price", nullable = false, scale = 10, precision = 2)
	public double getCartPrice() {
		return cartPrice;
	}

	public void setCartPrice(double cartPrice) {
		this.cartPrice = cartPrice;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "date")
	public Date getCartDate() {
		return cartDate;
	}

	public void setCartDate(Date cartDate) {
		this.cartDate = cartDate;
	}

	@Temporal(TemporalType.TIME)
	@Column(name = "time")
	public Date getCartTime() {
		return cartTime;
	}

	public void setCartTime(Date cartTime) {
		this.cartTime = cartTime;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "type")
	public OrderType getOrderType() {
		return orderType;
	}

	public void setOrderType(OrderType orderType) {
		this.orderType = orderType;
	}

	@Override
	public String toString() {
		return "Cart [cartId=" + cartId +", cartPrice=" + cartPrice + ", cartDate=" + cartDate
				+ ", cartTime=" + cartTime + ", orderType=" + orderType + "]";
	}

	// ================CartItems-CartItems :: One Cart HAS-A Many CartItems================
	@OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
	public List<CartItems> getCartItemsList() {
		return cartItemsList;
	}

	public void setCartItemsList(List<CartItems> cartItemsList) {
		this.cartItemsList = cartItemsList;
	}

	// ================Cart-User :: One Cart HAS-A One User================
	@OneToOne
	@JoinColumn(name = "userid")
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
