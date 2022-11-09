package com.biricik.devs.business.responses.ProgrammingLanguageResponses;


import java.util.List;

import com.biricik.devs.entities.concretes.ProgrammingLanguage;

public record GetAllProgrammingLanguagesResponse(int id, String name,List<String> technologieName) {

    public static GetAllProgrammingLanguagesResponse convert(final ProgrammingLanguage programmingLanguage) {
        return new GetAllProgrammingLanguagesResponse(programmingLanguage.getId(), programmingLanguage.getName(),
        programmingLanguage.getProgrammingLanguageTechnologies().stream().map(item -> item.getName()).toList());
    }
}
