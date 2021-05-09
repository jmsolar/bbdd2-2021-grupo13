package ar.edu.unlp.info.bd2.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name = "BD2_USER")
public class User {
	@GeneratedValue
	@Column(name = "id_user")
	@Id
	private int Id;
	
	@Column
	private String fullname;
	
	@Column
	private String email;
	
	@Column
	private String password;
	
	@Column
	private Date dayOfBirth;
	
	@OneToMany(mappedBy = "client", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Purchase> purchases;
	
	@Version
	@Column(name = "version")
	private int version;
	
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
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		this.Id = id;
	}
	
	public Set<Purchase> getPurchases() {
		return purchases;
	}
	public void setPurchases(Set<Purchase> purchases) {
		this.purchases = purchases;
	}
	
	public User() {}
	
	public User (String email, String fullname, String password, Date dayOfBirth) {
		this.email = email;
		this.fullname = fullname;
		this.password = password;
		this.dayOfBirth = dayOfBirth;
	}
}