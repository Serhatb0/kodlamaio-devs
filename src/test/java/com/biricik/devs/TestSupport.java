package com.biricik.devs;

import java.util.Arrays;

import com.biricik.devs.business.requests.ProgrammingLanguageRequests.CreateProgrammingLanguageRequest;
import com.biricik.devs.business.requests.ProgrammingLanguageRequests.UpdateProgrammingLanguageRequest;
import com.biricik.devs.business.responses.ProgrammingLanguageResponses.CreateProgrammingLanguageResponse;
import com.biricik.devs.business.responses.ProgrammingLanguageResponses.GetAllProgrammingLanguagesResponse;
import com.biricik.devs.business.responses.ProgrammingLanguageResponses.GetByIdProgrammingLanguagesResponse;
import com.biricik.devs.business.responses.ProgrammingLanguageResponses.UpdateProgrammingLanguageResponse;
import com.biricik.devs.entities.concretes.Language;
import com.biricik.devs.entities.concretes.ProgrammingLanguage;
import com.biricik.devs.entities.concretes.ProgrammingLanguageTechnologie;
import com.biricik.devs.entities.concretes.Translation;
import com.biricik.devs.entities.concretes.Word;

public class TestSupport {

    public Word generateWord() {
        return new Word("programming.language.not.found");
    }

    public ProgrammingLanguage generateProgrammingLanguage() {
        return new ProgrammingLanguage(0, "Java");
    }

    public ProgrammingLanguageTechnologie generateProgrammingLanguageTechnologie() {
        return new ProgrammingLanguageTechnologie(
                "Spring", generateProgrammingLanguage());
    }

    public Language generateLanguage() {
        return new Language("English");
    }

    public Translation generateTranslation(Word word, Language language) {
        return new Translation(0, "Programming Language Not found", word, language);
    }

    public CreateProgrammingLanguageRequest generateCreateProgrammingLanguageRequest() {
        return new CreateProgrammingLanguageRequest(generateProgrammingLanguage().getName());
    }

    public CreateProgrammingLanguageResponse generateCreateProgrammingLanguageResponse() {
        return CreateProgrammingLanguageResponse.convert(generateProgrammingLanguage());
    }

    public GetByIdProgrammingLanguagesResponse generateGetByIdProgrammingLanguagesResponse() {
        return GetByIdProgrammingLanguagesResponse.convert(generateProgrammingLanguage());
    }

    public GetAllProgrammingLanguagesResponse generateGetAllProgrammingLanguagesResponse() {
        return GetAllProgrammingLanguagesResponse
                .convert(new ProgrammingLanguage(0, "Java", Arrays.asList(generateProgrammingLanguageTechnologie())));
    }

    public UpdateProgrammingLanguageRequest generateUpdateProgrammingLanguageRequest(){
        return new UpdateProgrammingLanguageRequest(generateProgrammingLanguage().getName());
    }

    public UpdateProgrammingLanguageResponse generateUpdateProgrammingLanguageResponse(){
        return UpdateProgrammingLanguageResponse.convert(generateProgrammingLanguage());
    }

}
