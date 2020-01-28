package br.com.alura.forum.model;

import java.util.Optional;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Course {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	
	@ManyToOne
	private Category subcategory;
	
	/**
	 * @deprecated
	 */
	public Course() {}

	public Course(String name, Category subcategory) {
		super();
		this.name = name;
		this.subcategory = subcategory;
	}

	public String getName() {
		return name;
	}

	public Category getSubcategory() {
		return subcategory;
	}

	public String getSubcategoryName() {
		return subcategory.getName();
	}


	public String getCategoryName() {
		Optional<Category> possibleCategory = this.subcategory.getCategory();
		
		return possibleCategory
				.orElseThrow(() -> new IllegalStateException("Esta já é uma categoria mãe"))
				.getName(); 
	}
}
