package br.com.alura.forum.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.alura.forum.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

	List<Category> findByCategoryIsNull();
	
}