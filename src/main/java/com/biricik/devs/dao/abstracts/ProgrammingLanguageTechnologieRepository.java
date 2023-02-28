package com.biricik.devs.dao.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.biricik.devs.entities.concretes.ProgrammingLanguageTechnologie;

public interface ProgrammingLanguageTechnologieRepository extends JpaRepository<ProgrammingLanguageTechnologie,Integer> {
    

    List<ProgrammingLanguageTechnologie> findByProgrammingLanguageId(int id);
    
    
}
