package com.foodapp.restapi.model;

import java.util.List;
import java.util.Map;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "order")
public class Order {
	
	public static final String SEQUENCE_NAME = "order_sequence"; 
	
	@Id
	private Long id;
	
	private Long restId;
	
	@DBRef
	private Map<MenuItem,Integer> menuItems;
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}




	@Override
	public String toString() {
		return "Order [id=" + id + ", restId=" + restId + "]";
	}

	public Long getRestId() {
		return restId;
	}

	public void setRestId(Long restId) {
		this.restId = restId;
	}

	public Map<MenuItem, Integer> getMenuItems() {
		return menuItems;
	}

	public void setMenuItems(Map<MenuItem, Integer> menuItems) {
		this.menuItems = menuItems;
	}
	
}
