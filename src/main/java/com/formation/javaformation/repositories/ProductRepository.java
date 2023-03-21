package com.formation.javaformation.repositories;

import com.formation.javaformation.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public interface ProductRepository extends JpaRepository<Product, Long> {
	
	

}
