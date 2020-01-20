package com.app.pojos.customer;

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

import com.app.pojos.common.User;

@Entity
@Table(name = "address")
public class Address {

	private Integer addressId;
	private AddressType addressType;
	private String addressFieldOne;
	private String addressFieldTwo;
	private String addressCity;
	private String addressState;
	private int addressPincode;
	
	//Many Address HAS-A One User
	private User user; 
	
	
	public Address() {
		System.out.println("In Pojos :: Address :: ctor");
	}

	public Address(AddressType addressType, String addressFieldOne, String addressFieldTwo,
			String addressCity, String addressState, int addressPincode) {
		super();
		this.addressType = addressType;
		this.addressFieldOne = addressFieldOne;
		this.addressFieldTwo = addressFieldTwo;
		this.addressCity = addressCity;
		this.addressState = addressState;
		this.addressPincode = addressPincode;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id", nullable = false)
	public Integer getAddressId() {
		return addressId;
	}

	public void setAddressId(Integer addressId) {
		this.addressId = addressId;
	}

	@Enumerated(EnumType.STRING)
	@Column(name="type", nullable = false)
	public AddressType getAddressType() {
		return addressType;
	}

	public void setAddressType(AddressType addressType) {
		this.addressType = addressType;
	}

	@Column(name="fieldone", nullable = false)
	public String getAddressFieldOne() {
		return addressFieldOne;
	}

	public void setAddressFieldOne(String addressFieldOne) {
		this.addressFieldOne = addressFieldOne;
	}

	@Column(name="fieldtwo", nullable = false)
	public String getAddressFieldTwo() {
		return addressFieldTwo;
	}

	public void setAddressFieldTwo(String addressFieldTwo) {
		this.addressFieldTwo = addressFieldTwo;
	}

	@Column(name="city", nullable = false)
	public String getAddressCity() {
		return addressCity;
	}

	public void setAddressCity(String addressCity) {
		this.addressCity = addressCity;
	}

	@Column(name="state", nullable = false)
	public String getAddressState() {
		return addressState;
	}

	public void setAddressState(String addressState) {
		this.addressState = addressState;
	}


	@Column(name="pincode", nullable = false)
	public int getAddressPincode() {
		return addressPincode;
	}

	public void setAddressPincode(int addressPincode) {
		this.addressPincode = addressPincode;
	}

	@Override
	public String toString() {
		return "Address [addressId=" + addressId + ", addressType=" + addressType
				+ ", addressFieldOne=" + addressFieldOne + ", addressFieldTwo=" + addressFieldTwo + ", addressCity="
				+ addressCity + ", addressState=" + addressState + ", addressPincode=" + addressPincode + "]";
	}

	//================Address-User :: Many Address HAS-A One User================
	@ManyToOne
	@JoinColumn(name="userid")
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	

}











