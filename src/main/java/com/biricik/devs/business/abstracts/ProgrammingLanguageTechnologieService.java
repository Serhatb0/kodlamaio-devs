package com.biricik.devs.business.abstracts;

import java.util.List;

import com.biricik.devs.business.requests.ProgrammingLanguageTechnologieRequests.CreateProgrammingLanguageTechnologieRequest;
import com.biricik.devs.business.requests.ProgrammingLanguageTechnologieRequests.UpdateProgrammingLanguageTechnologieRequest;
import com.biricik.devs.business.responses.ProgrammingLanguageTechnologieResponses.CreateProgrammingLanguageTechnologieResponse;
import com.biricik.devs.business.responses.ProgrammingLanguageTechnologieResponses.GetAllProgrammingLanguagesTechnologieResponse;
import com.biricik.devs.business.responses.ProgrammingLanguageTechnologieResponses.UpdateProgrammingLanguageTechnologieResponse;

public interface ProgrammingLanguageTechnologieService {

    public CreateProgrammingLanguageTechnologieResponse addProgrammingLanguageTechnologie(
            CreateProgrammingLanguageTechnologieRequest createProgrammingLanguageRequest);

    public List<GetAllProgrammingLanguagesTechnologieResponse> getAllProgrammingLanguageTechnologies();

    public UpdateProgrammingLanguageTechnologieResponse updateProgrammingLanguageTechnologies(
            UpdateProgrammingLanguageTechnologieRequest updateProgrammingLanguageRequest);

    public void deleteProgrammingLanguageTechnologie(int id);

}
