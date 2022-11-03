package com.biricik.devs.business.responses.ProgrammingLanguageResponses;

import com.biricik.devs.entities.concretes.ProgrammingLanguage;

public record GetAllProgrammingLanguagesResponse(int id, String name) {

    public static GetAllProgrammingLanguagesResponse convert(final ProgrammingLanguage programmingLanguage) {
        return new GetAllProgrammingLanguagesResponse(programmingLanguage.getId(), programmingLanguage.getName());
    }
}
