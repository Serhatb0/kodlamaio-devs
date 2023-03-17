package com.biricik.devs.business.responses.ProgrammingLanguageTechnologieResponses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateProgrammingLanguageTechnologieResponse {

	private int id; 
	String technologieName;
	private String programmingLanguageName;

	
}
