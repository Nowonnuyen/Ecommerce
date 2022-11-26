package com.formation.javaformation.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name="customer")
public class Customer extends Material {
	private String firstname;
	private String username;
	private String lastname;
	@OneToOne(mappedBy = "customer", cascade = CascadeType.ALL,
			fetch = FetchType.LAZY, optional = false)
	private Address address;
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "customer", fetch = FetchType.LAZY)
	private List<OrderClient> orderClientList;
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "customer", fetch = FetchType.LAZY)
	private List<Avis> avisList;
	@Transient
	private String avis;
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
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
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
	public void addAvis(Avis avis) {
		if(this.avisList==null) {
			this.avisList = new ArrayList<>();
		}
		this.avisList.add(avis);
		avis.setCustomer(this);
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

	public String getAvis() {
		return avis;
	}

	public void setAvis(String avis) {
		this.avis = avis;
	}
}
