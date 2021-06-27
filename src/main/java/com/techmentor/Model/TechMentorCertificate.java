package com.techmentor.Model;

import javax.persistence.*;
import java.sql.Date;

@Entity
public class TechMentorCertificate {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	int id;
	String name;
	String email;
	String coursename;
	Date courseStartDate;
	Date courseEndDate;
	@Column(columnDefinition = "boolean default false")
	boolean status;
	@OneToOne()
	@JoinColumn(name = "id")
	Course course;
	@OneToOne()
	@JoinColumn(name = "email",insertable = false,updatable = false	)
	User user;

	public TechMentorCertificate() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TechMentorCertificate(int id, String name, String email, String coursename, Date courseStartDate,
			Date courseEndDate, boolean status, Course course, User user) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.coursename = coursename;
		this.courseStartDate = courseStartDate;
		this.courseEndDate = courseEndDate;
		this.status = status;
		this.course = course;
		this.user = user;
	}

	public TechMentorCertificate(String name, String email, String coursename, Date courseStartDate, Date courseEndDate,
			boolean status, Course course, User user) {
		super();
		this.name = name;
		this.email = email;
		this.coursename = coursename;
		this.courseStartDate = courseStartDate;
		this.courseEndDate = courseEndDate;
		this.status = status;
		this.course = course;
		this.user = user;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCoursename() {
		return coursename;
	}

	public void setCoursename(String coursename) {
		this.coursename = coursename;
	}

	public Date getCourseStartDate() {
		return courseStartDate;
	}

	public void setCourseStartDate(Date courseStartDate) {
		this.courseStartDate = courseStartDate;
	}

	public Date getCourseEndDate() {
		return courseEndDate;
	}

	public void setCourseEndDate(Date courseEndDate) {
		this.courseEndDate = courseEndDate;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "TechMentorCertificate [id=" + id + ", name=" + name + ", email=" + email + ", coursename=" + coursename
				+ ", courseStartDate=" + courseStartDate + ", courseEndDate=" + courseEndDate + ", status=" + status
				+ ", course=" + course + ", user=" + user + "]";
	}

}
