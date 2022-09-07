package com.formation.javaformation.repositories;

import com.formation.javaformation.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public interface CustomerRepository extends JpaRepository<Customer, UUID> {
}
