package com.biricik.devs.webApi.controllers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.http.MediaType;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import com.biricik.devs.TestSupport;
import com.biricik.devs.business.abstracts.ProgrammingLanguageService;
import com.biricik.devs.business.requests.ProgrammingLanguageRequests.CreateProgrammingLanguageRequest;
import com.biricik.devs.business.requests.ProgrammingLanguageRequests.UpdateProgrammingLanguageRequest;
import com.biricik.devs.dao.abstracts.ProgrammingLanguageRepository;
import com.biricik.devs.entities.concretes.ProgrammingLanguage;
import com.fasterxml.jackson.databind.ObjectMapper;
import static org.hamcrest.Matchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class ProgrammingLanguagesControllerTest extends TestSupport {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ProgrammingLanguageService programmingLanguageService;

    @Autowired
    private ProgrammingLanguageRepository programmingLanguageRepository;

    private static final ObjectMapper ob = new ObjectMapper();

    @Nested
    @DisplayName("Create Methods")
    class Create {
        @Test
        public void testCreateProgrammingLanguageRequest_whenCreateProgrammingLanguageRequestExists_shouldReturnCreateProgrammingLanguageResponse()
                throws Exception {
            CreateProgrammingLanguageRequest request = generateCreateProgrammingLanguageRequest();

            mockMvc.perform(post("/api/programming-languages")
                    .contentType(MediaType.APPLICATION_JSON).content(ob.writer()
                            .withDefaultPrettyPrinter().writeValueAsString(request)))
                    .andExpect(jsonPath("$.data.name", is("Java")));
        }
    }

    @Nested
    @DisplayName("GetOne Methods")
    class GetOne {
        @Test
        public void testGetByIdProgrammingLanguage_whenProgrammingLanguageIdExists_shouldReturnGetByIdProgrammingLanguagesResponse()
                throws Exception {

            ProgrammingLanguage programmingLanguage = programmingLanguageRepository.save(generateProgrammingLanguage());

            mockMvc.perform(get("/api/programming-languages/" + programmingLanguage.getId()))
                    .andExpect(status().isOk())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                    .andExpect(jsonPath("$.data.id", is(programmingLanguage.getId())))
                    .andExpect(jsonPath("$.data", notNullValue()))
                    .andExpect(jsonPath("$.data.name", is(programmingLanguage.getName())));

        }

        @Test
        public void testGetByIdProgrammingLanguage_whenProgrammingLanguageIdDoesNotExists_shouldReturnErrorDataResultMessageIsProgrammingLanguageNotFound()
                throws Exception {

            mockMvc.perform(get("/api/programming-languages/" + "1232131321"))
                    .andExpect(status().isOk())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                    .andExpect(jsonPath("$.message", is("Programming Language Not Found")));

        }
    }

    @Nested
    @DisplayName("Update Methods")
    class Update {
        @Test
        public void testUpdateProgrammingLanguage_whenProgrammingLanguageIdExists_shouldReturnUpdateProgrammingLanguageResponse()
                throws Exception {

            ProgrammingLanguage programmingLanguage = programmingLanguageRepository.save(generateProgrammingLanguage());
            UpdateProgrammingLanguageRequest updateProgrammingLanguageRequest = generateUpdateProgrammingLanguageRequest();

            mockMvc.perform(put("/api/programming-languages/" + programmingLanguage.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(ob.writer().withDefaultPrettyPrinter()
                            .writeValueAsString(updateProgrammingLanguageRequest)))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.data.id", is(programmingLanguage.getId())))
                    .andExpect(jsonPath("$.data", notNullValue()))
                    .andExpect(jsonPath("$.data.name", is(updateProgrammingLanguageRequest.name())));

        }

        @Test
        public void testUpdateProgrammingLanguage_whenProgrammingLanguageIdDoesNotExists_shouldReturnErrorDataResultMessageIsProgrammingLanguageNotFound()
                throws Exception {
            UpdateProgrammingLanguageRequest updateProgrammingLanguageRequest = generateUpdateProgrammingLanguageRequest();
            
            mockMvc.perform(put("/api/programming-languages/" + "1232131321").contentType(MediaType.APPLICATION_JSON)
                    .content(ob.writer().withDefaultPrettyPrinter()
                            .writeValueAsString(updateProgrammingLanguageRequest)))
                    .andExpect(status().isOk())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                    .andExpect(jsonPath("$.message", is("Programming Language Not Found")));
        }
    }
}
