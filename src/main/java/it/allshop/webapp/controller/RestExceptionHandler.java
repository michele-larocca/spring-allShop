package it.allshop.webapp.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import it.allshop.webapp.exception.BindingException;
import it.allshop.webapp.exception.DuplicateException;
import it.allshop.webapp.exception.ErrorResponse;
import it.allshop.webapp.exception.NotFoundException;

@ControllerAdvice
@RestController
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(NotFoundException.class)
	public final ResponseEntity<ErrorResponse> exceptionNotFoundHandler(Exception ex) {
		ErrorResponse errore = new ErrorResponse();
		errore.setCodice(HttpStatus.NOT_FOUND.value());
		errore.setMessaggio(ex.getMessage());
		return new ResponseEntity<ErrorResponse>(errore, new HttpHeaders(), HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(BindingException.class)
	public final ResponseEntity<ErrorResponse> exceptionBindingHandler(Exception ex) {
		ErrorResponse errore = new ErrorResponse();
		errore.setCodice(HttpStatus.BAD_REQUEST.value());
		errore.setMessaggio(ex.getMessage());
		return new ResponseEntity<ErrorResponse>(errore, new HttpHeaders(), HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(DuplicateException.class)
	public final ResponseEntity<ErrorResponse> exceptionDuplicatedHandler(Exception ex) {
		ErrorResponse errore = new ErrorResponse();
		errore.setCodice(HttpStatus.NOT_ACCEPTABLE.value());
		errore.setMessaggio(ex.getMessage());
		return new ResponseEntity<ErrorResponse>(errore, new HttpHeaders(), HttpStatus.NOT_ACCEPTABLE);
	}
	
}
