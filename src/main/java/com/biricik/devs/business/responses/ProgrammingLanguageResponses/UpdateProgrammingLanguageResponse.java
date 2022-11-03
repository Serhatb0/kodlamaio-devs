package com.biricik.devs.business.responses.ProgrammingLanguageResponses;

import com.biricik.devs.entities.concretes.ProgrammingLanguage;

public record UpdateProgrammingLanguageResponse(int id, String name) {

    public UpdateProgrammingLanguageResponse() {
        this(0, null);
    }

    public static UpdateProgrammingLanguageResponse convert(final ProgrammingLanguage programmingLanguage) {
        return new UpdateProgrammingLanguageResponse(programmingLanguage.getId(), programmingLanguage.getName());
    }
}
