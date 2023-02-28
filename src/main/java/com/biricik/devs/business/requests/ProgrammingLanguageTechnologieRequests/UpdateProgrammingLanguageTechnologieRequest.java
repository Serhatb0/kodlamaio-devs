package com.biricik.devs.business.requests.ProgrammingLanguageTechnologieRequests;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateProgrammingLanguageTechnologieRequest {

	
	private int id;
	@NotBlank()
	@Size(min = 3)
	private String name;

	private int programmingLanguageId;
}
