package ar.edu.unlp.info.bd2.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity(name = "BD2_CREDIT_CARD_PAYMENT")
@DiscriminatorValue("credit_card_payment")
public class CreditCardPayment extends PaymentMethod {
	@Column
	public String brand;
	
	@Column
	public Long number;
	
	@Column
	public Date expiry;
	
	@Column
	public Integer cvv;
	
	@Column
	public String owner;
	
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public Long getNumber() {
		return number;
	}
	public void setNumber(Long number) {
		this.number = number;
	}
	public Date getExpiry() {
		return expiry;
	}
	public void setExpiry(Date expiry) {
		this.expiry = expiry;
	}
	public Integer getCvv() {
		return cvv;
	}
	public void setCvv(Integer cvv) {
		this.cvv = cvv;
	}
	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}

	public CreditCardPayment(String name, String brand, Long number, Date expiry, Integer cvv, String owner) {
		super(name);
		this.brand = brand;
		this.number = number;
		this.expiry = expiry;
		this.cvv = cvv;
		this.owner = owner;
	}
}