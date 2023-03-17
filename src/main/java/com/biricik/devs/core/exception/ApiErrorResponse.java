package com.biricik.devs.core.exception;

import lombok.Data;

@Data
public class ApiErrorResponse  extends BaseError{

	private String guid;
	private String errorCode;
	
}
