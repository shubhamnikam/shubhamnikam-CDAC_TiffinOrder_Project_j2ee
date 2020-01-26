package com.app.pojos.customer;

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
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.app.pojos.common.User;
import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "orders")
public class Order {

	private Integer orderId;
	private int transactionId;
	private double totalOrderPrice;
	private Date orderDate;
	private Date orderTime;
	private OrderType orderType;
	private PaymentStatusType paymentStatusType;
	
	//One Order HAS-A Many OrderItems
	List<OrderItems> orderItemsList = new ArrayList<>();
	
	//One Order HAS-A One User
	User user;
	
	public Order() {
		System.out.println("In Pojos :: Order :: ctor");
	}

	public Order(int transactionId, double totalOrderPrice, Date orderDate, Date orderTime,
			OrderType orderType) {
		super();
		this.transactionId = transactionId;
		this.totalOrderPrice = totalOrderPrice;
		this.orderDate = orderDate;
		this.orderTime = orderTime;
		this.orderType = orderType;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id", nullable = false)
	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	
	@Column(name="transactionid", nullable = false)
	public int getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}

	@Column(name="totalorderprice", nullable = false, scale = 10, precision = 2)
	public double getTotalOrderPrice() {
		return totalOrderPrice;
	}

	public void setTotalOrderPrice(double totalOrderPrice) {
		this.totalOrderPrice = totalOrderPrice;
	}
	
	@Temporal(TemporalType.DATE)
	@Column(name="date", nullable = false)
	@JsonFormat(pattern = "yyyy-MM-dd")
	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	
	@Temporal(TemporalType.TIME)
	@Column(name="time", nullable = false)
	public Date getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(Date orderTime) {
		this.orderTime = orderTime;
	}

	@Enumerated(EnumType.STRING)
	@Column(name="type")
	public OrderType getOrderType() {
		return orderType;
	}

	public void setOrderType(OrderType orderType) {
		this.orderType = orderType;
	}

	@Enumerated(EnumType.STRING)
	@Column(name="status")
	public PaymentStatusType getPaymentStatusType() {
		return paymentStatusType;
	}
	public void setPaymentStatusType(PaymentStatusType paymentStatusType) {
		this.paymentStatusType = paymentStatusType;
	}

	@Override
	public String toString() {
		return "Order [orderId=" + orderId + ", transactionId=" + transactionId
				+ ", totalOrderPrice=" + totalOrderPrice + ", orderDate=" + orderDate + ", orderTime=" + orderTime
				+ ", orderType=" + orderType + ", paymentStatusType=" + paymentStatusType + ", orderItemsList="
				+ orderItemsList + "]";
	}

	//================OrderItems-OrderItems :: One Order HAS-A Many OrderItems================
	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
	public List<OrderItems> getOrderItemsList() {
		return orderItemsList;
	}

	public void setOrderItemsList(List<OrderItems> orderItemsList) {
		this.orderItemsList = orderItemsList;
	}

	
	//================Order-User :: One Order HAS-A One User================
	@OneToOne
	@JoinColumn(name = "userid")
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}	
	
	
}
