package com.formation.javaformation.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
public class Post extends Material {

	//private int id;
	//private String label;
	//private String description;
	@ManyToMany(cascade = {
			CascadeType.PERSIST,
			CascadeType.MERGE
	})
	@JoinTable(name = "post_tag",
	joinColumns = @JoinColumn(name = "post_id"),
	inverseJoinColumns = @JoinColumn(name = "tag_id")

)

private List<PostTags> tags = new ArrayList<>();

public void addTag(PostTags tag) {
	tags.add(tag);
	tag.getPosts().add(this);

}


public void removeTag(PostTags tag) {
	tags.remove(tag);
	tag.getPosts().remove(this); //? o??
}

}
