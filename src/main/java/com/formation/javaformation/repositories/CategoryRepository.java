package com.formation.javaformation.repositories;

import com.formation.javaformation.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public interface CategoryRepository extends JpaRepository<Category, Long> {

}
