package com.biricik.devs.core.exception;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class BaseError {

	private String message;
	private Integer statusCode;
	private String statusName;
	private String path;
	private String method;
	private LocalDateTime timestamp;
	
	

}
