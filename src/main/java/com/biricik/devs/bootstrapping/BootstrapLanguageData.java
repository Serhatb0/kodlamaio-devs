package com.biricik.devs.bootstrapping;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.biricik.devs.dao.abstracts.LanguageRepository;
import com.biricik.devs.dao.abstracts.TranslationRepository;
import com.biricik.devs.dao.abstracts.WordRepository;
import com.biricik.devs.entities.concretes.Language;
import com.biricik.devs.entities.concretes.Translation;
import com.biricik.devs.entities.concretes.Word;

@Component
public class BootstrapLanguageData implements CommandLineRunner {

    private final WordRepository wordRepository;
    private final LanguageRepository languageRepository;
    private final TranslationRepository translationRepository;

    public BootstrapLanguageData(WordRepository wordRepository, LanguageRepository languageRepository,
            TranslationRepository translationRepository) {
        this.wordRepository = wordRepository;
        this.languageRepository = languageRepository;
        this.translationRepository = translationRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        // loadLanguageData();
        // loadWordData();
        // loadTranslationEnglishData();
        // loadTranslationTurkishData();
    }

  


    private void loadLanguageData() {
        this.languageRepository.save(new Language("English"));
        this.languageRepository.save(new Language("Turkish"));

    }

    private void loadWordData() {

        List<Word> words = new ArrayList<>();

        words.add(new Word("programming.language.added"));
        words.add(new Word("programming.language.updated"));
        words.add(new Word("programming.language.delete"));
        words.add(new Word("programming.language.not.found"));

        words.add(new Word("programming.language.technologie.added"));
        words.add(new Word("programming.language.technologie.updated"));
        words.add(new Word("programming.language.technologie.delete"));
        words.add(new Word("programming.language.technologie.not.found"));

        this.wordRepository.saveAll(words);

    }

    private void loadTranslationEnglishData() {
        List<Translation> translationsEnglish = new ArrayList<>();
        Language english = this.languageRepository.findByName("English").get();

        translationsEnglish.add(new Translation(0, "Programming Language Added",
                wordRepository.findWordByKey("programming.language.added").get(), english));

        translationsEnglish.add(new Translation(0, "Programming Language Updated",
                wordRepository.findWordByKey("programming.language.updated").get(), english));

        translationsEnglish.add(new Translation(0, "Programming Language Deleted",
                wordRepository.findWordByKey("programming.language.delete").get(), english));

        translationsEnglish.add(new Translation(0, "Programming Language Not Found",
                wordRepository.findWordByKey("programming.language.not.found").get(), english));

        translationsEnglish.add(new Translation(0, "Programming Language Technologie Added",
                wordRepository.findWordByKey("programming.language.technologie.added").get(), english));

        translationsEnglish.add(new Translation(0, "Programming Language Technologie Updated",
                wordRepository.findWordByKey("programming.language.technologie.updated").get(), english));

        translationsEnglish.add(new Translation(0, "Programming Language Technologie Deleted",
                wordRepository.findWordByKey("programming.language.technologie.delete").get(), english));

        translationsEnglish.add(new Translation(0, "Programming Language Technologie Not Found",
                wordRepository.findWordByKey("programming.language.technologie.not.found").get(), english));

        this.translationRepository.saveAll(translationsEnglish);

    }

    private void loadTranslationTurkishData() {
        List<Translation> translationsTurlish = new ArrayList<>();
        Language turkish = this.languageRepository.findByName("Turkish").get();

        translationsTurlish.add(new Translation(0, "Programlama Dili Eklendi",
                wordRepository.findWordByKey("programming.language.added").get(), turkish));

        translationsTurlish.add(new Translation(0, "Programlama Dili Güncellendi",
                wordRepository.findWordByKey("programming.language.updated").get(), turkish));

        translationsTurlish.add(new Translation(0, "Programlama Dili Silindi",
                wordRepository.findWordByKey("programming.language.delete").get(), turkish));

        translationsTurlish.add(new Translation(0, "Programlama Dili Bulunamıyor",
                wordRepository.findWordByKey("programming.language.not.found").get(), turkish));

        translationsTurlish.add(new Translation(0, "Programlama Dili Teknolojisi Eklendi",
                wordRepository.findWordByKey("programming.language.technologie.added").get(), turkish));

        translationsTurlish.add(new Translation(0, "Programlama Dili Teknolojisi Güncellendi",
                wordRepository.findWordByKey("programming.language.technologie.updated").get(), turkish));

        translationsTurlish.add(new Translation(0, "Programlama Dili Teknolojisi Silindi",
                wordRepository.findWordByKey("programming.language.technologie.delete").get(), turkish));

        translationsTurlish.add(new Translation(0, "Programlama Dili Teknolojisi Bulanamıyor",
                wordRepository.findWordByKey("programming.language.technologie.not.found").get(), turkish));

        this.translationRepository.saveAll(translationsTurlish);

    }

}
