package com.biricik.devs.business.responses.ProgrammingLanguageResponses;

import com.biricik.devs.entities.concretes.ProgrammingLanguage;

public record GetByIdProgrammingLanguagesResponse(int id ,String name) {


    public GetByIdProgrammingLanguagesResponse(){
        this(0,null);
    }
    
    public static GetByIdProgrammingLanguagesResponse convert(final ProgrammingLanguage programmingLanguage) {
        return new GetByIdProgrammingLanguagesResponse(programmingLanguage.getId(), programmingLanguage.getName());
    }
}
