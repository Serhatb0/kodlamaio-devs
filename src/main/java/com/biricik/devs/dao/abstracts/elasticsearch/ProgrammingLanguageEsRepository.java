package com.biricik.devs.dao.abstracts.elasticsearch;

import java.util.List;

import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.biricik.devs.entities.elasticsearch.ProgrammingLanguageEs;

public interface ProgrammingLanguageEsRepository extends ElasticsearchRepository<ProgrammingLanguageEs, Integer> {

    @Query("{\"bool\": {\"must\": [{\"match\": {\"name\": \"?0\"}}]}}")
    List<ProgrammingLanguageEs> findByProgrammingLanguageName(String name);

    @Query(" {" +
            " \"bool\": {\n" +
            "     \"should\": [\n" +
            "       {\n" +
            "         \"multi_match\": {\n" +
            "           \"query\": \"?0\",\n" +
            "           \"fields\": [\"name\"]\n" +
            "         }\n" +
            "       },\n" +
            "       {\n" +
            "         \"nested\": {\n" +
            "           \"path\": \"programmingLanguageTechnologieEs\",\n" +
            "           \"query\": {\n" +
            "             \"match\": {\n" +
            "               \"programmingLanguageTechnologieEs.name\": \"?0\"\n" +
            "             }}\n" +
            "         }\n" +
            "       } ]\n" +
            "  }" +
            "}")
    List<ProgrammingLanguageEs> findByProgrammingLanguageNameOrProgrammingLanguageTechnologieName(String name);

}
