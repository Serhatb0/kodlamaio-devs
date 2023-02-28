package com.biricik.devs.business.requests.ProgrammingLanguageRequests;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor	
@NoArgsConstructor
public class UpdateProgrammingLanguageRequest {
	

	private int id;
	@NotBlank()
	@Size(min = 3)
	private String name;
}
