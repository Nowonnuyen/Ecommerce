package com.formation.javaformation.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;


import com.formation.javaformation.entities.Post;

public interface PostRepository extends JpaRepository<Post, UUID> {

}
