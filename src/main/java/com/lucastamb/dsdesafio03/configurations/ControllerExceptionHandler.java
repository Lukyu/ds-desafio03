package com.lucastamb.dsdesafio03.configurations;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.lucastamb.dsdesafio03.exceptions.ClientNotFoundExcpetion;
import com.lucastamb.dsdesafio03.exceptions.dto.CustomErrorDTO;
import com.lucastamb.dsdesafio03.exceptions.dto.ValidationErrorDTO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;

@ControllerAdvice
public class ControllerExceptionHandler {
	
	/*@ExceptionHandler(CustomException.class)
	public ResponseEntity<CustomError> customName(CustomException e, HttpServletRequest request) {
		HttpStatus status = HttpStatus.NOT_FOUND;
		CustomError err = new ...
		return ResponseEntity.status(status).body(err);
	}*/
	
	@ExceptionHandler(ClientNotFoundExcpetion.class)
    public ResponseEntity<CustomErrorDTO> resourceNotFound(ClientNotFoundExcpetion e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        CustomErrorDTO err = new CustomErrorDTO(Instant.now(), status.value(), e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<CustomErrorDTO> methodArgumentNotValidation(ConstraintViolationException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;
        ValidationErrorDTO err = new ValidationErrorDTO(Instant.now(), status.value(), "Dados inválidos", request.getRequestURI());
        
        for (ConstraintViolation<?> f : e.getConstraintViolations()) {
            err.addError(f.getPropertyPath().toString(), f.getMessage());
        }
        return ResponseEntity.status(status).body(err);
    }
    
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<CustomErrorDTO> methodArgumentNotValidation(MethodArgumentNotValidException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;
        ValidationErrorDTO err = new ValidationErrorDTO(Instant.now(), status.value(), "Dados inválidos", request.getRequestURI());
        for (FieldError f : e.getBindingResult().getFieldErrors()) {
            err.addError(f.getField(), f.getDefaultMessage());
        }
        return ResponseEntity.status(status).body(err);
    }
}
