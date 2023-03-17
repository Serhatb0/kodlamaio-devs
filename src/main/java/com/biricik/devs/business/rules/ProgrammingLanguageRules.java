package com.biricik.devs.business.rules;

import org.springframework.stereotype.Component;

import com.biricik.devs.dao.abstracts.ProgrammingLanguageRepository;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class ProgrammingLanguageRules {
	
	private final ProgrammingLanguageRepository programmingLanguageRepository;
	


}
