package com.biricik.devs.entities.concretes;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.*;

@Data
@AllArgsConstructor
@Entity
@Table(name = "programming_language_technologies")
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class ProgrammingLanguageTechnologie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "programming_language_id")
    private ProgrammingLanguage programmingLanguage;

    public ProgrammingLanguageTechnologie(String name, ProgrammingLanguage programmingLanguage) {
        this.name = name;
        this.programmingLanguage = programmingLanguage;
    }

    public ProgrammingLanguageTechnologie(String name) {
        this.name = name;
    }

    

}
