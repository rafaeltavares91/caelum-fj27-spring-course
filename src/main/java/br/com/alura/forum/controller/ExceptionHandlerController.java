package br.com.alura.forum.controller;

import java.util.stream.Collectors;

import javax.naming.AuthenticationException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import br.com.alura.forum.dto.output.FieldErrorOutputDto;
import br.com.alura.forum.dto.output.MessageCodeOutputDto;
import br.com.alura.forum.exception.ResourceNotFoundException;

@RestControllerAdvice
public class ExceptionHandlerController {
	
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler({ResourceNotFoundException.class})
    public MessageCodeOutputDto handleNotFoundException(Exception exception, WebRequest request) {
		return MessageCodeOutputDto.of("Resource Not Found", HttpStatus.NOT_FOUND.value());
    }
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler({AuthenticationException.class})
    public MessageCodeOutputDto handleAuthenticationException(Exception exception, WebRequest request) {
		return MessageCodeOutputDto.of("User or email invalid", HttpStatus.BAD_REQUEST.value());
    }
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler({MethodArgumentNotValidException.class})
    public MessageCodeOutputDto handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
		return MessageCodeOutputDto.of("Argumentos Inválios", HttpStatus.BAD_REQUEST.value(), ex.getBindingResult().getFieldErrors()
				.stream()
				.map(FieldErrorOutputDto::new)
				.collect(Collectors.toList()));
    }

}
