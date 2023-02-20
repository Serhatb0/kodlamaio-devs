package com.biricik.devs.business.requests.ProgrammingLanguageTechnologieRequests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateProgrammingLanguageTechnologieRequest {
	private int id;
	private String name ;
	private int programmingLanguageId;
}
