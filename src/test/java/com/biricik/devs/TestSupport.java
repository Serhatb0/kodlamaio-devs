package com.biricik.devs;

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
        return new ProgrammingLanguage("Java");
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

}
