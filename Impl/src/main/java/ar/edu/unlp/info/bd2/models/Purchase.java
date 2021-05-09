package ar.edu.unlp.info.bd2.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name = "BD2_PURCHASE")
public class Purchase {
	@GeneratedValue
	@Column(name = "id_purchase")
	@Id
	private int Id;	

	@OneToOne(fetch = FetchType.EAGER)
	private ProductOnSale productOnSale;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User client;
	
	@OneToOne(fetch = FetchType.EAGER)
	private DeliveryMethod deliveryMethod;
	
	@OneToOne(fetch = FetchType.EAGER)
	private PaymentMethod paymentMethod;

	@Version
	@Column(name = "version")
	private int version;
	
	@Column
	private Integer quantity;
	
	@Column
	private String address;
	
	@Column
	private Float coordX;
	
	@Column
	private Float coordY;
	
	@Column
	private Date dateOfPurchase;
	
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
	
	public Float getAmount() {
		Float priceProduct = this.getProductOnSale().getPrice();
		Float deliveryAmount = this.getDeliveryMethod().getCost();
		Float result = (priceProduct * this.getQuantity()) + deliveryAmount;
		
		return result;
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