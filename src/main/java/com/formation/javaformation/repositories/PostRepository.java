package com.formation.javaformation.repositories;

import com.formation.javaformation.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PostRepository extends JpaRepository<Post, UUID> {

}
