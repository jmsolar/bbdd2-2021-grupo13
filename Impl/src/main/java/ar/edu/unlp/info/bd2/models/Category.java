package ar.edu.unlp.info.bd2.models;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.persistence.GeneratedValue;
import javax.persistence.Column;
import javax.persistence.Id;

@Entity
@Table(name = "BD2_CATEGORY")
public class Category {
	@GeneratedValue
	@Column(name = "id_category")
	@Id
	public int Id;
	
	@Column
	public String name;
	
	@Version
	@Column(name = "version")
	private int version;
	
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