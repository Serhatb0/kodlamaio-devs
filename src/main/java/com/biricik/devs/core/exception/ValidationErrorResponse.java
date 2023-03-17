package com.biricik.devs.core.exception;

import java.util.Map;

import lombok.Data;

@Data
public class ValidationErrorResponse extends BaseError {

	
	Map<String, String> validationErrors;
}
