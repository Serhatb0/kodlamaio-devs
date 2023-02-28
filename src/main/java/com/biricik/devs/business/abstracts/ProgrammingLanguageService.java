package com.biricik.devs.business.abstracts;

import com.biricik.devs.business.requests.PaginatedRequest;
import com.biricik.devs.business.requests.ProgrammingLanguageRequests.CreateProgrammingLanguageRequest;
import com.biricik.devs.business.requests.ProgrammingLanguageRequests.UpdateProgrammingLanguageRequest;
import com.biricik.devs.business.responses.PaginatedGenericResponse;
import com.biricik.devs.business.responses.ProgrammingLanguageResponses.CreateProgrammingLanguageResponse;
import com.biricik.devs.business.responses.ProgrammingLanguageResponses.GetAllProgrammingLanguagesResponse;
import com.biricik.devs.business.responses.ProgrammingLanguageResponses.GetByIdProgrammingLanguagesResponse;
import com.biricik.devs.business.responses.ProgrammingLanguageResponses.UpdateProgrammingLanguageResponse;
import com.biricik.devs.entities.concretes.ProgrammingLanguage;

public interface ProgrammingLanguageService {
	public PaginatedGenericResponse<GetAllProgrammingLanguagesResponse> getAllProgrammingLanguage(
			PaginatedRequest paginatedRequest);

	public GetByIdProgrammingLanguagesResponse getByIdProgrammingLanguage(int id);

	public CreateProgrammingLanguageResponse addProgrammingLanguage(
			CreateProgrammingLanguageRequest createProgrammingLanguageRequest);

	public  UpdateProgrammingLanguageResponse updateProgrammingLanguage(
			UpdateProgrammingLanguageRequest updateProgrammingLanguageRequest);

	public void deleteProgrammingLanguage(int id);

	public ProgrammingLanguage findById(int id);

}
