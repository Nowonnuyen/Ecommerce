package com.formation.javaformation.entities;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name="orderClient")
public class OrderClient extends Material {
		//private UUID id;
		//private String label;
		@OneToMany(cascade = CascadeType.ALL,mappedBy = "orderClient", fetch = FetchType.LAZY)
		private List<OrderItem> orderItemList; 

		@ManyToOne
		@JoinColumn(name = "customer_id") //foreign key
		private Customer customer;
		//methods
	
		public List<OrderItem> getOrderItemList() {
			return orderItemList;
		}

		public void setOrderItemList(List<OrderItem> orderItemList) {
			this.orderItemList = orderItemList;
		}

		//liasion avec mon custoemr
		public Customer getCustomer() {
			return customer;
		}
		public void setCustomer(Customer customer) {
			this.customer = customer;
		}

	}

