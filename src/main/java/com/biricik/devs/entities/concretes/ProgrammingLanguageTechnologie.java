package com.biricik.devs.entities.concretes;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.biricik.devs.entities.ParentEntity;

import lombok.*;

@Data
@AllArgsConstructor
@Entity
@Table(name = "programming_language_technologies")
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ToString
public class ProgrammingLanguageTechnologie extends ParentEntity {

    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "programming_language_id")
    private ProgrammingLanguage programmingLanguage;

    public ProgrammingLanguageTechnologie(String name) {
        this.name = name;
    }

}
