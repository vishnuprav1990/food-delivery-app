package com.foodapp.restapi.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "menuitem")
public class MenuItem {
	
	public static final String SEQUENCE_NAME = "menuitem_sequence"; 
	
	@Id
	private Long id;
	
	private String name;
	
	private Long price;
	
	private Long restId;

	public Long getRestId() {
		return restId;
	}

	public void setRestId(Long restId) {
		this.restId = restId;
	}

	public Long getPrice() {
		return price;
	}

	public void setPrice(Long price) {
		this.price = price;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "MenuItem [id=" + id + ", name=" + name + ", restId=" + restId + ", price=" + price +  "]";
	}
	
}
