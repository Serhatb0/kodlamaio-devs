package com.biricik.devs.business.responses.ProgrammingLanguageResponses;



import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class GetByIdProgrammingLanguagesResponse {

	private int id;
	private String name;
	private List<String> technologieName;
	public void setId(int id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setTechnologieName(List<String> technologieName) {
		this.technologieName = technologieName;
	}
	
	

}
