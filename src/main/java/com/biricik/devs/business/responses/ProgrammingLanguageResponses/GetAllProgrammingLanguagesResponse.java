package com.biricik.devs.business.responses.ProgrammingLanguageResponses;


import java.util.Collections;
import java.util.List;

import com.biricik.devs.entities.concretes.ProgrammingLanguage;
import com.biricik.devs.entities.elasticsearch.ProgrammingLanguageEs;

public record GetAllProgrammingLanguagesResponse(int id, String name,List<String> technologieName) {

    public static GetAllProgrammingLanguagesResponse convert(final ProgrammingLanguage programmingLanguage) {
        return new GetAllProgrammingLanguagesResponse(programmingLanguage.getId(), programmingLanguage.getName(),
        programmingLanguage.getProgrammingLanguageTechnologies().stream().map(item -> item.getName()).toList());
    }

    

    public static GetAllProgrammingLanguagesResponse convert(final ProgrammingLanguageEs programmingLanguageEs) {
        if(programmingLanguageEs.getProgrammingLanguageTechnologieEs() == null){
            programmingLanguageEs.setProgrammingLanguageTechnologieEs(Collections.emptyList());
        }

        return new GetAllProgrammingLanguagesResponse(programmingLanguageEs.getId(), programmingLanguageEs.getName(),
            programmingLanguageEs.getProgrammingLanguageTechnologieEs().stream().map(item -> item.getName()).toList());

    }
}
