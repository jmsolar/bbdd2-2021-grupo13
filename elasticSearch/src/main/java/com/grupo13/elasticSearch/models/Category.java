package com.grupo13.elasticSearch.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Document(indexName = "categories")
public class Category {
	@Id
	@Field(type = FieldType.Auto)
	private String id;
	
	@Field(type = FieldType.Text)
	private String name;
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@JsonCreator
	public Category() {}

	@JsonCreator
	public Category(@JsonProperty("name") String name) {
		this.name = name;
	}
}