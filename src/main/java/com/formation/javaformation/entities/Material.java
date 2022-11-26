package com.formation.javaformation.entities;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.UUID;


@MappedSuperclass
public abstract class Material implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", updatable = false, nullable = false)
	private long id;
	@Column(name="is_delete")
	private boolean deleted;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public boolean isDeleted() {
		return deleted;
		
	}
	public void setsDeleted(boolean deleted) {
		this.deleted = deleted ;
		
	}

}
