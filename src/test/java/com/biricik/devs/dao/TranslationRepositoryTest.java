package com.biricik.devs.dao;

import java.util.Optional;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.biricik.devs.TestSupport;
import com.biricik.devs.dao.abstracts.postgresql.LanguageRepository;
import com.biricik.devs.dao.abstracts.postgresql.TranslationRepository;
import com.biricik.devs.dao.abstracts.postgresql.WordRepository;
import com.biricik.devs.entities.concretes.Language;
import com.biricik.devs.entities.concretes.Translation;
import com.biricik.devs.entities.concretes.Word;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
public class TranslationRepositoryTest extends TestSupport {

    @Autowired
    private TranslationRepository translationRepository;

    @Autowired
    private LanguageRepository languageRepository;

    @Autowired
    private WordRepository wordRepository;

    @Nested
    class findTranslationByLanguage_IdAndWord_Id {

        @Test
        public void itShouldCheckWhenTranslationFindTranslationByLanguageIdAndWordId() {

            Language language = languageRepository.save(generateLanguage());

            Word word = wordRepository.save(generateWord());

            Translation translation = translationRepository.save(generateTranslation(word, language));

            Optional<Translation> optionalTranslation = translationRepository
                    .findTranslationByLanguage_IdAndWord_Id(language.getId(), word.getId());

            assertThat(optionalTranslation.get()).isEqualTo(translation);
            assertThat(optionalTranslation.get().getTranslation()).isEqualTo(translation.getTranslation());
            assertThat(optionalTranslation.get().getLanguage()).isEqualTo(language);
            assertThat(optionalTranslation.get().getLanguage().getName()).isEqualTo(language.getName());
            assertThat(optionalTranslation.get().getWord()).isEqualTo(word);
            assertThat(optionalTranslation.get().getWord().getKey()).isEqualTo(word.getKey());

        }

        @Test
        public void itShouldCheckWhenTranslationFindTranslationByLanguageIdAndWordIdIsEmpty() {
            assertThat(translationRepository.findTranslationByLanguage_IdAndWord_Id(4, 5)).isEmpty();
        }

    }

}
