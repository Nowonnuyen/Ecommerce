package com.formation.javaformation.entities;



import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="product")
public class Product extends Material{


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
}
