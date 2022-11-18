
package com.biricik.devs.entities.concretes;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.biricik.devs.entities.ParentEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@Entity
@Table(name = "programming_languages")
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ToString
public class ProgrammingLanguage extends ParentEntity {

    @Column(name = "name")
    private String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "programmingLanguage")
    List<ProgrammingLanguageTechnologie> programmingLanguageTechnologies;

    public ProgrammingLanguage(int id, String name) {
        super(id);
        this.name = name;
    }

    public ProgrammingLanguage(String name) {
        this.name = name;
    }

    public ProgrammingLanguage(int id, String name,
            List<ProgrammingLanguageTechnologie> programmingLanguageTechnologies) {
        super(id);
        this.name = name;
        this.programmingLanguageTechnologies = programmingLanguageTechnologies;
    }

    

}
