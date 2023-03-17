package com.biricik.devs.core.utilities;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

import com.biricik.devs.core.constants.Languages;
import com.biricik.devs.dao.abstracts.LanguageRepository;
import com.biricik.devs.dao.abstracts.TranslationRepository;
import com.biricik.devs.dao.abstracts.WordRepository;
import com.biricik.devs.entities.concretes.Language;
import com.biricik.devs.entities.concretes.Translation;
import com.biricik.devs.entities.concretes.Word;

@Component
public class LanguageSelector {

    private static LanguageRepository languageRepository;
    private static WordRepository wordRepository;
    private static TranslationRepository translationRepository;
    private static HttpServletRequest request;

    public LanguageSelector(LanguageRepository languageRepository, WordRepository wordRepository,
            TranslationRepository translationRepository, HttpServletRequest request) {

        LanguageSelector.languageRepository = languageRepository;
        LanguageSelector.wordRepository = wordRepository;
        LanguageSelector.translationRepository = translationRepository;
        LanguageSelector.request = request;

    }

    public static String selector(String message) {
        String selectedLanguage =request.getHeader("Accept-Language");
        String result = Languages.ENGLISH;
        if (selectedLanguage == null) {
            result = Languages.ENGLISH;
        }else if (selectedLanguage.equals("tr")){
            result = Languages.TURKISH;
        }

        Optional<Language> language = languageRepository.findByName(result);

        if (language.isPresent()) {
            language.get().setName(Languages.ENGLISH);
        }
        if (!wordRepository.findWordByKey(message).isPresent()) {
            return "message not found";
        }
        if(message == null){
            return message;
        }

        Word word = wordRepository.findWordByKey(message).get();

        Translation translation = translationRepository
                .findTranslationByLanguage_IdAndWord_Id(language.get().getId(), word.getId()).get();

        return translation == null ? "message not found" : translation.getTranslation();
    }

}
