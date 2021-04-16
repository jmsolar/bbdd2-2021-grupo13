package ar.edu.unlp.info.bd2.models;

public class Category {
	public String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Category(String name) {
		this.name = name;
	}
}