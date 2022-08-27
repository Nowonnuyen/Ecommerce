package com.formation.javaformation.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;

@Entity
public class PostTags extends Material {
	
	//private String id;
	//private String label;
	
	@ManyToMany(mappedBy = "tags")
	private List<Post> posts = new ArrayList<>()
	
;

	public List<Post> getPosts() {
		return posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}}
