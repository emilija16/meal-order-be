package com.example.demo.exceptions;

import java.util.NoSuchElementException;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import io.jsonwebtoken.ExpiredJwtException;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(NoSuchElementException.class)
	public ResponseEntity<?> noSuchElementExceptionHandler(NoSuchElementException e) {
		return new ResponseEntity<>(e.getStackTrace(), HttpStatus.FORBIDDEN);
	}

	@ExceptionHandler(NotUniqueException.class)
	public ResponseEntity<?> notUniqueExceptionHandler(NotUniqueException e) {
		return new ResponseEntity<>(e.getMessage(), HttpStatus.FORBIDDEN);
	}

	@ExceptionHandler(ExpiredJwtException.class)
	public ResponseEntity<?> expiredJwtExceptionHandler(ExpiredJwtException e) {
		return new ResponseEntity<>(e.getMessage(), HttpStatus.FORBIDDEN);
	}

	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<?> fileNotFoundExceptionHandler(NotFoundException e) {
		return new ResponseEntity<>(e.getMessage(), HttpStatus.FORBIDDEN);
	}

	@ExceptionHandler(InvalidateShoppingItemException.class)
	public ResponseEntity<?> fileNotFoundExceptionHandler(InvalidateShoppingItemException e) {
		return new ResponseEntity<>(e.getMessage(), HttpStatus.FORBIDDEN);
	}

	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<?> fileNotFoundExceptionHandler(UserNotFoundException e) {
		return new ResponseEntity<>(e.getMessage(), HttpStatus.FORBIDDEN);
	}
	
	@ExceptionHandler(NullException.class)
	public ResponseEntity<?> fileNotFoundExceptionHandler(NullException e) {
		return new ResponseEntity<>(e.getMessage(), HttpStatus.FORBIDDEN);
	}

}
