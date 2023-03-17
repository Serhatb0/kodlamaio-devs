package com.biricik.devs.dao.abstracts;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.biricik.devs.entities.concretes.Translation;

public interface TranslationRepository extends JpaRepository<Translation,Integer>{

    Optional<Translation> findTranslationByLanguage_IdAndWord_Id(int languageId,int wordId);
    
}
