package com.app.pojos.common;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.app.pojos.customer.Address;
import com.app.pojos.customer.Cart;
import com.app.pojos.customer.Order;
import com.app.pojos.customer.Payment;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;

@Entity
@Table(name = "users")
public class User {

	private Integer userId;
	private String userName;
	private String userEmail;
	private String userPhone;
	private String userPassword;
	private Date userJoinDate;
	private UserRoleType userRole;

	// One User HAS-A Many Address
	List<Address> addressList = new ArrayList<>();

	// One User HAS-A Many Payment
	List<Payment> paymentList = new ArrayList<>();

	// One User HAS-A One Order
	Order order;
	
	// One User HAS-A One Cart
	Cart cart;

	public User() {
		System.out.println("In Pojos :: User :: ctor");
	}

	public User(String userName, String userEmail, String userPhone, String userPassword, Date userJoinDate,
			UserRoleType userRole) {
		super();
		this.userName = userName;
		this.userEmail = userEmail;
		this.userPassword = userPassword;
		this.userPhone = userPhone;
		this.userJoinDate = userJoinDate;
		this.userRole = userRole;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	@Column(name = "name", length = 50, nullable = false)
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Column(name = "email", length = 50, nullable = false, unique = true)
	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	@Column(name = "phone", nullable = false)
	public String getUserPhone() {
		return userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

	@Column(name = "password", nullable = false)
	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "joindate")
	public Date getUserJoinDate() {
		return userJoinDate;
	}

	public void setUserJoinDate(Date userJoinDate) {
		this.userJoinDate = userJoinDate;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "role")
	public UserRoleType getUserRole() {
		return userRole;
	}

	public void setUserRole(UserRoleType userRole) {
		this.userRole = userRole;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", userName=" + userName + ", userEmail=" + userEmail + ", userPhone="
				+ userPhone + ", userPassword=" + userPassword + ", userJoinDate=" + userJoinDate + ", userRole="
				+ userRole + "]";
	}

	// ================User-Address :: One User HAS-A Many Address================
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
	public List<Address> getAddressList() {
		return addressList;
	}

	public void setAddressList(List<Address> addressList) {
		this.addressList = addressList;
	}

	// ================User-Payment :: One User HAS-A Many Payment================
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
	public List<Payment> getPaymentList() {
		return paymentList;
	}

	public void setPaymentList(List<Payment> paymentList) {
		this.paymentList = paymentList;
	}

	// ================User-Order :: One User HAS-A One Order================
	@OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	// ================User-Cart :: One User HAS-A One Cart================
	
	@OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

}
