package br.com.alura.forum.dto.output;

import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

public class FieldErrorOutputDto {

	private String field;
	private String message;
	
	public FieldErrorOutputDto(String field, String message) {
		this.field = field;
		this.message = message;
	}
	
	public FieldErrorOutputDto(FieldError fieldError) {
		this.field = fieldError.getField();
		this.message = fieldError.getDefaultMessage();
	}
	
	public FieldErrorOutputDto(ObjectError globalError) {
		this.field = globalError.getObjectName();
		this.message = globalError.getDefaultMessage();
	}

	public String getField() {
		return field;
	}

	public String getMessage() {
		return message;
	}
	
}