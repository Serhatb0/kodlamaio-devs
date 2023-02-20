package com.biricik.devs.business.abstracts;

import com.biricik.devs.business.requests.PaginatedRequest;
import com.biricik.devs.business.requests.ProgrammingLanguageRequests.CreateProgrammingLanguageRequest;
import com.biricik.devs.business.requests.ProgrammingLanguageRequests.UpdateProgrammingLanguageRequest;
import com.biricik.devs.business.responses.PaginatedGenericResponse;
import com.biricik.devs.business.responses.ProgrammingLanguageResponses.CreateProgrammingLanguageResponse;
import com.biricik.devs.business.responses.ProgrammingLanguageResponses.GetAllProgrammingLanguagesResponse;
import com.biricik.devs.business.responses.ProgrammingLanguageResponses.GetByIdProgrammingLanguagesResponse;
import com.biricik.devs.business.responses.ProgrammingLanguageResponses.UpdateProgrammingLanguageResponse;
import com.biricik.devs.core.utilities.result.DataResult;
import com.biricik.devs.core.utilities.result.Result;
import com.biricik.devs.entities.concretes.ProgrammingLanguage;

public interface ProgrammingLanguageService {
	public DataResult<PaginatedGenericResponse<GetAllProgrammingLanguagesResponse>> getAllProgrammingLanguage(
			PaginatedRequest paginatedRequest);

	public DataResult<GetByIdProgrammingLanguagesResponse> getByIdProgrammingLanguage(int id);

	public DataResult<CreateProgrammingLanguageResponse> addProgrammingLanguage(
			CreateProgrammingLanguageRequest createProgrammingLanguageRequest);

	public DataResult<UpdateProgrammingLanguageResponse> updateProgrammingLanguage(
			UpdateProgrammingLanguageRequest updateProgrammingLanguageRequest);

	public Result deleteProgrammingLanguage(int id);

	public ProgrammingLanguage findById(int id);

}
