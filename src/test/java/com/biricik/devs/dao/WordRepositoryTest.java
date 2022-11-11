package com.biricik.devs.dao;

import java.util.Optional;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.biricik.devs.TestSupport;
import com.biricik.devs.dao.abstracts.WordRepository;
import com.biricik.devs.entities.concretes.Word;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
public class WordRepositoryTest extends TestSupport {

    @Autowired
    private WordRepository wordRepository;

    @Nested
    class findWordByKey {

        @Test
        public void itShouldCheckWhenWordFindByWordKey() {

            Word word = wordRepository.save(generateWord());

            Optional<Word> optionalWord = wordRepository.findWordByKey("programming.language.not.found");

            assertThat(optionalWord.get()).isNotNull();
            assertThat(optionalWord.get()).isEqualTo(word);
            assertThat(optionalWord.get().getKey()).isEqualTo(word.getKey());
            assertThat(optionalWord.get().getId()).isEqualTo(word.getId());
            assertThat(optionalWord.get().getTranslations()).isNull();

        }

        @Test
        public void itShouldCheckWhenWordFindByWordKeyIsEmpty() {
            assertThat(wordRepository.findWordByKey("programming.language.not.found")).isEmpty();
        }

    }

    @Nested
    class existsByKey {

        @Test
        public void itShouldCheckWhenWordExistsByKey() {
            String key = "programming.language.not.found";

            wordRepository.save(generateWord());

            boolean expected = wordRepository.existsByKey(key);

            assertThat(expected).isTrue();

        }

        @Test
        public void itShouldCheckWhenWordDoesNotExistsByKey() {
            String key = "programming.language.not.found";

            boolean expected = wordRepository.existsByKey(key);

            assertThat(expected).isFalse();

        }
    }
}
