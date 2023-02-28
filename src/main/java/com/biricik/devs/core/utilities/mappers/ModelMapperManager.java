package com.biricik.devs.core.utilities.mappers;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;

import com.biricik.devs.business.mappers.GetByIdProgrammingLanguagesResponseTypeMap;
import com.biricik.devs.business.responses.ProgrammingLanguageResponses.GetByIdProgrammingLanguagesResponse;
import com.biricik.devs.entities.concretes.ProgrammingLanguage;

@Component
public class ModelMapperManager implements ModelMapperService {

	private ModelMapper modelMapper;

	public ModelMapperManager(ModelMapper modelMapper) {
		this.modelMapper = modelMapper;
	}

	@Override
	public ModelMapper forResponse() {
		this.modelMapper.getConfiguration().setAmbiguityIgnored(true).setMatchingStrategy(MatchingStrategies.LOOSE);
		
		GetByIdProgrammingLanguagesResponseTypeMap typeMap = new GetByIdProgrammingLanguagesResponseTypeMap(modelMapper);
			
		if(typeMap.getTypeMap() == null) {
			this.modelMapper.createTypeMap(ProgrammingLanguage.class, GetByIdProgrammingLanguagesResponse.class).setPostConverter(typeMap);
			
		}
	
		return this.modelMapper;

	}



	@Override
	public ModelMapper forRequest() {
		this.modelMapper.getConfiguration().setAmbiguityIgnored(true).setMatchingStrategy(MatchingStrategies.STANDARD);
		return this.modelMapper;
	}

}
