package com.biricik.devs.dao.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import com.biricik.devs.entities.concretes.ProgrammingLanguageTechnologie;

public interface ProgrammingLanguageTechnologieRepository extends JpaRepository<ProgrammingLanguageTechnologie,Integer> {
    
}
