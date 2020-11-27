package com.qa.ims.persistence.domain;

import java.util.List;

public class Order {

	private Long id;
	private Long customer_id;
	private List<Item> item_id;
	private Long item_price;

	public Order(Long customer_id) {
		this.customer_id = customer_id;
	}
	
	public Order(Long id, Long customer_id) {
		this.id = id;
		this.customer_id = customer_id;
	}

	public Order(Long id ,Long customer_id, List<Item> item_id) {
		this.id = id;
		this.customer_id = customer_id;
		this.item_id = item_id;
	}
	
	public Order(Long id ,Long customer_id, List<Item> item_id, Long item_price) {
		this.id = id;
		this.customer_id = customer_id;
		this.item_id = item_id;
		this.item_price = item_price;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCustomerId() {
		return customer_id;
	}

	public void setCustomerId(Long customer_id) {
		this.customer_id = customer_id;
	}

	public List<Item> getItemId() {
		return item_id;
	}

	public void setItemId(List<Item> item_id) {
		this.item_id = item_id;
	}

	@Override
	public String toString() {
		return "id:" + id + " customer id:" + customer_id + " item_id:" + item_id + " total_price:" + item_price;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order other = (Order) obj;
		if (customer_id == null) {
			if (other.customer_id != null)
				return false;
		} else if (!customer_id.equals(other.customer_id))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (item_id == null) {
			if (other.item_id != null)
				return false;
		} else if (!item_id.equals(other.item_id))
			return false;
		return true;
	}

	
}
