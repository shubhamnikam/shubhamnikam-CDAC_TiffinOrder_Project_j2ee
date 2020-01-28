package com.app.pojos.customer;

import java.util.Date;

import javax.persistence.*;

import com.app.pojos.common.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "payment")
public class Payment {

	private Integer paymentId;
	private String paymentUPI;
	private String paymentCardNumber;
	private Date paymentCardExpiryDate;
	private int paymentCardCVV;
	
	//Many Payment HAS-A One User
	private User user;
		
	public Payment() {
		System.out.println("In Pojo :: Payment :: ctor");
	}

	public Payment(String paymentUPI, String paymentCardNumber, Date paymentCardExpiryDate, int paymentCardCVV) {
		super();
		this.paymentUPI = paymentUPI;
		this.paymentCardNumber = paymentCardNumber;
		this.paymentCardExpiryDate = paymentCardExpiryDate;
		this.paymentCardCVV = paymentCardCVV;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id", nullable = false)
	public Integer getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(Integer paymentId) {
		this.paymentId = paymentId;
	}
	
	@Column(name="upi", unique = true)
	public String getPaymentUPI() {
		return paymentUPI;
	}

	public void setPaymentUPI(String paymentUPI) {
		this.paymentUPI = paymentUPI;
	}
	
	@Column(name="cardnumber")
	public String getPaymentCardNumber() {
		return paymentCardNumber;
	}

	public void setPaymentCardNumber(String paymentCardNumber) {
		this.paymentCardNumber = paymentCardNumber;
	}
	
	@Temporal(TemporalType.DATE)
	@Column(name="cardexpirydate")
	public Date getPaymentCardExpiryDate() {
		return paymentCardExpiryDate;
	}

	public void setPaymentCardExpiryDate(Date paymentCardExpiryDate) {
		this.paymentCardExpiryDate = paymentCardExpiryDate;
	}

	@Column(name="cardcvv")
	public int getPaymentCardCVV() {
		return paymentCardCVV;
	}

	public void setPaymentCardCVV(int paymentCardCVV) {
		this.paymentCardCVV = paymentCardCVV;
	}

	@Override
	public String toString() {
		return "Payment [paymentId=" + paymentId + ", paymentUPI=" + paymentUPI + ", paymentCardNumber="
				+ paymentCardNumber + ", paymentCardExpiryDate=" + paymentCardExpiryDate + ", paymentCardCVV="
				+ paymentCardCVV + "]";
	}

	//================Address-User :: Many Payment HAS-A One User================
		@ManyToOne
		@JsonManagedReference
		@JsonIgnore
		@JoinColumn(name="userid")
		public User getUser() {
			return user;
		}

		public void setUser(User user) {
			this.user = user;
		}
}
