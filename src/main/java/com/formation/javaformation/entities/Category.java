package com.formation.javaformation.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;



@Entity
@Table(name="category")
public class Category extends Material{
	private String label;
	private String description;
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "category", orphanRemoval = true, fetch = FetchType.LAZY)
	private List<Product> productList; //pk on tretire le  = new ArrayList<>()

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

	//mon getter liste
	public List<Product> getProductList() {
		return productList;
	}

	//pas de set 
	//méthode "add"

	public void addProduct(Product product) {
		if(this.productList==null) {
			this.productList = new ArrayList<>();
		}
		this.productList.add(product);
		product.setCategory(this); //on fait la liaison du produit avec la category cette meme liasion qui doit etre couper avant toute suppression
		//eviter les pb d 'orphelin
	}

	//méthode supprimer
	public void removeProduct(Product product){
		this.productList = this.productList.stream().map(p->{
			if(p.equals(product)) {
				unlinkedProductToRemoveToCategory(product); //on va utilser la méthode pour délier plus bas dans un premier temps 
			}
			return p;
		}).collect(Collectors.toList()); // La méthode toList() de Collectors Class est une méthode statique (classe).
		//Il renvoie une interface de collecteur qui rassemble les données d’entrée dans une nouvelle liste.


	}
	
	//couper la liaison entre le produit et sa catégory avant de supprimer (ce que java demande)
	private static Product unlinkedProductToRemoveToCategory (Product product) {
		product.setCategory(null);
		return product;
	}
}
