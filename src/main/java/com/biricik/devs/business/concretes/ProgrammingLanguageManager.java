package com.biricik.devs.business.concretes;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.biricik.devs.business.abstracts.ProgrammingLanguageService;
import com.biricik.devs.business.requests.ProgrammingLanguageRequests.CreateProgrammingLanguageRequest;
import com.biricik.devs.business.requests.ProgrammingLanguageRequests.UpdateProgrammingLanguageRequest;
import com.biricik.devs.business.responses.ProgrammingLanguageResponses.CreateProgrammingLanguageResponse;
import com.biricik.devs.business.responses.ProgrammingLanguageResponses.GetAllProgrammingLanguagesResponse;
import com.biricik.devs.business.responses.ProgrammingLanguageResponses.GetByIdProgrammingLanguagesResponse;
import com.biricik.devs.business.responses.ProgrammingLanguageResponses.UpdateProgrammingLanguageResponse;
import com.biricik.devs.dao.abstracts.ProgrammingLanguageRepository;
import com.biricik.devs.entities.concretes.ProgrammingLanguage;

@Service
public class ProgrammingLanguageManager implements ProgrammingLanguageService {

    private final ProgrammingLanguageRepository programmingLanguageRepository;

    @Autowired
    public ProgrammingLanguageManager(ProgrammingLanguageRepository programmingLanguageRepository) {
        this.programmingLanguageRepository = programmingLanguageRepository;
    }

    @Override
    public List<GetAllProgrammingLanguagesResponse> getAllProgrammingLanguage() {
        return programmingLanguageRepository.findAll().stream()
                .map(GetAllProgrammingLanguagesResponse::convert).collect(Collectors.toList());
    }

    @Override
    public GetByIdProgrammingLanguagesResponse getByIdProgrammingLanguage(int id) {
        Optional<ProgrammingLanguage> optionalProgrammingLanguage = programmingLanguageRepository
                .findById(id);
        return optionalProgrammingLanguage.map(GetByIdProgrammingLanguagesResponse::convert)
                .orElse(new GetByIdProgrammingLanguagesResponse());
    }

    @Override
    public CreateProgrammingLanguageResponse addProgrammingLanguage(
            CreateProgrammingLanguageRequest createProgrammingLanguageRequest) {

        ProgrammingLanguage programmingLanguage = programmingLanguageRepository
                .save(new ProgrammingLanguage(createProgrammingLanguageRequest.name()));
                
        return CreateProgrammingLanguageResponse.convert(programmingLanguage);
    }

    @Override
    public UpdateProgrammingLanguageResponse updateProgrammingLanguage(
            int id, UpdateProgrammingLanguageRequest updateProgrammingLanguageRequest) {
        Optional<ProgrammingLanguage> optionalProgrammingLanguage = programmingLanguageRepository
                .findById(id);

        optionalProgrammingLanguage.ifPresent(
                programmingLanguage -> {
                    programmingLanguage.setId(id);
                    programmingLanguage.setName(updateProgrammingLanguageRequest.name());
                    programmingLanguageRepository.save(programmingLanguage);
                });

        return optionalProgrammingLanguage.map(UpdateProgrammingLanguageResponse::convert)
                .orElse(new UpdateProgrammingLanguageResponse());

    }

    @Override
    public void deleteProgrammingLanguage(int id) {
        programmingLanguageRepository.deleteById(id);
    }

}
