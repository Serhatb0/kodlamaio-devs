package com.biricik.devs.business.concretes;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.biricik.devs.business.abstracts.ProgrammingLanguageService;
import com.biricik.devs.business.constants.Messages;
import com.biricik.devs.business.requests.PaginatedRequest;
import com.biricik.devs.business.requests.ProgrammingLanguageRequests.CreateProgrammingLanguageRequest;
import com.biricik.devs.business.requests.ProgrammingLanguageRequests.UpdateProgrammingLanguageRequest;
import com.biricik.devs.business.responses.PaginatedGenericResponse;
import com.biricik.devs.business.responses.ProgrammingLanguageResponses.CreateProgrammingLanguageResponse;
import com.biricik.devs.business.responses.ProgrammingLanguageResponses.GetAllProgrammingLanguagesResponse;
import com.biricik.devs.business.responses.ProgrammingLanguageResponses.GetByIdProgrammingLanguagesResponse;
import com.biricik.devs.business.responses.ProgrammingLanguageResponses.UpdateProgrammingLanguageResponse;
import com.biricik.devs.core.utilities.mappers.ModelMapperService;
import com.biricik.devs.core.utilities.result.DataResult;
import com.biricik.devs.core.utilities.result.ErrorResult;
import com.biricik.devs.core.utilities.result.Result;
import com.biricik.devs.core.utilities.result.SuccessDataResult;
import com.biricik.devs.core.utilities.result.SuccessResult;
import com.biricik.devs.dao.abstracts.ProgrammingLanguageRepository;
import com.biricik.devs.entities.concretes.ProgrammingLanguage;

@Service
public class ProgrammingLanguageManager implements ProgrammingLanguageService {

	private final ProgrammingLanguageRepository programmingLanguageRepository;
	private final ModelMapperService modelMapperService;

	public ProgrammingLanguageManager(ProgrammingLanguageRepository programmingLanguageRepository,
			ModelMapperService modelMapperService) {
		this.programmingLanguageRepository = programmingLanguageRepository;
		this.modelMapperService = modelMapperService;

	}

	@Override
	public DataResult<PaginatedGenericResponse<GetAllProgrammingLanguagesResponse>> getAllProgrammingLanguage(
			PaginatedRequest paginatedRequest) {

		Pageable pageable = PageRequest.of(paginatedRequest.getPage(), paginatedRequest.getSize());

		Page<ProgrammingLanguage> pageProgrammingLanguage = programmingLanguageRepository.findAll(pageable);

		List<GetAllProgrammingLanguagesResponse> response = pageProgrammingLanguage.stream()
				.map(programmingLanguage -> modelMapperService.forResponse().map(programmingLanguage,
						GetAllProgrammingLanguagesResponse.class))
				.collect(Collectors.toList());
		;

		return new SuccessDataResult<>(new PaginatedGenericResponse<GetAllProgrammingLanguagesResponse>(response,
				pageProgrammingLanguage.getNumber(), pageProgrammingLanguage.getSize(),
				pageProgrammingLanguage.getTotalElements(), pageProgrammingLanguage.getTotalPages()));
	}

	@Override
	public DataResult<GetByIdProgrammingLanguagesResponse> getByIdProgrammingLanguage(int id) {
		ProgrammingLanguage optionalProgrammingLanguage = programmingLanguageRepository.findById(id).get();

//		if (optionalProgrammingLanguage.isEmpty()) {
//			return new ErrorDataResult<>(Messages.PROGRAMMİNGLANGUAGENOTFOUND);
//		}

		GetByIdProgrammingLanguagesResponse response = modelMapperService.forResponse().map(optionalProgrammingLanguage,
				GetByIdProgrammingLanguagesResponse.class);
		return new SuccessDataResult<>(response);
	}

	@Override
	public DataResult<CreateProgrammingLanguageResponse> addProgrammingLanguage(
			CreateProgrammingLanguageRequest createProgrammingLanguageRequest) {

		ProgrammingLanguage programmingLanguage = programmingLanguageRepository
				.save(modelMapperService.forRequest().map(createProgrammingLanguageRequest, ProgrammingLanguage.class));

		return new SuccessDataResult<>(
				modelMapperService.forResponse().map(programmingLanguage, CreateProgrammingLanguageResponse.class),
				Messages.PROGRAMMİNGLANGUAGEADD);

	}

	@Override
	public DataResult<UpdateProgrammingLanguageResponse> updateProgrammingLanguage(
			UpdateProgrammingLanguageRequest updateProgrammingLanguageRequest) {

		ProgrammingLanguage programmingLanguage = modelMapperService.forRequest().map(updateProgrammingLanguageRequest,
				ProgrammingLanguage.class);
		this.programmingLanguageRepository.save(programmingLanguage);

		return new SuccessDataResult<>(
				modelMapperService.forResponse().map(programmingLanguage, UpdateProgrammingLanguageResponse.class),
				Messages.PROGRAMMİNGLANGUAGEUPDATE);

	}

	@Override
	public Result deleteProgrammingLanguage(int id) {
		Optional<ProgrammingLanguage> programmingLanguage = programmingLanguageRepository.findById(id);
		if (programmingLanguage.isEmpty()) {
			return new ErrorResult(Messages.PROGRAMMİNGLANGUAGENOTFOUND);
		}
		programmingLanguageRepository.delete(programmingLanguage.get());

		return new SuccessResult(Messages.PROGRAMMİNGLANGUAGEDELETE);

	}

	@Override
	public ProgrammingLanguage findById(int id) {

		return programmingLanguageRepository.findById(id).get();
	}

}
