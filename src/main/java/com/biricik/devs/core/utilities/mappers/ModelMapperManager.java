package com.biricik.devs.core.utilities.mappers;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;

import com.biricik.devs.business.responses.ProgrammingLanguageTechnologieResponses.CreateProgrammingLanguageTechnologieResponse;
import com.biricik.devs.entities.concretes.ProgrammingLanguageTechnologie;

@Component
public class ModelMapperManager implements ModelMapperService {

	private ModelMapper modelMapper;

	public ModelMapperManager(ModelMapper modelMapper) {
		this.modelMapper = modelMapper;
	}

	@Override
	public ModelMapper forResponse() {
		this.modelMapper.getConfiguration().setAmbiguityIgnored(true).setMatchingStrategy(MatchingStrategies.LOOSE);
//		this.modelMapper.typeMap(ProgrammingLanguageTechnologie.class, CreateProgrammingLanguageTechnologieResponse.class).addMappings(mapper ->{
//			mapper.map(src -> src.getProgrammingLanguage().getName(),CreateProgrammingLanguageTechnologieResponse::setProgrammingLanguageName);
//		});
//		
		return this.modelMapper;
	}

	@Override
	public ModelMapper forRequest() {
		this.modelMapper.getConfiguration().setAmbiguityIgnored(true).setMatchingStrategy(MatchingStrategies.STANDARD);
		return this.modelMapper;
	}

}
