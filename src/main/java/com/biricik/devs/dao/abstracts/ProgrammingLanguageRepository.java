package com.biricik.devs.dao.abstracts;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.biricik.devs.entities.concretes.ProgrammingLanguage;

@Repository
public interface ProgrammingLanguageRepository extends JpaRepository<ProgrammingLanguage, Integer> {

    Boolean existsByName(String name);

    Optional<ProgrammingLanguage> findByName(String name);


}
