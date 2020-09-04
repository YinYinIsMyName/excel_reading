package com.spring.read.file.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tutorial")
public class Tutorial {

	@Id
	@Column(name = "id", nullable = false, unique = true)
	private long id;

	@Column(name = "title")
	private String title;

	@Column(name = "descriptions")
	private String descriptions;

	@Column(name = "published")
	private String published;

	public Tutorial() {
	}

	public Tutorial(int id, String title, String descriptions, String published) {
		super();
		this.id = id;
		this.title = title;
		this.descriptions = descriptions;
		this.published = published;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescriptions() {
		return descriptions;
	}

	public void setDescriptions(String descriptions) {
		this.descriptions = descriptions;
	}

	public String getPublished() {
		return published;
	}

	public void setPublished(String published) {
		this.published = published;
	}

	@Override
	public String toString() {
		return "Tutorial [id=" + id + ", title=" + title + ", descriptions=" + descriptions + ", published=" + published
				+ "]";
	}

}
