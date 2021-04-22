package ar.edu.unlp.info.bd2.models;

public class Category {
	public int Id;
	public String name;
	
	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

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