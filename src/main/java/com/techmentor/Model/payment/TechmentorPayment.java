package com.techmentor.Model.payment;

import com.sun.istack.NotNull;
import com.techmentor.Model.Course;
import com.techmentor.Model.User;

import javax.persistence.*;
@Entity
public class TechmentorPayment {

@Id
String orderid;
@Column(nullable = true)
String paymentid;
@Column(nullable = true)
String signature;
int amount;
@NotNull
@ManyToOne
@JoinColumn(columnDefinition = "email")
User user;
@ManyToOne
@JoinColumn(columnDefinition = "id")
Course course;




public TechmentorPayment(String orderid, String paymentid, String signature, int amount, User user, Course course) {
	super();
	this.orderid = orderid;
	this.paymentid = paymentid;
	this.signature = signature;
	this.amount = amount;
	this.user = user;
	this.course = course;
}

public TechmentorPayment() {
	super();
	// TODO Auto-generated constructor stub
}

public String getOrderid() {
	return orderid;
}
public void setOrderid(String orderid) {
	this.orderid = orderid;
}
public String getPaymentid() {
	return paymentid;
}
public void setPaymentid(String paymentid) {
	this.paymentid = paymentid;
}
public String getSignature() {
	return signature;
}
public void setSignature(String signature) {
	this.signature = signature;
}
public User getUser() {
	return user;
}
public void setUser(User user) {
	this.user = user;
}

public int getAmount() {
	return amount;
}

public void setAmount(int amount) {
	this.amount = amount;
}

public Course getCourse() {
	return course;
}

public void setCourse(Course course) {
	this.course = course;
}

@Override
public String toString() {
	return "TechmentorPayment [orderid=" + orderid + ", paymentid=" + paymentid + ", signature=" + signature
			+ ", amount=" + amount + ", user=" + user + ", course=" + course + "]";
}







	
}
