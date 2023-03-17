package com.biricik.devs.dao.abstracts;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.biricik.devs.entities.concretes.Word;

public interface WordRepository extends JpaRepository<Word, Integer> {

    Optional<Word> findWordByKey(String key);

    boolean existsByKey(String key);
}
