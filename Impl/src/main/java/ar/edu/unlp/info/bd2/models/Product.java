package ar.edu.unlp.info.bd2.models;

public class Product {
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Float getWeight() {
		return weight;
	}
	public void setWeight(Float weight) {
		this.weight = weight;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		this.Id = id;
	}
	public int Id;
	public String name;
	public Float weight;
	public Category category;
	
	public Product(String name, Float weight, Category category) {
		this.name = name;
		this.weight = weight;
		this.category = category;
	}
}