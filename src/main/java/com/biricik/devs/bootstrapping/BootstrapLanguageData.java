package com.biricik.devs.bootstrapping;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
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
         loadLanguageData();
         loadWordData();
         loadTranslationEnglishData();
         loadTranslationTurkishData();
    }

  


    private void loadLanguageData() {
        this.languageRepository.save(new Language("English"));
        this.languageRepository.save(new Language("Turkish"));

    }

    private void loadWordData() {

        List<Word> words = new ArrayList<>();

     
        words.add(new Word("0001"));
        words.add(new Word("0002"));

        this.wordRepository.saveAll(words);

    }

    private void loadTranslationEnglishData() {
        List<Translation> translationsEnglish = new ArrayList<>();
        Language english = this.languageRepository.findByName("English").get();

      

      
        translationsEnglish.add(new Translation(0, "Technologies of a Programming Language should be at most 4",
                wordRepository.findWordByKey("0001").get(), english));
        
        translationsEnglish.add(new Translation(0, "Element not found",
                wordRepository.findWordByKey("0002").get(), english));

        this.translationRepository.saveAll(translationsEnglish);

    }

    private void loadTranslationTurkishData() {
        List<Translation> translationsTurlish = new ArrayList<>();
        Language turkish = this.languageRepository.findByName("Turkish").get();

 
        
        translationsTurlish.add(new Translation(0, "Bir Programlama Dilinin Teknolojileri en fazla 4 tane olmalı",
                wordRepository.findWordByKey("0001").get(), turkish));
        
        translationsTurlish.add(new Translation(0, "Öğe bulunamadı",
                wordRepository.findWordByKey("0002").get(), turkish));



        this.translationRepository.saveAll(translationsTurlish);

    }

}
