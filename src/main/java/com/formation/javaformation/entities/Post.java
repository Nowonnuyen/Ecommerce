package com.formation.javaformation.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Post extends Material {
	private String label;
	private String description;
	@ManyToMany(cascade = {
			CascadeType.PERSIST,
			CascadeType.MERGE
	})
	@JoinTable(name = "post_tag",
	joinColumns = @JoinColumn(name = "post_id"),
	inverseJoinColumns = @JoinColumn(name = "tag_id"))
	private List<PostTags> tags = new ArrayList<>();
	public void addTag(PostTags tag) {
		tags.add(tag);
		tag.getPosts().add(this);
   	}


	public void removeTag(PostTags tag) {
		tags.remove(tag);
		tag.getPosts().remove(this); //? o??
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
