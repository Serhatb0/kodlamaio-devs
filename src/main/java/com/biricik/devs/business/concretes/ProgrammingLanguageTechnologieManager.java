package com.biricik.devs.business.concretes;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import com.biricik.devs.business.abstracts.ProgrammingLanguageService;
import com.biricik.devs.business.abstracts.ProgrammingLanguageTechnologieService;
import com.biricik.devs.business.constants.Messages;
import com.biricik.devs.business.requests.ProgrammingLanguageTechnologieRequests.CreateProgrammingLanguageTechnologieRequest;
import com.biricik.devs.business.requests.ProgrammingLanguageTechnologieRequests.UpdateProgrammingLanguageTechnologieRequest;
import com.biricik.devs.business.responses.ProgrammingLanguageTechnologieResponses.CreateProgrammingLanguageTechnologieResponse;
import com.biricik.devs.business.responses.ProgrammingLanguageTechnologieResponses.GetAllProgrammingLanguagesTechnologieResponse;
import com.biricik.devs.business.responses.ProgrammingLanguageTechnologieResponses.UpdateProgrammingLanguageTechnologieResponse;
import com.biricik.devs.core.utilities.mappers.ModelMapperService;
import com.biricik.devs.core.utilities.result.DataResult;
import com.biricik.devs.core.utilities.result.ErrorResult;
import com.biricik.devs.core.utilities.result.Result;
import com.biricik.devs.core.utilities.result.SuccessDataResult;
import com.biricik.devs.core.utilities.result.SuccessResult;
import com.biricik.devs.dao.abstracts.ProgrammingLanguageTechnologieRepository;
import com.biricik.devs.entities.concretes.ProgrammingLanguage;
import com.biricik.devs.entities.concretes.ProgrammingLanguageTechnologie;

@Service
public class ProgrammingLanguageTechnologieManager implements ProgrammingLanguageTechnologieService {

	private final ProgrammingLanguageTechnologieRepository programmingLanguageTechnologieRepository;
	private final ProgrammingLanguageService programmingLanguageService;
	private final ModelMapperService modelMapperService;

	@Autowired
	public ProgrammingLanguageTechnologieManager(
			ProgrammingLanguageTechnologieRepository programmingLanguageTechnologieRepository,
			ProgrammingLanguageService programmingLanguageService, ApplicationEventPublisher applicationEventPublisher,
			ModelMapperService modelMapperService) {
		this.programmingLanguageTechnologieRepository = programmingLanguageTechnologieRepository;
		this.programmingLanguageService = programmingLanguageService;
		this.modelMapperService = modelMapperService;

	}

	@Override
	public DataResult<CreateProgrammingLanguageTechnologieResponse> addProgrammingLanguageTechnologie(
			CreateProgrammingLanguageTechnologieRequest createProgrammingLanguageRequest) {

		ProgrammingLanguageTechnologie technologie = modelMapperService.forRequest()
				.map(createProgrammingLanguageRequest, ProgrammingLanguageTechnologie.class);
	
		ProgrammingLanguage programmingLanguage = programmingLanguageService.findById(createProgrammingLanguageRequest.getProgrammingLanguageId());
		technologie.setProgrammingLanguage(programmingLanguage);	
		technologie.setId(0);
		ProgrammingLanguageTechnologie programmingLanguageTechnologie = this.programmingLanguageTechnologieRepository
				.save(technologie);
//		System.out.println(programmingLanguageTechnologie.getProgrammingLanguage());
		return new SuccessDataResult<>(
				modelMapperService.forResponse().map(programmingLanguageTechnologie,
						CreateProgrammingLanguageTechnologieResponse.class) ,
				Messages.PROGRAMMİNGLANGUAGETECHNOLOGIEADD);

	}

	@Override
	public DataResult<List<GetAllProgrammingLanguagesTechnologieResponse>> getAllProgrammingLanguageTechnologies() {
		List<GetAllProgrammingLanguagesTechnologieResponse> response = programmingLanguageTechnologieRepository
				.findAll().stream().map(technologie -> modelMapperService.forResponse().map(technologie,
						GetAllProgrammingLanguagesTechnologieResponse.class))
				.collect(Collectors.toList());

		return new SuccessDataResult<>(response);
	}

	@Override
	public DataResult<UpdateProgrammingLanguageTechnologieResponse> updateProgrammingLanguageTechnologies(
			UpdateProgrammingLanguageTechnologieRequest updateProgrammingLanguageRequest) {

		ProgrammingLanguageTechnologie programmingLanguageTechnologie = modelMapperService.forRequest()
				.map(updateProgrammingLanguageRequest, ProgrammingLanguageTechnologie.class);
		this.programmingLanguageTechnologieRepository.save(programmingLanguageTechnologie);

		return new SuccessDataResult<>(modelMapperService.forResponse().map(programmingLanguageTechnologie,
				UpdateProgrammingLanguageTechnologieResponse.class));

	}

	@Override
	public Result deleteProgrammingLanguageTechnologie(int id) {
		Optional<ProgrammingLanguageTechnologie> programmingLanguageTechnologie = programmingLanguageTechnologieRepository
				.findById(id);

		if (programmingLanguageTechnologie.isEmpty()) {
			return new ErrorResult(Messages.PROGRAMMİNGLANGUAGETECHNOLOGIENOTFOUND);
		}
		programmingLanguageTechnologieRepository.delete(programmingLanguageTechnologie.get());

		int programmingLanguageId = programmingLanguageTechnologie.get().getProgrammingLanguage().getId();
		ProgrammingLanguage programmingLanguage = programmingLanguageService.findById(programmingLanguageId);
		programmingLanguage.setProgrammingLanguageTechnologies(
				programmingLanguageTechnologieRepository.findByProgrammingLanguageId(programmingLanguageId));

		return new SuccessResult(Messages.PROGRAMMİNGLANGUAGETECHNOLOGIEDELETE);

	}

}
