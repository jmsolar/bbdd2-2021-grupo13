package ar.edu.unlp.info.bd2.models;

import java.util.Date;

public class User {
	public String getFullname() {
		return fullname;
	}
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Date getDayOfBirth() {
		return dayOfBirth;
	}
	public void setDayOfBirth(Date dayOfBirth) {
		this.dayOfBirth = dayOfBirth;
	}
	public String fullname;
	public String email;
	public String password;
	public Date dayOfBirth;
	
	public User (String email, String fullname, String password, Date dayOfBirth) {
		this.email = email;
		this.fullname = fullname;
		this.password = password;
		this.dayOfBirth = dayOfBirth;
	}
}