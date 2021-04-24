package ar.edu.unlp.info.bd2.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name = "BD2_PURCHASE")
public class Purchase {
	@GeneratedValue
	@Column(name = "id_purchase")
	@Id
	public int Id;	

	@OneToOne(mappedBy="productOnSale")
	public ProductOnSale productOnSale;
	
	@OneToMany(mappedBy="user")
	public User client;
	
	@OneToOne(mappedBy="deliveryMethod")
	public DeliveryMethod deliveryMethod;
	
	@OneToOne(mappedBy="paymentMethod")
	public PaymentMethod paymentMethod;
	
	@Version
	@Column(name = "version")
	private int version;
	
	public Integer quantity;
	public String address;
	public Float coordX;
	public Float coordY;
	public Date dateOfPurchase;
	
	public ProductOnSale getProductOnSale() {
		return productOnSale;
	}
	public void setProductOnSale(ProductOnSale productOnSale) {
		this.productOnSale = productOnSale;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public User getClient() {
		return client;
	}
	public void setClient(User client) {
		this.client = client;
	}
	public DeliveryMethod getDeliveryMethod() {
		return deliveryMethod;
	}
	public void setDeliveryMethod(DeliveryMethod deliveryMethod) {
		this.deliveryMethod = deliveryMethod;
	}
	public PaymentMethod getPaymentMethod() {
		return paymentMethod;
	}
	public void setPaymentMethod(PaymentMethod paymentMethod) {
		this.paymentMethod = paymentMethod;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Float getCoordX() {
		return coordX;
	}
	public void setCoordX(Float coordX) {
		this.coordX = coordX;
	}
	public Float getCoordY() {
		return coordY;
	}
	public void setCoordY(Float coordY) {
		this.coordY = coordY;
	}
	public Date getDateOfPurchase() {
		return dateOfPurchase;
	}
	public void setDateOfPurchase(Date dateOfPurchase) {
		this.dateOfPurchase = dateOfPurchase;
	}
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		this.Id = id;
	}
	
	public Purchase(ProductOnSale productOnSale, Integer quantity, User client, DeliveryMethod deliveryMethod,
			PaymentMethod paymentMethod, String address, Float coordX, Float coordY, Date dateOfPurchase) {
		this.productOnSale = productOnSale;
		this.quantity = quantity;
		this.client = client;
		this.deliveryMethod = deliveryMethod;
		this.paymentMethod = paymentMethod;
		this.address = address;
		this.coordX = coordX;
		this.coordY = coordY;
		this.dateOfPurchase = dateOfPurchase;
	}
}