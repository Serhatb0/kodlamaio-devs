package com.biricik.devs.business.abstracts;

import java.util.List;

import com.biricik.devs.business.requests.ProgrammingLanguageTechnologieRequests.CreateProgrammingLanguageTechnologieRequest;
import com.biricik.devs.business.requests.ProgrammingLanguageTechnologieRequests.UpdateProgrammingLanguageTechnologieRequest;
import com.biricik.devs.business.responses.ProgrammingLanguageTechnologieResponses.CreateProgrammingLanguageTechnologieResponse;
import com.biricik.devs.business.responses.ProgrammingLanguageTechnologieResponses.GetAllProgrammingLanguagesTechnologieResponse;
import com.biricik.devs.business.responses.ProgrammingLanguageTechnologieResponses.UpdateProgrammingLanguageTechnologieResponse;
import com.biricik.devs.core.utilities.result.DataResult;
import com.biricik.devs.core.utilities.result.Result;

public interface ProgrammingLanguageTechnologieService {

    public DataResult<CreateProgrammingLanguageTechnologieResponse> addProgrammingLanguageTechnologie(
            CreateProgrammingLanguageTechnologieRequest createProgrammingLanguageRequest);

    public DataResult<List<GetAllProgrammingLanguagesTechnologieResponse>> getAllProgrammingLanguageTechnologies();

    public DataResult<UpdateProgrammingLanguageTechnologieResponse> updateProgrammingLanguageTechnologies(
            UpdateProgrammingLanguageTechnologieRequest updateProgrammingLanguageRequest);

    public Result deleteProgrammingLanguageTechnologie(int id);

}
