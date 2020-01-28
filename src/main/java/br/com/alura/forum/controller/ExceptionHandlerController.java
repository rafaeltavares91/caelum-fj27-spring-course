package br.com.alura.forum.controller;

import javax.naming.AuthenticationException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import br.com.alura.forum.exception.ResourceNotFoundException;

@ControllerAdvice
public class ExceptionHandlerController {
	
	@ExceptionHandler({ResourceNotFoundException.class})
    public ResponseEntity<Object> handleNotFoundException(Exception exception, WebRequest request){
        return new ResponseEntity<Object>("Resource Not Found", new HttpHeaders(), HttpStatus.NOT_FOUND);
    }
	
	@ExceptionHandler({AuthenticationException.class})
    public ResponseEntity<Object> handleAuthenticationException(Exception exception, WebRequest request){
        return new ResponseEntity<Object>("User or email invalid", new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }
	

}
