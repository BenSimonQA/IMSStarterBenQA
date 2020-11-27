package com.qa.ims.persistence.domain;

public class Item {

	private Long id;
	private String itemName;
	private Long price;

	public Item(String itemName, Long price) {
		this.itemName = itemName;
		this.price = price;
	}

	public Item(Long id, String itemName, Long price) {
		this.id = id;
		this.itemName = itemName;
		this.price = price;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public Long getPrice() {
		return price;
	}

	public void setPrice(Long price) {
		this.price = price;
	}
	@Override
	public String toString() {
		return "id:" + id + " item name:" + itemName + " price:" + price;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Item other = (Item) obj;
		if (itemName == null) {
			if (other.itemName != null)
				return false;
		} else if (!itemName.equals(other.itemName))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (price == null) {
			if (other.price != null)
				return false;
		} else if (!price.equals(other.price))
			return false;
		return true;
	}
	
}
