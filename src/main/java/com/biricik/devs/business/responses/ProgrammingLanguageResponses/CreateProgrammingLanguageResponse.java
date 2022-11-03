package com.biricik.devs.business.responses.ProgrammingLanguageResponses;

import com.biricik.devs.entities.concretes.ProgrammingLanguage;

public record CreateProgrammingLanguageResponse(int id ,String name) {
    
    public static CreateProgrammingLanguageResponse convert(final ProgrammingLanguage programmingLanguage) {
        return new CreateProgrammingLanguageResponse(programmingLanguage.getId(), programmingLanguage.getName());
    }
}
