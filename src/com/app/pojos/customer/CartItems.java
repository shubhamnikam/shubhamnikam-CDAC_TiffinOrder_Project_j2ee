package com.app.pojos.customer;

import javax.persistence.*;

@Entity
@Table(name = "cartitems")
public class CartItems {

	private Integer cartItemsId;
	private int cartId;
	private int menuId;
	private String cartItemsMenuName;
	private double cartItemsPrice;
	private int cartItemsQuantity;
	private double cartItemsTotalPrice;
	
	//Many CartItems HAS-A One Cart
	Cart cart;

	
	public CartItems() {
		System.out.println("In Pojos :: CartItems :: ctor");
	}
	
	
	public CartItems(int cartId, int menuId, String cartItemsMenuName, double cartItemsPrice, int cartItemsQuantity,
			double cartItemsTotalPrice) {
		super();
		this.cartId = cartId;
		this.menuId = menuId;
		this.cartItemsMenuName = cartItemsMenuName;
		this.cartItemsPrice = cartItemsPrice;
		this.cartItemsQuantity = cartItemsQuantity;
		this.cartItemsTotalPrice = cartItemsTotalPrice;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id", nullable = false)
	public int getCartId() {
		return cartId;
	}

	public void setCartId(int cartId) {
		this.cartId = cartId;
	}

	public Integer getCartItemsId() {
		return cartItemsId;
	}


	public void setCartItemsId(Integer cartItemsId) {
		this.cartItemsId = cartItemsId;
	}


	public int getMenuId() {
		return menuId;
	}


	public void setMenuId(int menuId) {
		this.menuId = menuId;
	}

	@Column(name="name", nullable = false)
	public String getCartItemsMenuName() {
		return cartItemsMenuName;
	}


	public void setCartItemsMenuName(String cartItemsMenuName) {
		this.cartItemsMenuName = cartItemsMenuName;
	}


	@Column(name="price", scale = 10, precision = 2)
	public double getCartItemsPrice() {
		return cartItemsPrice;
	}


	public void setCartItemsPrice(double cartItemsPrice) {
		this.cartItemsPrice = cartItemsPrice;
	}


	@Column(name="quantity")
	public int getCartItemsQuantity() {
		return cartItemsQuantity;
	}


	public void setCartItemsQuantity(int cartItemsQuantity) {
		this.cartItemsQuantity = cartItemsQuantity;
	}


	@Column(name="totalprice", scale = 10, precision = 2)
	public double getCartItemsTotalPrice() {
		return cartItemsTotalPrice;
	}


	public void setCartItemsTotalPrice(double cartItemsTotalPrice) {
		this.cartItemsTotalPrice = cartItemsTotalPrice;
	}

	@Override
	public String toString() {
		return "CartItems [cartItemsId=" + cartItemsId + ", cartId=" + cartId + ", menuId=" + menuId
				+ ", cartItemsMenuName=" + cartItemsMenuName + ", cartItemsPrice=" + cartItemsPrice
				+ ", cartItemsQuantity=" + cartItemsQuantity + ", cartItemsTotalPrice=" + cartItemsTotalPrice + "]";
	}
	
	
	//================CartItems-Cart :: Many CartItems HAS-A One Cart================
	@ManyToOne
	@JoinColumn(name = "cartid")
	public Cart getCart() {
		return cart;
	}
	public void setCart(Cart cart) {
		this.cart = cart;
	}

	
	
	
	
}
