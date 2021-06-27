package com.techmentor.Model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class EmailSubscriber {
	@Id
	String email;
	
	boolean status;

	public EmailSubscriber() {
		super();
		// TODO Auto-generated constructor stub
	}

	public EmailSubscriber(String email, boolean status) {
		super();
		this.email = email.trim();
		this.status = status;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

}
