package com.biricik.devs.entities.elasticsearch;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import com.biricik.devs.entities.concretes.ProgrammingLanguage;
import com.biricik.devs.util.Indices;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Document(indexName = Indices.PROGRAMMINGLANGUAGE_INDEX)
@Builder
@Data
@ToString
public class ProgrammingLanguageEs {

    @Id
    @Field(name = "id", type = FieldType.Keyword)
    private int id;

    @Field(name = "name", type = FieldType.Text)
    private String name;

    @Field(type = FieldType.Nested, includeInParent = true)
    private List<ProgrammingLanguageTechnologieEs> programmingLanguageTechnologieEs;

    public static ProgrammingLanguageEs programmingLanguageConvertToProgrammingLanguageEs(final ProgrammingLanguage programmingLanguage) {
        return ProgrammingLanguageEs.builder()
                .id(programmingLanguage.getId())
                .name(programmingLanguage.getName())
                .programmingLanguageTechnologieEs(ProgrammingLanguageTechnologieEs
                        .programmingLanguageTechnologieConvertToProgrammingLanguageTechnologieEs(
                                programmingLanguage.getProgrammingLanguageTechnologies()))
                .build();
    }

    

}
