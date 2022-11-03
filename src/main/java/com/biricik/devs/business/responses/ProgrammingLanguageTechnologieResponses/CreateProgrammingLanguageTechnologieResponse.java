package com.biricik.devs.business.responses.ProgrammingLanguageTechnologieResponses;

import com.biricik.devs.entities.concretes.ProgrammingLanguageTechnologie;

public record CreateProgrammingLanguageTechnologieResponse(int id 
,String programmingLanguageTechnologieName,String programmingLanguageName) {
    

    public static CreateProgrammingLanguageTechnologieResponse convert(final ProgrammingLanguageTechnologie programmingLanguageTechnologie) {
        return new CreateProgrammingLanguageTechnologieResponse(programmingLanguageTechnologie.getId(), 
        programmingLanguageTechnologie.getName(),programmingLanguageTechnologie.getProgrammingLanguage().getName());
    }
}
