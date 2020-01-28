package br.com.alura.forum.dto;

import java.util.List;

import br.com.alura.forum.model.Category;

public class DashboardDto {
	
	private String categoryName;
	private List<String> subcategories;
	private int allTopics;
	private int lastWeekTopics;
	private int unansweredTopics;
	
	public DashboardDto(Category categoria, int topicos, int lastWeekTopics, int unansweredTopics) {
		this.categoryName = categoria.getName();
		this.subcategories = categoria.getSubcategoryNames();
		this.allTopics = topicos;
		this.lastWeekTopics = lastWeekTopics;
		this.unansweredTopics = unansweredTopics;
	}
	
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public List<String> getSubcategories() {
		return subcategories;
	}
	public void setSubcategories(List<String> subcategories) {
		this.subcategories = subcategories;
	}
	public int getAllTopics() {
		return allTopics;
	}
	public void setAllTopics(int allTopics) {
		this.allTopics = allTopics;
	}
	public int getLastWeekTopics() {
		return lastWeekTopics;
	}
	public void setLastWeekTopics(int lastWeekTopics) {
		this.lastWeekTopics = lastWeekTopics;
	}
	public int getUnansweredTopics() {
		return unansweredTopics;
	}
	public void setUnansweredTopics(int unansweredTopics) {
		this.unansweredTopics = unansweredTopics;
	}
	
	

}
