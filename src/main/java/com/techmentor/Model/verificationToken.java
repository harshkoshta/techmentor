package com.techmentor.Model;

import javax.persistence.*;
@Entity
public class verificationToken {
@Id
@GeneratedValue(strategy = GenerationType.AUTO)
int id;
String email;
@Column(columnDefinition = "boolean default false")
boolean expire;
public verificationToken() {
	super();
	// TODO Auto-generated constructor stub
}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public boolean isExpire() {
	return expire;
}
public void setExpire(boolean expire) {
	this.expire = expire;
}


}
