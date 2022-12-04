package com.biricik.devs.business.concretes;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import com.biricik.devs.business.abstracts.ProgrammingLanguageService;
import com.biricik.devs.business.abstracts.ProgrammingLanguageTechnologieService;
import com.biricik.devs.business.constants.Messages;
import com.biricik.devs.business.requests.ProgrammingLanguageTechnologieRequests.CreateProgrammingLanguageTechnologieRequest;
import com.biricik.devs.business.requests.ProgrammingLanguageTechnologieRequests.UpdateProgrammingLanguageTechnologieRequest;
import com.biricik.devs.business.responses.ProgrammingLanguageResponses.GetByIdProgrammingLanguagesResponse;
import com.biricik.devs.business.responses.ProgrammingLanguageTechnologieResponses.CreateProgrammingLanguageTechnologieResponse;
import com.biricik.devs.business.responses.ProgrammingLanguageTechnologieResponses.GetAllProgrammingLanguagesTechnologieResponse;
import com.biricik.devs.business.responses.ProgrammingLanguageTechnologieResponses.UpdateProgrammingLanguageTechnologieResponse;
import com.biricik.devs.core.utilities.result.DataResult;
import com.biricik.devs.core.utilities.result.ErrorDataResult;
import com.biricik.devs.core.utilities.result.ErrorResult;
import com.biricik.devs.core.utilities.result.Result;
import com.biricik.devs.core.utilities.result.SuccessDataResult;
import com.biricik.devs.core.utilities.result.SuccessResult;
import com.biricik.devs.dao.abstracts.postgresql.ProgrammingLanguageTechnologieRepository;
import com.biricik.devs.entities.concretes.ProgrammingLanguage;
import com.biricik.devs.entities.concretes.ProgrammingLanguageTechnologie;
import com.biricik.devs.event.ProgrammingLanguageEsCreatedEvent;

@Service
public class ProgrammingLanguageTechnologieManager implements ProgrammingLanguageTechnologieService {

        private final ProgrammingLanguageTechnologieRepository programmingLanguageTechnologieRepository;
        private final ProgrammingLanguageService programmingLanguageService;
        private final ApplicationEventPublisher applicationEventPublisher;

        @Autowired
        public ProgrammingLanguageTechnologieManager(
                        ProgrammingLanguageTechnologieRepository programmingLanguageTechnologieRepository,
                        ProgrammingLanguageService programmingLanguageService,
                        ApplicationEventPublisher applicationEventPublisher) {
                this.programmingLanguageTechnologieRepository = programmingLanguageTechnologieRepository;
                this.programmingLanguageService = programmingLanguageService;
                this.applicationEventPublisher = applicationEventPublisher;
        }

        @Override
        public DataResult<CreateProgrammingLanguageTechnologieResponse> addProgrammingLanguageTechnologie(
                        CreateProgrammingLanguageTechnologieRequest createProgrammingLanguageRequest) {

                GetByIdProgrammingLanguagesResponse getByIdProgrammingLanguagesResponse = this.programmingLanguageService
                                .getByIdProgrammingLanguage(createProgrammingLanguageRequest.programmingLanguageId())
                                .getData();

                ProgrammingLanguage programmingLanguage = new ProgrammingLanguage(
                                getByIdProgrammingLanguagesResponse.id(),
                                getByIdProgrammingLanguagesResponse.name());

                ProgrammingLanguageTechnologie programmingLanguageTechnologie = this.programmingLanguageTechnologieRepository
                                .save(new ProgrammingLanguageTechnologie(createProgrammingLanguageRequest.name(),
                                                programmingLanguage));

                programmingLanguage
                                .setProgrammingLanguageTechnologies(programmingLanguageTechnologieRepository
                                                .findByProgrammingLanguageId(programmingLanguage.getId()));

                applicationEventPublisher.publishEvent(new ProgrammingLanguageEsCreatedEvent(programmingLanguage));

                return new SuccessDataResult<>(
                                CreateProgrammingLanguageTechnologieResponse.convert(programmingLanguageTechnologie),
                                Messages.PROGRAMMİNGLANGUAGETECHNOLOGIEADD);
        }

        @Override
        public DataResult<List<GetAllProgrammingLanguagesTechnologieResponse>> getAllProgrammingLanguageTechnologies() {
                return new SuccessDataResult<>(programmingLanguageTechnologieRepository.findAll().stream()
                                .map(GetAllProgrammingLanguagesTechnologieResponse::convert)
                                .collect(Collectors.toList()));
        }

        @Override
        public DataResult<UpdateProgrammingLanguageTechnologieResponse> updateProgrammingLanguageTechnologies(int id,
                        UpdateProgrammingLanguageTechnologieRequest updateProgrammingLanguageRequest) {

                GetByIdProgrammingLanguagesResponse getByIdProgrammingLanguagesResponse = this.programmingLanguageService
                                .getByIdProgrammingLanguage(updateProgrammingLanguageRequest.programmingLanguageId())
                                .getData();

                ProgrammingLanguage programmingLanguage = new ProgrammingLanguage(
                                getByIdProgrammingLanguagesResponse.id(),
                                getByIdProgrammingLanguagesResponse.name());

                Optional<ProgrammingLanguageTechnologie> optionalProgrammingLanguageTechnologie = programmingLanguageTechnologieRepository
                                .findById(id);

                if (optionalProgrammingLanguageTechnologie.isEmpty()) {
                        return new ErrorDataResult<>(Messages.PROGRAMMİNGLANGUAGETECHNOLOGIENOTFOUND);
                }

                optionalProgrammingLanguageTechnologie.ifPresent(programmingLanguageTechnologie -> {
                        programmingLanguageTechnologie.setId(id);
                        programmingLanguageTechnologie.setName(updateProgrammingLanguageRequest.name());
                        programmingLanguageTechnologie.setProgrammingLanguage(programmingLanguage);
                        programmingLanguageTechnologieRepository.save(programmingLanguageTechnologie);
                });

                programmingLanguage
                                .setProgrammingLanguageTechnologies(programmingLanguageTechnologieRepository
                                                .findByProgrammingLanguageId(programmingLanguage.getId()));

                applicationEventPublisher.publishEvent(new ProgrammingLanguageEsCreatedEvent(programmingLanguage));

                return new SuccessDataResult<>(optionalProgrammingLanguageTechnologie
                                .map(UpdateProgrammingLanguageTechnologieResponse::convert).get());
        }

        @Override
        public Result deleteProgrammingLanguageTechnologie(int id) {
                Optional<ProgrammingLanguageTechnologie> programmingLanguageTechnologie = programmingLanguageTechnologieRepository
                                .findById(id);

                if (programmingLanguageTechnologie.isEmpty()) {
                        return new ErrorResult(Messages.PROGRAMMİNGLANGUAGETECHNOLOGIENOTFOUND);
                }
                programmingLanguageTechnologieRepository.delete(programmingLanguageTechnologie.get());

                int programmingLanguageId = programmingLanguageTechnologie.get().getProgrammingLanguage().getId();
                ProgrammingLanguage programmingLanguage = programmingLanguageService.findById(programmingLanguageId);
                programmingLanguage.setProgrammingLanguageTechnologies(programmingLanguageTechnologieRepository
                                .findByProgrammingLanguageId(programmingLanguageId));

                applicationEventPublisher.publishEvent(new ProgrammingLanguageEsCreatedEvent(programmingLanguage));

                return new SuccessResult(Messages.PROGRAMMİNGLANGUAGETECHNOLOGIEDELETE);

        }

}
