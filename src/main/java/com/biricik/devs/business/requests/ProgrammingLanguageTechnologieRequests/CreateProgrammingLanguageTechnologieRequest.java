package com.biricik.devs.business.requests.ProgrammingLanguageTechnologieRequests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateProgrammingLanguageTechnologieRequest {
    
	private String name;
	private int programmingLanguageId;
}
