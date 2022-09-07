package com.formation.javaformation.repositories;

import com.formation.javaformation.entities.OrderClient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public interface OrderRepository extends JpaRepository<OrderClient, UUID> {

}
