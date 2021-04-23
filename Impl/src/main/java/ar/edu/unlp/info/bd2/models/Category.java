package ar.edu.unlp.info.bd2.models;

import javax.persistence.*;

@Entity
@Table(name = "BD2_CATEGORY")
public class Category {
	@GeneratedValue
	@Column(name = "id_category")
	@Id
	
	public int Id;
	
	@Column(name = "category_name")
	public String name;
	
	public int getId() {
		return Id;
	}

	public void setId(int id) {
		this.Id = id;
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