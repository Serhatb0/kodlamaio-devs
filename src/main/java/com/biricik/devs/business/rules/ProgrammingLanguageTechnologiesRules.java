package com.biricik.devs.business.rules;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.biricik.devs.business.constants.Messages;
import com.biricik.devs.core.exception.BusinessExcepiton;
import com.biricik.devs.core.utilities.LanguageSelector;
import com.biricik.devs.dao.abstracts.ProgrammingLanguageTechnologieRepository;
import com.biricik.devs.entities.concretes.ProgrammingLanguageTechnologie;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class ProgrammingLanguageTechnologiesRules {
	
	private final ProgrammingLanguageTechnologieRepository programmingLanguageTechnologieRepository;
	
	public void checkIfTechnologiesOfAProgrammingLanguageBeAtMostFour(int programmingLanguageId) {
		List<ProgrammingLanguageTechnologie> technologies = this.programmingLanguageTechnologieRepository.findByProgrammingLanguageId(programmingLanguageId);

		if(technologies.size() >= 4) {
			throw new BusinessExcepiton("0001",LanguageSelector.selector(Messages.TECHNOLOGIESOFAPROGRAMMINGLANGUAGEBEATMOSTFOUR),HttpStatus.BAD_REQUEST);
		}
	}

}
