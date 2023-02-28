package com.biricik.devs.business.concretes;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import com.biricik.devs.business.abstracts.ProgrammingLanguageService;
import com.biricik.devs.business.abstracts.ProgrammingLanguageTechnologieService;
import com.biricik.devs.business.requests.ProgrammingLanguageTechnologieRequests.CreateProgrammingLanguageTechnologieRequest;
import com.biricik.devs.business.requests.ProgrammingLanguageTechnologieRequests.UpdateProgrammingLanguageTechnologieRequest;
import com.biricik.devs.business.responses.ProgrammingLanguageTechnologieResponses.CreateProgrammingLanguageTechnologieResponse;
import com.biricik.devs.business.responses.ProgrammingLanguageTechnologieResponses.GetAllProgrammingLanguagesTechnologieResponse;
import com.biricik.devs.business.responses.ProgrammingLanguageTechnologieResponses.UpdateProgrammingLanguageTechnologieResponse;
import com.biricik.devs.business.rules.ProgrammingLanguageTechnologiesRules;
import com.biricik.devs.core.utilities.mappers.ModelMapperService;
import com.biricik.devs.dao.abstracts.ProgrammingLanguageTechnologieRepository;
import com.biricik.devs.entities.concretes.ProgrammingLanguage;
import com.biricik.devs.entities.concretes.ProgrammingLanguageTechnologie;

@Service
public class ProgrammingLanguageTechnologieManager implements ProgrammingLanguageTechnologieService {

	private final ProgrammingLanguageTechnologieRepository programmingLanguageTechnologieRepository;
	private final ProgrammingLanguageService programmingLanguageService;
	private final ModelMapperService modelMapperService;
	private final ProgrammingLanguageTechnologiesRules programmingLanguageTechnologiesRules;

	@Autowired
	public ProgrammingLanguageTechnologieManager(
			ProgrammingLanguageTechnologieRepository programmingLanguageTechnologieRepository,
			ProgrammingLanguageService programmingLanguageService, ApplicationEventPublisher applicationEventPublisher,
			ModelMapperService modelMapperService, ProgrammingLanguageTechnologiesRules programmingLanguageTechnologiesRules) {
		this.programmingLanguageTechnologieRepository = programmingLanguageTechnologieRepository;
		this.programmingLanguageService = programmingLanguageService;
		this.modelMapperService = modelMapperService;
		this.programmingLanguageTechnologiesRules = programmingLanguageTechnologiesRules;

	}

	@Override
	public CreateProgrammingLanguageTechnologieResponse addProgrammingLanguageTechnologie(
			CreateProgrammingLanguageTechnologieRequest createProgrammingLanguageRequest) {

		programmingLanguageTechnologiesRules.checkIfTechnologiesOfAProgrammingLanguageBeAtMostFour(createProgrammingLanguageRequest.getProgrammingLanguageId());
		
		ProgrammingLanguageTechnologie technologie = modelMapperService.forRequest()
				.map(createProgrammingLanguageRequest, ProgrammingLanguageTechnologie.class);

		ProgrammingLanguage programmingLanguage = programmingLanguageService
				.findById(createProgrammingLanguageRequest.getProgrammingLanguageId());
		technologie.setProgrammingLanguage(programmingLanguage);
		technologie.setId(0);
		ProgrammingLanguageTechnologie programmingLanguageTechnologie = this.programmingLanguageTechnologieRepository
				.save(technologie);

		return modelMapperService.forResponse().map(programmingLanguageTechnologie,
				CreateProgrammingLanguageTechnologieResponse.class);

	}

	@Override
	public List<GetAllProgrammingLanguagesTechnologieResponse> getAllProgrammingLanguageTechnologies() {
		List<GetAllProgrammingLanguagesTechnologieResponse> response = programmingLanguageTechnologieRepository
				.findAll().stream().map(technologie -> modelMapperService.forResponse().map(technologie,
						GetAllProgrammingLanguagesTechnologieResponse.class))
				.collect(Collectors.toList());

		return response;
	}

	@Override
	public UpdateProgrammingLanguageTechnologieResponse updateProgrammingLanguageTechnologies(
			UpdateProgrammingLanguageTechnologieRequest updateProgrammingLanguageRequest) {

		ProgrammingLanguageTechnologie programmingLanguageTechnologie = modelMapperService.forRequest()
				.map(updateProgrammingLanguageRequest, ProgrammingLanguageTechnologie.class);
		this.programmingLanguageTechnologieRepository.save(programmingLanguageTechnologie);

		return modelMapperService.forResponse().map(programmingLanguageTechnologie,
				UpdateProgrammingLanguageTechnologieResponse.class);

	}

	@Override
	public void deleteProgrammingLanguageTechnologie(int id) {
		Optional<ProgrammingLanguageTechnologie> programmingLanguageTechnologie = programmingLanguageTechnologieRepository
				.findById(id);

		programmingLanguageTechnologieRepository.delete(programmingLanguageTechnologie.get());

		int programmingLanguageId = programmingLanguageTechnologie.get().getProgrammingLanguage().getId();
		ProgrammingLanguage programmingLanguage = programmingLanguageService.findById(programmingLanguageId);
		programmingLanguage.setProgrammingLanguageTechnologies(
				programmingLanguageTechnologieRepository.findByProgrammingLanguageId(programmingLanguageId));

	}

}
