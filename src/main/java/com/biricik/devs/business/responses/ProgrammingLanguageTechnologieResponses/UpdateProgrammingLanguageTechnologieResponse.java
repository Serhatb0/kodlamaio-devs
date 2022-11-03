package com.biricik.devs.business.responses.ProgrammingLanguageTechnologieResponses;

import com.biricik.devs.entities.concretes.ProgrammingLanguageTechnologie;

public record UpdateProgrammingLanguageTechnologieResponse(int id,String name,String programmingLanguageName) {
    
    public UpdateProgrammingLanguageTechnologieResponse(){
        this(0,null,null);
    }

    public static UpdateProgrammingLanguageTechnologieResponse convert(final ProgrammingLanguageTechnologie programmingLanguageTechnologie) {
        return new UpdateProgrammingLanguageTechnologieResponse(programmingLanguageTechnologie.getId(),
         programmingLanguageTechnologie.getName(),programmingLanguageTechnologie.getProgrammingLanguage().getName());
    }
}
