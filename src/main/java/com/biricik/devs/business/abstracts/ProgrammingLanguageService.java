package com.biricik.devs.business.abstracts;

import java.util.List;

import com.biricik.devs.business.requests.ProgrammingLanguageRequests.CreateProgrammingLanguageRequest;
import com.biricik.devs.business.requests.ProgrammingLanguageRequests.UpdateProgrammingLanguageRequest;
import com.biricik.devs.business.responses.ProgrammingLanguageResponses.CreateProgrammingLanguageResponse;
import com.biricik.devs.business.responses.ProgrammingLanguageResponses.GetAllProgrammingLanguagesResponse;
import com.biricik.devs.business.responses.ProgrammingLanguageResponses.GetByIdProgrammingLanguagesResponse;
import com.biricik.devs.business.responses.ProgrammingLanguageResponses.UpdateProgrammingLanguageResponse;

public interface ProgrammingLanguageService {
    public List<GetAllProgrammingLanguagesResponse> getAllProgrammingLanguage();

    public GetByIdProgrammingLanguagesResponse getByIdProgrammingLanguage(int id);

    public CreateProgrammingLanguageResponse addProgrammingLanguage(CreateProgrammingLanguageRequest createProgrammingLanguageRequest);

    public UpdateProgrammingLanguageResponse updateProgrammingLanguage(int id,UpdateProgrammingLanguageRequest updateProgrammingLanguageRequest);

    public void deleteProgrammingLanguage(int id);

}
