package com.biricik.devs.business.concretes;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import com.biricik.devs.business.abstracts.ProgrammingLanguageService;
import com.biricik.devs.business.constants.Messages;
import com.biricik.devs.business.requests.ProgrammingLanguageRequests.CreateProgrammingLanguageRequest;
import com.biricik.devs.business.requests.ProgrammingLanguageRequests.UpdateProgrammingLanguageRequest;
import com.biricik.devs.business.responses.ProgrammingLanguageResponses.CreateProgrammingLanguageResponse;
import com.biricik.devs.business.responses.ProgrammingLanguageResponses.GetAllProgrammingLanguagesResponse;
import com.biricik.devs.business.responses.ProgrammingLanguageResponses.GetByIdProgrammingLanguagesResponse;
import com.biricik.devs.business.responses.ProgrammingLanguageResponses.UpdateProgrammingLanguageResponse;
import com.biricik.devs.core.utilities.result.DataResult;
import com.biricik.devs.core.utilities.result.ErrorDataResult;
import com.biricik.devs.core.utilities.result.ErrorResult;
import com.biricik.devs.core.utilities.result.Result;
import com.biricik.devs.core.utilities.result.SuccessDataResult;
import com.biricik.devs.core.utilities.result.SuccessResult;
import com.biricik.devs.dao.abstracts.elasticsearch.ProgrammingLanguageEsRepository;
import com.biricik.devs.dao.abstracts.postgresql.ProgrammingLanguageRepository;
import com.biricik.devs.entities.concretes.ProgrammingLanguage;
import com.biricik.devs.event.ProgrammingLanguageEsCreatedEvent;
import com.biricik.devs.event.ProgrammingLanguageEsDeletedEvent;

@Service
public class ProgrammingLanguageManager implements ProgrammingLanguageService {

    private final ProgrammingLanguageRepository programmingLanguageRepository;
    private final ApplicationEventPublisher applicationEventPublisher;
    private final ProgrammingLanguageEsRepository programmingLanguageEsRepository;

    public ProgrammingLanguageManager(ProgrammingLanguageRepository programmingLanguageRepository,
            ApplicationEventPublisher applicationEventPublisher,
            ProgrammingLanguageEsRepository programmingLanguageEsRepository) {
        this.programmingLanguageRepository = programmingLanguageRepository;
        this.applicationEventPublisher = applicationEventPublisher;
        this.programmingLanguageEsRepository = programmingLanguageEsRepository;
    }

    @Override
    public DataResult<List<GetAllProgrammingLanguagesResponse>> getAllProgrammingLanguage() {
        return new SuccessDataResult<>(programmingLanguageRepository.findAll().stream()
                .map(GetAllProgrammingLanguagesResponse::convert).collect(Collectors.toList()));
    }

    @Override
    public DataResult<List<GetAllProgrammingLanguagesResponse>> findByProgrammingLanguageName(String name) {

        return new SuccessDataResult<>(programmingLanguageEsRepository.findByProgrammingLanguageName(name).stream()
                .map(GetAllProgrammingLanguagesResponse::convert).collect(Collectors.toList()));

    }

    @Override
    public DataResult<List<GetAllProgrammingLanguagesResponse>> findByProgrammingLanguageNameOrProgrammingLanguageTechnologieName(
            String name) {
        return new SuccessDataResult<>(programmingLanguageEsRepository
                .findByProgrammingLanguageNameOrProgrammingLanguageTechnologieName(name).stream()
                .map(GetAllProgrammingLanguagesResponse::convert).collect(Collectors.toList()));
    }

    @Override
    public DataResult<GetByIdProgrammingLanguagesResponse> getByIdProgrammingLanguage(int id) {
        Optional<ProgrammingLanguage> optionalProgrammingLanguage = programmingLanguageRepository
                .findById(id);

        if (optionalProgrammingLanguage.isEmpty()) {
            return new ErrorDataResult<>(Messages.PROGRAMMİNGLANGUAGENOTFOUND);
        }

        return new SuccessDataResult<>(
                optionalProgrammingLanguage.map(GetByIdProgrammingLanguagesResponse::convert).get());
    }

    @Override
    public DataResult<CreateProgrammingLanguageResponse> addProgrammingLanguage(
            CreateProgrammingLanguageRequest createProgrammingLanguageRequest) {

        ProgrammingLanguage program = new ProgrammingLanguage(createProgrammingLanguageRequest.name());

        ProgrammingLanguage programmingLanguage = programmingLanguageRepository
                .save(program);

        applicationEventPublisher.publishEvent(new ProgrammingLanguageEsCreatedEvent(programmingLanguage));

        return new SuccessDataResult<>(CreateProgrammingLanguageResponse.convert(programmingLanguage),
                Messages.PROGRAMMİNGLANGUAGEADD);

    }

    @Override
    public DataResult<UpdateProgrammingLanguageResponse> updateProgrammingLanguage(
            int id, UpdateProgrammingLanguageRequest updateProgrammingLanguageRequest) {

        Optional<ProgrammingLanguage> optionalProgrammingLanguage = programmingLanguageRepository
                .findById(id);
        if (optionalProgrammingLanguage.isEmpty()) {
            return new ErrorDataResult<>(Messages.PROGRAMMİNGLANGUAGENOTFOUND);
        }

        optionalProgrammingLanguage.ifPresent(
                programmingLanguage -> {
                    programmingLanguage.setId(id);
                    programmingLanguage.setName(updateProgrammingLanguageRequest.name());
                    programmingLanguageRepository.save(programmingLanguage);
                });

        applicationEventPublisher
                .publishEvent(new ProgrammingLanguageEsCreatedEvent(optionalProgrammingLanguage.get()));

        return new SuccessDataResult<>(
                optionalProgrammingLanguage.map(UpdateProgrammingLanguageResponse::convert).get(),
                Messages.PROGRAMMİNGLANGUAGEUPDATE);

    }

    @Override
    public Result deleteProgrammingLanguage(int id) {
        Optional<ProgrammingLanguage> programmingLanguage = programmingLanguageRepository.findById(id);
        if (programmingLanguage.isEmpty()) {
            return new ErrorResult(Messages.PROGRAMMİNGLANGUAGENOTFOUND);
        }
        programmingLanguageRepository.delete(programmingLanguage.get());

        applicationEventPublisher.publishEvent(new ProgrammingLanguageEsDeletedEvent(id));

        return new SuccessResult(Messages.PROGRAMMİNGLANGUAGEDELETE);

    }

    @Override
    public ProgrammingLanguage findById(int id) {

        return programmingLanguageRepository.findById(id).get();
    }

}
