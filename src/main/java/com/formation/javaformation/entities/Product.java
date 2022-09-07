package com.formation.javaformation.entities;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="product")
public class Product extends Material{

	private String label;
	private String description;
	private int price;
	@OneToOne
	@JoinColumn(name = "category_id")
	private Category category; //correspond au 'category' du mappedBy du OneToMnay de la classe Category
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "product", fetch = FetchType.LAZY)
	private List<OrderItem> orderItemList;
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	//liaison de ma category
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}

	
	//liste order
	
	public List<OrderItem> getOrderItemList() {
		return orderItemList;
	}
	public void setOrderItemList(List<OrderItem> orderItemList) {
		this.orderItemList = orderItemList;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
