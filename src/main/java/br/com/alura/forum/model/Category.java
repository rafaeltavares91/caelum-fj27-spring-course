package br.com.alura.forum.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Category {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String name;

	@ManyToOne
	private Category category;
	
	@OneToMany(mappedBy = "category")
	private List<Category> subcategories = new ArrayList<>();
	
	/**
	 * @deprecated
	 */
	public Category() {}
	
	public Category(String name) {
		this.name = name;
	}
	
	public Category(String name, Category category) {
		this(name);
		this.category = category; 
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}
	
	public Optional<Category> getCategory() {
		return Optional.ofNullable(this.category);
	}
	
	public List<String> getSubcategoryNames() {
		return this.subcategories.stream()
				.map(Category::getName)
				.collect(Collectors.toList());
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Category other = (Category) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
}
