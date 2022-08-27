package com.formation.javaformation.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="customer")
public class Customer extends Material {

	//private int id;
	private String firstname;
	private String lastname;
	@OneToOne(mappedBy = "customer", cascade = CascadeType.ALL,
			fetch = FetchType.LAZY, optional = false)
	private Address address;
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "customer", fetch = FetchType.LAZY)
	private List<OrderClient> orderClientList; 



	/*
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	 */
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}



	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	//mon getter liste de Order pas de setter

	public List<OrderClient> getOrderClientList() {
		return orderClientList;
	}

	public void addOrderClient(OrderClient orderClient) {
		if(this.orderClientList==null) {
			this.orderClientList = new ArrayList<>();
		}
		this.orderClientList.add(orderClient);
		orderClient.setCustomer(this); //on fait la liaison du produit avec le client cette meme liasion qui doit etre couper avant toute suppression
		//eviter les pb d 'orphelin
	}

	//méthode supprimer
	public void removeOrderClient(OrderClient orderClient){
		this.orderClientList = this.orderClientList.stream().map(p->{
			if(p.equals(orderClient)) {
				unlinkedOrderToRemoveToCustomer(orderClient); //on va utilser la méthode pour délier plus bas dans un premier temps 
			}
			return p;
		}).collect(Collectors.toList()); // La méthode toList() de Collectors Class est une méthode statique (classe).
		//Il renvoie une interface de collecteur qui rassemble les données d’entrée dans une nouvelle liste.


	}

	//couper la liaison entre la commande et son client avant de supprimer (ce que java demande)
	private static OrderClient unlinkedOrderToRemoveToCustomer (OrderClient orderClient) {
		orderClient.setCustomer(null);
		return orderClient;
	}
}
