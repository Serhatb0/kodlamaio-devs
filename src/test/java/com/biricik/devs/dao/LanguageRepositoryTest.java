package com.biricik.devs.dao;

import java.util.Optional;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.biricik.devs.TestSupport;
import com.biricik.devs.dao.abstracts.LanguageRepository;
import com.biricik.devs.entities.concretes.Language;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
public class LanguageRepositoryTest extends TestSupport {

    @Autowired
    private LanguageRepository languageRepository;

    @Nested
    class findByName {

        @Test
        public void itShouldCheckWhenLanguageFindByName() {
            Language language = languageRepository.save(generateLanguage());

            Optional<Language> optionalLanguage = languageRepository.findByName(language.getName());

            assertThat(optionalLanguage.get()).isNotNull();
            assertThat(optionalLanguage.get()).isEqualTo(language);
            assertThat(optionalLanguage.get().getName()).isEqualTo(language.getName());
            assertThat(optionalLanguage.get().getId()).isEqualTo(language.getId());
            assertThat(optionalLanguage.get().getTranslations()).isNull();
        }

        @Test
        public void itShouldCheckWhenLanguageFindByNameIsEmpty() {
            assertThat(languageRepository.findByName("English")).isEmpty();
        }
    }

}
