package br.com.alura.forum.dto.output;

import java.util.ArrayList;
import java.util.List;

public class ValidationErrorsOutputDto {

	private List<FieldErrorOutputDto> fieldErrors = new ArrayList<>();
	
	public void addFieldError(String field, String message) {
		FieldErrorOutputDto fieldError = new FieldErrorOutputDto(field, message);
		fieldErrors.add(fieldError);
	}

	public List<FieldErrorOutputDto> getFieldErrors() {
		return fieldErrors;
	}
	
	public int getNumberOfErrors() {
		return fieldErrors.size();
	}
	
}
