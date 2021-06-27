package com.techmentor.Model;


import org.springframework.context.annotation.Lazy;

import javax.persistence.*;
import java.sql.Date;

@Entity
public class Course {

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
int id;
String name;
@Column(length = 1000)
String shortdescription;
@Lob
@Lazy
String description;
String image;
@Column(columnDefinition = "boolean default true")
boolean enrollable;
@Column(length = 1)
int rating;
int fees;
Date start_course;
Date end_course;
public Course() {
	super();
	// TODO Auto-generated constructor stub
}
public Course(int id, String name, String shortdescription, String description, String image, boolean enrollable,
		int rating, int fees, Date start_course, Date end_course) {
	super();
	this.id = id;
	this.name = name;
	this.shortdescription = shortdescription;
	this.description = description;
	this.image = image;
	this.enrollable = enrollable;
	this.rating = rating;
	this.fees = fees;
	this.start_course = start_course;
	this.end_course = end_course;
}
public Course(String name, String shortdescription, String description, String image, boolean enrollable, int rating,
		int fees, Date start_course, Date end_course) {
	super();
	this.name = name;
	this.shortdescription = shortdescription;
	this.description = description;
	this.image = image;
	this.enrollable = enrollable;
	this.rating = rating;
	this.fees = fees;
	this.start_course = start_course;
	this.end_course = end_course;
}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getShortdescription() {
	return shortdescription;
}
public void setShortdescription(String shortdescription) {
	this.shortdescription = shortdescription;
}
public String getDescription() {
	return description;
}
public void setDescription(String description) {
	this.description = description;
}
public String getImage() {
	return image;
}
public void setImage(String image) {
	this.image = image;
}
public boolean isEnrollable() {
	return enrollable;
}
public void setEnrollable(boolean enrollable) {
	this.enrollable = enrollable;
}
public int getRating() {
	return rating;
}
public void setRating(int rating) {
	this.rating = rating;
}
public int getFees() {
	return fees;
}
public void setFees(int fees) {
	this.fees = fees;
}
public Date getStart_course() {
	return start_course;
}
public void setStart_course(Date start_course) {
	this.start_course = start_course;
}
public Date getEnd_course() {
	return end_course;
}
public void setEnd_course(Date end_course) {
	this.end_course = end_course;
}
@Override
public String toString() {
	return "Course [id=" + id + ", name=" + name + ", shortdescription=" + shortdescription + ", description="
			+ description + ", image=" + image + ", enrollable=" + enrollable + ", rating=" + rating + ", fees=" + fees
			+ ", start_course=" + start_course + ", end_course=" + end_course + "]";
}
}
