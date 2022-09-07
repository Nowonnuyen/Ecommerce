package com.formation.javaformation.entities;

import javax.persistence.*;

@Entity
@Table(name="orderItem")
public class OrderItem extends Material {

	private int quantite;
	
	@OneToOne
	@JoinColumn(name = "product_id") //foreign key
	private Product product;
	
	@ManyToOne
	@JoinColumn(name = "orderClient_id") //foreign key
	private OrderClient orderClient;


	public int getQuantite() {
		return quantite;
	}


	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}


	public OrderClient getOrderClient() {
		return orderClient;
	}


	public void setOrderClient(OrderClient orderCLient) {
		this.orderClient = orderClient;
	}
	
	
}
