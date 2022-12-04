package com.biricik.devs.dao.abstracts.postgresql;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.biricik.devs.entities.concretes.Language;

public interface LanguageRepository  extends JpaRepository<Language,Integer>{
    
    Optional<Language> findByName(String name);
}
