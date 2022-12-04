package com.biricik.devs.entities.elasticsearch;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import com.biricik.devs.entities.concretes.ProgrammingLanguageTechnologie;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProgrammingLanguageTechnologieEs {

    @Id
    @Field(name = "id", type = FieldType.Keyword)
    private int id;

    @Field(name = "name", type = FieldType.Text)
    private String name;

    public static List<ProgrammingLanguageTechnologieEs> programmingLanguageTechnologieConvertToProgrammingLanguageTechnologieEs(
            final List<ProgrammingLanguageTechnologie> programmingLanguageTechnologie) {
        if (programmingLanguageTechnologie == null) {
            return null;
        }
        return programmingLanguageTechnologie.stream().map(item -> {
            return ProgrammingLanguageTechnologieEs.builder()
                    .id(item.getId())
                    .name(item.getName())
                    .build();

        }).collect(Collectors.toList());

    }

}
