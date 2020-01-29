package br.com.alura.forum.dto.output;

import java.util.List;

public class MessageCodeOutputDto {
	
	private String message;
	private int code;
	private List<FieldErrorOutputDto> fieldErrors;
	
	public static MessageCodeOutputDto of(String message, int code) {
		return of(message, code, null);
	}
	
	public static MessageCodeOutputDto of(String message, int code, List<FieldErrorOutputDto> fieldErrors) {
		return new MessageCodeOutputDto(message, code, fieldErrors);
	}
	
	private MessageCodeOutputDto(String message, int code, List<FieldErrorOutputDto> fieldErrors) {
		this.message = message;
		this.code = code;
		this.fieldErrors = fieldErrors;
	}
	
	public String getMessage() {
		return message;
	}
	
	public int getCode() {
		return code;
	}

	public List<FieldErrorOutputDto> getFieldErrors() {
		return fieldErrors;
	}

	public void setFieldErrors(List<FieldErrorOutputDto> fieldErrors) {
		this.fieldErrors = fieldErrors;
	}

}
