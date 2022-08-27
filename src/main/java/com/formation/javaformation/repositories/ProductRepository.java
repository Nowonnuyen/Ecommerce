package com.formation.javaformation.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.formation.javaformation.entities.Product;

@Component
public interface ProductRepository extends JpaRepository<Product, UUID> {

}
