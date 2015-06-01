package com.haivu.spring.jpa.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name = "User")
public class User {

	public enum StatusUser {
		ENABLE, DIASBLE, DELETE
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "userId", length = 11)
	private int userId;

	@Column(name = "userName", length = 100)
	@Size(min = 4, max = 20, message = "The user name must be more than 4 and less than 20 characters long")
	@Pattern(regexp = "^[a-zA-Z0-9_.]+$", message = "The user name can only consist of alphabetical, number, dot and underscore")
	private String userName;

	@Column(name = "pwd", length = 100)
	@Size(min = 6, max = 30, message = "The user name must be more than 6 and less than 30 characters long")
	private String pwd;

	@Column(name = "fullName", length = 100)
	@NotNull(message = "The full name is required and can't be empty")
	private String fullName;

	@Column(name = "dateOfBirth")
	private Date dateOfBirth;

	@Column(name = "email", length = 100)
	@NotNull(message = "The email address is required and can't be empty")
	@Pattern(regexp = "[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}", message = "The input is not a valid email address")
	private String email;

	@Column(name = "status")
	@Enumerated(EnumType.STRING)
	private StatusUser status;

	public User() {
		super();
	}

	public User(int userId, String userName, String pwd, String fullName,
			java.util.Date date, String email, StatusUser status) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.pwd = pwd;
		this.fullName = fullName;
		this.dateOfBirth = date;
		this.email = email;
		this.status = status;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public StatusUser getStatus() {
		return status;
	}

	public void setStatus(StatusUser status) {
		this.status = status;
	}

}
