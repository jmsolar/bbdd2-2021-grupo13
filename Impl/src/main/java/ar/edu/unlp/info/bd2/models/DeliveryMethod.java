package ar.edu.unlp.info.bd2.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name = "BD2_DELIVERY_METHOD")
public class DeliveryMethod {
	@GeneratedValue
	@Column(name = "id_delivery_method")
	@Id
	public int Id;
	
	@Column
	public String name;
	
	@Column
	public Float cost; 
	
	@Column
	public Float startWeight;
	
	@Column
	public Float endWeight;
	
	@Version
	@Column(name = "version")
	private int version;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Float getCost() {
		return cost;
	}

	public void setCost(Float cost) {
		this.cost = cost;
	}

	public Float getStartWeight() {
		return startWeight;
	}

	public void setStartWeight(Float startWeight) {
		this.startWeight = startWeight;
	}

	public Float getEndWeight() {
		return endWeight;
	}

	public void setEndWeight(Float endWeight) {
		this.endWeight = endWeight;
	}
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		this.Id = id;
	}
	
	public DeliveryMethod(String name, Float cost, Float startWeight, Float endWeight) {
		this.name = name;
		this.cost = cost;
		this.startWeight = startWeight;
		this.endWeight = endWeight;
	}
}