package com.biricik.devs.business.abstracts;

import java.util.List;

import com.biricik.devs.business.requests.ProgrammingLanguageRequests.CreateProgrammingLanguageRequest;
import com.biricik.devs.business.requests.ProgrammingLanguageRequests.UpdateProgrammingLanguageRequest;
import com.biricik.devs.business.responses.ProgrammingLanguageResponses.CreateProgrammingLanguageResponse;
import com.biricik.devs.business.responses.ProgrammingLanguageResponses.GetAllProgrammingLanguagesResponse;
import com.biricik.devs.business.responses.ProgrammingLanguageResponses.GetByIdProgrammingLanguagesResponse;
import com.biricik.devs.business.responses.ProgrammingLanguageResponses.UpdateProgrammingLanguageResponse;
import com.biricik.devs.core.utilities.result.DataResult;
import com.biricik.devs.core.utilities.result.Result;

public interface ProgrammingLanguageService {
    public DataResult<List<GetAllProgrammingLanguagesResponse>> getAllProgrammingLanguage();

    public DataResult<GetByIdProgrammingLanguagesResponse> getByIdProgrammingLanguage(int id);

    public DataResult<CreateProgrammingLanguageResponse> addProgrammingLanguage(CreateProgrammingLanguageRequest createProgrammingLanguageRequest);

    public DataResult<UpdateProgrammingLanguageResponse> updateProgrammingLanguage(int id,UpdateProgrammingLanguageRequest updateProgrammingLanguageRequest);

    public Result deleteProgrammingLanguage(int id);

}
