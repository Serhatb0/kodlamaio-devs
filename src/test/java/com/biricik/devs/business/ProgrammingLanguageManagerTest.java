package com.biricik.devs.business;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.biricik.devs.TestSupport;
import com.biricik.devs.business.concretes.ProgrammingLanguageManager;
import com.biricik.devs.business.responses.ProgrammingLanguageResponses.CreateProgrammingLanguageResponse;
import com.biricik.devs.business.responses.ProgrammingLanguageResponses.GetAllProgrammingLanguagesResponse;
import com.biricik.devs.business.responses.ProgrammingLanguageResponses.GetByIdProgrammingLanguagesResponse;
import com.biricik.devs.business.responses.ProgrammingLanguageResponses.UpdateProgrammingLanguageResponse;
import com.biricik.devs.core.utilities.result.DataResult;
import com.biricik.devs.core.utilities.result.Result;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import com.biricik.devs.dao.abstracts.ProgrammingLanguageRepository;
import com.biricik.devs.entities.concretes.ProgrammingLanguage;

import static org.mockito.BDDMockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class ProgrammingLanguageManagerTest extends TestSupport {

    @InjectMocks
    private ProgrammingLanguageManager programmingLanguageService;

    @Mock
    private ProgrammingLanguageRepository programmingLanguageRepository;

    @Nested
    @DisplayName("Create Methods")
    class Create {
        @Test
        public void testCreateProgrammingLanguage_whenCreateProgrammingLanguageRequestExists_shouldCreateProgrammingLanguageResponse() {

            given(programmingLanguageRepository.save(generateProgrammingLanguage()))
                    .willReturn(generateProgrammingLanguage());

            DataResult<CreateProgrammingLanguageResponse> response = programmingLanguageService
                    .addProgrammingLanguage(generateCreateProgrammingLanguageRequest());

            verify(programmingLanguageRepository).save(generateProgrammingLanguage());
            assertThat(response.getData()).isNotNull();
            assertThat(response.getMessageTest()).isEqualTo("programming.language.added");
            assertThat(response.getData()).isEqualTo(generateCreateProgrammingLanguageResponse());

        }

    }

    @Nested
    @DisplayName("Update Methods")
    class Update {
        @Test
        public void testUpdateProgrammingLanguage_whenProgrammingLanguageIdExists_shouldUpdateProgrammingLanguageResponse() {

            given(programmingLanguageRepository.findById(0))
                    .willReturn(Optional.of(generateProgrammingLanguage()));

            given(programmingLanguageRepository.save(generateProgrammingLanguage()))
                    .willReturn(generateProgrammingLanguage());

            DataResult<UpdateProgrammingLanguageResponse> response = programmingLanguageService
                    .updateProgrammingLanguage(0, generateUpdateProgrammingLanguageRequest());

            verify(programmingLanguageRepository, times(1)).save(generateProgrammingLanguage());
            verify(programmingLanguageRepository, times(1)).findById(0);

            assertThat(response.getData()).isNotNull();
            assertThat(response.getMessageTest()).isEqualTo("programming.language.updated");
            assertThat(response.getData()).isEqualTo(generateUpdateProgrammingLanguageResponse());

        }

        @Test
        public void testUpdateProgrammingLanguage_whenProgrammingLanguageIdDoesNotExists_shouldErrorDataResultMessageIsProgrammingLanguageNotFound() {

            given(programmingLanguageRepository.findById(0))
                    .willReturn(Optional.empty());

            DataResult<UpdateProgrammingLanguageResponse> response = programmingLanguageService
                    .updateProgrammingLanguage(0, generateUpdateProgrammingLanguageRequest());

            verify(programmingLanguageRepository, never()).save(generateProgrammingLanguage());
            verify(programmingLanguageRepository, times(1)).findById(0);

            assertThat(response.getData()).isNull();
            assertThat(response.getMessageTest()).isEqualTo("programming.language.not.found");

        }

    }

    @Nested
    @DisplayName("GetAll Methods")
    class GetAll {
        @Test
        public void testGetAllProgrammingLanguage_shouldVerifyProgrammingLanguageRepositoryFindAll() {
            given(programmingLanguageRepository.findAll())
                    .willReturn(Arrays.asList(new ProgrammingLanguage(
                            0, "Java", Arrays.asList(generateProgrammingLanguageTechnologie()))));

            DataResult<List<GetAllProgrammingLanguagesResponse>> response = programmingLanguageService
                    .getAllProgrammingLanguage();

            verify(programmingLanguageRepository, times(1)).findAll();
            assertThat(response.getData()).isNotNull();
            assertThat(response.getData()).isEqualTo(Arrays.asList(generateGetAllProgrammingLanguagesResponse()));
            assertThat(response.getMessageTest()).isNull();
            ;

        }

    }

    @Nested
    @DisplayName("Get One Methods")
    class GetOne {
        @Test
        public void testGetByIdProgrammingLanguage_whenProgrammingLanguageIdExists_shouldGetByIdProgrammingLanguagesResponse() {

            given(programmingLanguageRepository.findById(0))
                    .willReturn(Optional.of(generateProgrammingLanguage()));

            DataResult<GetByIdProgrammingLanguagesResponse> response = programmingLanguageService
                    .getByIdProgrammingLanguage(0);

            verify(programmingLanguageRepository).findById(0);
            assertThat(response.getData()).isNotNull();
            assertThat(response.getMessageTest()).isNull();
            ;
            assertThat(response.getData()).isEqualTo(generateGetByIdProgrammingLanguagesResponse());

        }

        @Test
        public void testGetByIdProgrammingLanguage_whenProgrammingLanguageIdDoesNotExists_shouldErrorDataResult() {

            given(programmingLanguageRepository.findById(0))
                    .willReturn(Optional.empty());

            DataResult<GetByIdProgrammingLanguagesResponse> response = programmingLanguageService
                    .getByIdProgrammingLanguage(0);

            verify(programmingLanguageRepository).findById(0);
            assertThat(response.getData()).isNull();
            assertThat(response.getMessageTest()).isEqualTo("programming.language.not.found");

        }

    }

    @Nested
    @DisplayName("Delete Methods")
    class Delete {
        @Test
        public void testDeleteProgrammingLanguage_whenProgrammingLanguageIdExists_shouldSuccessResultIsMessageProgrammingLanguageDelete() {

            given(programmingLanguageRepository.findById(0))
                    .willReturn(Optional.of(generateProgrammingLanguage()));

            doNothing().when(programmingLanguageRepository).delete(generateProgrammingLanguage());

            Result response = programmingLanguageService
                    .deleteProgrammingLanguage(0);

            verify(programmingLanguageRepository, times(1)).findById(0);
            verify(programmingLanguageRepository, times(1)).delete(generateProgrammingLanguage());
            assertThat(response.getMessageTest()).isEqualTo("programming.language.delete");

        }

        @Test
        public void testDeleteProgrammingLanguage_whenProgrammingLanguageIdDoesNotExists_shouldErrorResultIsMessageProgrammingLanguageNotFound() {
            given(programmingLanguageRepository.findById(0))
                    .willReturn(Optional.empty());

            Result response = programmingLanguageService
                    .deleteProgrammingLanguage(0);

            verify(programmingLanguageRepository, times(1)).findById(0);
            verify(programmingLanguageRepository, never()).delete(generateProgrammingLanguage());
            assertThat(response.getMessageTest()).isEqualTo("programming.language.not.found");

        }

    }

}
