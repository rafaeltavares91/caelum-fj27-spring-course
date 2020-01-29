package br.com.alura.forum.dto.input;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class NewTopicInputDto {

	@NotBlank
	@Size(min=10)
	private String shortDescription;
	
	@NotBlank
	@Size(min=10)
	private String content;
	
	@NotBlank
	private String courseName;
	
	public String getShortDescription() {
		return shortDescription;
	}
	
	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}
	
	public String getContent() {
		return content;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
	
	public String getCourseName() {
		return courseName;
	}
	
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	
}
