package com.biricik.devs.business.mappers;

import java.util.stream.Collectors;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.modelmapper.spi.MappingContext;

import com.biricik.devs.business.responses.ProgrammingLanguageResponses.GetByIdProgrammingLanguagesResponse;
import com.biricik.devs.entities.concretes.ProgrammingLanguage;

public class GetByIdProgrammingLanguagesResponseTypeMap implements Converter<ProgrammingLanguage, GetByIdProgrammingLanguagesResponse>{


	private ModelMapper modelMapper;

	
	
	public GetByIdProgrammingLanguagesResponseTypeMap(ModelMapper modelMapper) {
	
		this.modelMapper = modelMapper;
	}

	@Override
	public GetByIdProgrammingLanguagesResponse convert(
			MappingContext<ProgrammingLanguage, GetByIdProgrammingLanguagesResponse> context) {
		GetByIdProgrammingLanguagesResponse response = new GetByIdProgrammingLanguagesResponse();
		response.setId(context.getSource().getId());
		response.setName(context.getSource().getName());
		response.setTechnologieName(context.getSource().getProgrammingLanguageTechnologies()
				.stream().map(item -> item.getTechnologieName()).collect(Collectors.toList()));

		return response;
	}
	
	public TypeMap<ProgrammingLanguage, GetByIdProgrammingLanguagesResponse> getTypeMap() {
		TypeMap<ProgrammingLanguage, GetByIdProgrammingLanguagesResponse> typeMap = this.modelMapper
				.getTypeMap(ProgrammingLanguage.class, GetByIdProgrammingLanguagesResponse.class);
		
		return typeMap;
	}

}
