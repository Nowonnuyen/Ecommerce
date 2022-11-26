package com.formation.javaformation.initdatabase;

import com.formation.javaformation.entities.*;
import com.formation.javaformation.repositories.*;
import org.springframework.boot.CommandLineRunner;

import java.util.UUID;


public class InitDatabaseRunner implements CommandLineRunner {
	private final CategoryRepository categoryRepository;
	private final ProductRepository productRepository;
	private final CustomerRepository customerRepository;
	private final OrderRepository orderRepository;
	private final PostRepository postRepository;

	public InitDatabaseRunner(CategoryRepository categoryRepository, ProductRepository productRepository,CustomerRepository customerRepository, OrderRepository orderRepository,PostRepository postRepository) {
		this.categoryRepository = categoryRepository;
		this.productRepository = productRepository;
		this.customerRepository = customerRepository;  
		this.orderRepository = orderRepository;
		this.postRepository = postRepository;
	}

	@Override
	public void run(String... args) throws Exception {
		Category categoryOne = new Category();
		
		categoryOne.setLabel("Boisson");

		Category categoryTwo = new Category();

		categoryTwo.setLabel("Plat");


		Category categoryThree = new Category();
		
		categoryThree.setLabel("Dessert");

		//******

		Product productOne = new Product();
	
		productOne.setLabel("Perrier");
		categoryOne.addProduct(productOne);

		Product productTwo = new Product();
		
		productTwo.setLabel("Coca");
		categoryOne.addProduct(productTwo);


	
	


		Product productFour = new Product();
		
		productFour.setLabel("Fraisier");
		categoryThree.addProduct(productFour);


		this.categoryRepository.save(categoryOne); // cascadeType.ALL donc pas besoin de save les produits juste les conteneurs
		this.categoryRepository.save(categoryTwo);
		this.categoryRepository.save(categoryThree);


		//****
		Customer customerOne = new Customer();
		customerOne.setFirstname("Khang");
		customerOne.setLastname("Nguyen");
		Address addressOne = new Address();
		addressOne.setCity("Rennes");
		addressOne.setZipCode(35000);
		addressOne.setRoad("Rue du Bac");
		
		customerOne.setAddress(addressOne);
		addressOne.setCustomer(customerOne);
		
		this.customerRepository.save(customerOne);
		
		Post PostOne = new Post();
		PostOne.setLabel("Projet 1");
		PostOne.setDescription("Projet E commerce");
		
		PostTags PostTagOne = new PostTags();
		PostTagOne.setLabel("Trend");
		
		
 
	}

}
