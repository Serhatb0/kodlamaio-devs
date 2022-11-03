package com.biricik.devs.business.concretes;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.biricik.devs.business.abstracts.ProgrammingLanguageService;
import com.biricik.devs.business.abstracts.ProgrammingLanguageTechnologieService;
import com.biricik.devs.business.requests.ProgrammingLanguageTechnologieRequests.CreateProgrammingLanguageTechnologieRequest;
import com.biricik.devs.business.requests.ProgrammingLanguageTechnologieRequests.UpdateProgrammingLanguageTechnologieRequest;
import com.biricik.devs.business.responses.ProgrammingLanguageResponses.GetByIdProgrammingLanguagesResponse;
import com.biricik.devs.business.responses.ProgrammingLanguageTechnologieResponses.CreateProgrammingLanguageTechnologieResponse;
import com.biricik.devs.business.responses.ProgrammingLanguageTechnologieResponses.GetAllProgrammingLanguagesTechnologieResponse;
import com.biricik.devs.business.responses.ProgrammingLanguageTechnologieResponses.UpdateProgrammingLanguageTechnologieResponse;
import com.biricik.devs.dao.abstracts.ProgrammingLanguageTechnologieRepository;
import com.biricik.devs.entities.concretes.ProgrammingLanguage;
import com.biricik.devs.entities.concretes.ProgrammingLanguageTechnologie;

@Service
public class ProgrammingLanguageTechnologieManager implements ProgrammingLanguageTechnologieService {

    private final ProgrammingLanguageTechnologieRepository programmingLanguageTechnologieRepository;
    private final ProgrammingLanguageService programmingLanguageService;

    @Autowired
    public ProgrammingLanguageTechnologieManager(
            ProgrammingLanguageTechnologieRepository programmingLanguageTechnologieRepository,
            ProgrammingLanguageService programmingLanguageService) {
        this.programmingLanguageTechnologieRepository = programmingLanguageTechnologieRepository;
        this.programmingLanguageService = programmingLanguageService;
    }

    @Override
    public CreateProgrammingLanguageTechnologieResponse addProgrammingLanguageTechnologie(
            CreateProgrammingLanguageTechnologieRequest createProgrammingLanguageRequest) {

        GetByIdProgrammingLanguagesResponse getByIdProgrammingLanguagesResponse = this.programmingLanguageService
                .getByIdProgrammingLanguage(createProgrammingLanguageRequest.programmingLanguageId());
                
        ProgrammingLanguage programmingLanguage = new ProgrammingLanguage(getByIdProgrammingLanguagesResponse.id(),
                getByIdProgrammingLanguagesResponse.name());

        ProgrammingLanguageTechnologie programmingLanguageTechnologie = this.programmingLanguageTechnologieRepository
                .save(new ProgrammingLanguageTechnologie(createProgrammingLanguageRequest.name(), programmingLanguage));

        return CreateProgrammingLanguageTechnologieResponse.convert(programmingLanguageTechnologie);
    }

    @Override
    public List<GetAllProgrammingLanguagesTechnologieResponse> getAllProgrammingLanguageTechnologies() {
        return programmingLanguageTechnologieRepository.findAll().stream()
                .map(GetAllProgrammingLanguagesTechnologieResponse::convert).collect(Collectors.toList());
    }

    @Override
    public UpdateProgrammingLanguageTechnologieResponse updateProgrammingLanguage(int id,
            UpdateProgrammingLanguageTechnologieRequest updateProgrammingLanguageRequest) {

        GetByIdProgrammingLanguagesResponse getByIdProgrammingLanguagesResponse = this.programmingLanguageService
                .getByIdProgrammingLanguage(updateProgrammingLanguageRequest.programmingLanguageId());
        ProgrammingLanguage programmingLanguage = new ProgrammingLanguage(getByIdProgrammingLanguagesResponse.id(),
                getByIdProgrammingLanguagesResponse.name());

        Optional<ProgrammingLanguageTechnologie> optionalProgrammingLanguageTechnologie = programmingLanguageTechnologieRepository
                .findById(id);

        optionalProgrammingLanguageTechnologie.ifPresent(programmingLanguageTechnologie -> {
            programmingLanguageTechnologie.setId(id);
            programmingLanguageTechnologie.setName(updateProgrammingLanguageRequest.name());
            programmingLanguageTechnologie.setProgrammingLanguage(programmingLanguage);
            programmingLanguageTechnologieRepository.save(programmingLanguageTechnologie);
        });

        return optionalProgrammingLanguageTechnologie.map(UpdateProgrammingLanguageTechnologieResponse::convert)
                .orElse(new UpdateProgrammingLanguageTechnologieResponse());
    }

    @Override
    public void deleteProgrammingLanguage(int id) {
        programmingLanguageTechnologieRepository.deleteById(id);

    }

}
