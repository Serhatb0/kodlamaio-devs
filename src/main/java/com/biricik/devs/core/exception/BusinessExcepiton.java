package com.biricik.devs.core.exception;

import org.springframework.http.HttpStatus;

import lombok.Data;

@Data
public class BusinessExcepiton  extends RuntimeException{

    private final String errorCode;
    private final String message;
    private final HttpStatus httpStatus;
	public BusinessExcepiton(String errorCode, String message, HttpStatus httpStatus) {
		this.errorCode = errorCode;
		this.message = message;
		this.httpStatus = httpStatus;
	}
	
	
}
