package com.biricik.devs.webApi.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.biricik.devs.annotation.LogExecutionTime;
import com.biricik.devs.business.abstracts.ProgrammingLanguageService;
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

@RestController
@RequestMapping("/api")
public class ProgrammingLanguagesController {

    private final ProgrammingLanguageService programmingLanguageService;

    public ProgrammingLanguagesController(ProgrammingLanguageService programmingLanguageService) {
        this.programmingLanguageService = programmingLanguageService;

    }

    @GetMapping("/programming-languages")
    public DataResult<PaginatedGenericResponse<GetAllProgrammingLanguagesResponse>> getAllProgrammingLanguage(
            @RequestBody PaginatedRequest paginatedRequest) {

        return programmingLanguageService.getAllProgrammingLanguage(paginatedRequest);
    }

    @GetMapping("/programming-languages-name/{searchKey}")
    public DataResult<List<GetAllProgrammingLanguagesResponse>> findByProgrammingLanguageName(
            @PathVariable String searchKey) {
        return programmingLanguageService.findByProgrammingLanguageName(searchKey);

    }

    @GetMapping("/programming-languages-name-or-technologiname/{searchKey}")
    public DataResult<PaginatedGenericResponse<GetAllProgrammingLanguagesResponse>> findByProgrammingLanguageNameOrProgrammingLanguageTechnologieName(
            @RequestBody PaginatedRequest paginatedRequest, @PathVariable String searchKey) {
        return programmingLanguageService.findByProgrammingLanguageNameOrProgrammingLanguageTechnologieName(searchKey,
                paginatedRequest);

    }

    @LogExecutionTime
    @GetMapping("/programming-languages/{id}")
    public DataResult<GetByIdProgrammingLanguagesResponse> getByIdProgrammingLanguage(@PathVariable int id) {
        return programmingLanguageService.getByIdProgrammingLanguage(id);
    }

    @DeleteMapping("/programming-languages/{id}")
    public Result deleteProgrammingLanguage(@PathVariable int id) {
        return programmingLanguageService.deleteProgrammingLanguage(id);
    }

    @PutMapping("/programming-languages/{id}")
    public DataResult<UpdateProgrammingLanguageResponse> updateProgrammingLanguage(@PathVariable int id,
            @RequestBody UpdateProgrammingLanguageRequest updateProgrammingLanguageRequest) {
        return programmingLanguageService.updateProgrammingLanguage(id, updateProgrammingLanguageRequest);
    }

    @PostMapping("/programming-languages")
    public DataResult<CreateProgrammingLanguageResponse> addProgrammingLanguage(
            @RequestBody CreateProgrammingLanguageRequest createProgrammingLanguageRequest) {
        return programmingLanguageService.addProgrammingLanguage(createProgrammingLanguageRequest);
    }

}
