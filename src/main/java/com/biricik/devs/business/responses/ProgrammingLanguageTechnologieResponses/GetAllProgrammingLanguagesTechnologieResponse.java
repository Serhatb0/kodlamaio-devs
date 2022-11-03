package com.biricik.devs.business.responses.ProgrammingLanguageTechnologieResponses;

import com.biricik.devs.entities.concretes.ProgrammingLanguageTechnologie;

public record GetAllProgrammingLanguagesTechnologieResponse(int id 
,String programmingLanguageTechnologieName,String programmingLanguageName) {
    


    public static GetAllProgrammingLanguagesTechnologieResponse convert(final ProgrammingLanguageTechnologie programmingLanguageTechnologie) {
        return new GetAllProgrammingLanguagesTechnologieResponse(programmingLanguageTechnologie.getId(), 
        programmingLanguageTechnologie.getName(),programmingLanguageTechnologie.getProgrammingLanguage().getName());
    }
}
