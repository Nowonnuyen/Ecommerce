package com.formation.javaformation.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.formation.javaformation.entities.OrderClient;

@Component
public interface OrderRepository extends JpaRepository<OrderClient, UUID> {

}
