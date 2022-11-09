package com.biricik.devs.dao;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.biricik.devs.dao.abstracts.ProgrammingLanguageRepository;
import com.biricik.devs.entities.concretes.ProgrammingLanguage;
import com.biricik.devs.entities.concretes.ProgrammingLanguageTechnologie;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@DataJpaTest
// @Transactional(propagation = Propagation.NOT_SUPPORTED)
public class ProgrammingLanguageRepositoryTest {

    @Autowired
    private ProgrammingLanguageRepository programmingLanguageRepository;

    @AfterEach
    void tearDown() {
        programmingLanguageRepository.deleteAll();
    }

    @Nested
    class findByName {

        @Test
        void itShouldCheckWhenProgrammingLanguageFindByNameIsEmpty() {
            assertThat(programmingLanguageRepository.findByName("Java")).isEmpty();
        }

        @Test
        void itShouldCheckWhenProgrammingLanguageFindByName() {

            ProgrammingLanguage programmingLanguage = programmingLanguageRepository
                    .save(new ProgrammingLanguage("Java"));

            ProgrammingLanguageTechnologie programmingLanguageTechnologie = new ProgrammingLanguageTechnologie(
                    "Spring", programmingLanguage);
            ProgrammingLanguageTechnologie programmingLanguageTechnologie2 = new ProgrammingLanguageTechnologie(
                    "Hibernate", programmingLanguage);

            List<ProgrammingLanguageTechnologie> programmingLanguageTechnologies = new ArrayList<>();
            programmingLanguageTechnologies.add(programmingLanguageTechnologie);
            programmingLanguageTechnologies.add(programmingLanguageTechnologie2);

            programmingLanguage.setProgrammingLanguageTechnologies(programmingLanguageTechnologies);
            programmingLanguageRepository.save(programmingLanguage);

            Optional<ProgrammingLanguage> optionalProgrammingLanguage = programmingLanguageRepository
                    .findByName("Java");

            assertThat(optionalProgrammingLanguage.get()).isEqualTo(programmingLanguage);
            assertThat(optionalProgrammingLanguage.get().getName()).isEqualTo(programmingLanguage.getName());
            assertThat(optionalProgrammingLanguage.get().getId()).isEqualTo(programmingLanguage.getId());

            assertThat(optionalProgrammingLanguage.get().getProgrammingLanguageTechnologies().size())
                    .isEqualTo(programmingLanguage.getProgrammingLanguageTechnologies().size());
        }
    }

    @Nested
    class ExistsByName {

        @Test
        void itShouldCheckWhenProgrammingLanguageExistsByName() {
            String name = "Java";

            ProgrammingLanguage programmingLanguage = new ProgrammingLanguage(name);

            programmingLanguageRepository.save(programmingLanguage);

            boolean expected = programmingLanguageRepository.existsByName(name);

            assertThat(expected).isTrue();

        }

        @Test
        void itShouldCheckWhenProgrammingLanguageByNameDoesNotExists() {
            String name = "Java";

            boolean expected = programmingLanguageRepository.existsByName(name);
            assertThat(expected).isFalse();
        }
    }

}
