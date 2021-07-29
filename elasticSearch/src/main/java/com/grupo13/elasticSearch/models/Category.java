package com.grupo13.elasticSearch.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Document(indexName = "categories", createIndex = true)
public class Category {
	
	@Id
	@Field(type = FieldType.Auto)
	private Long Id;
	
	@Field(type = FieldType.Text)
	private String name;
	
	public Long getId() {
		return Id;
	}
	
	public void setId(Long id) {
		this.Id = id;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Category() {}
	
	public Category(String name) {
		this.name = name;
	}
}