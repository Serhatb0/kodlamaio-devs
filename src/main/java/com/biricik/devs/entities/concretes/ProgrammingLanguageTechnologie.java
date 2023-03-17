package com.biricik.devs.entities.concretes;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.biricik.devs.entities.ParentEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@Entity
@Table(name = "programming_language_technologies")
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ToString
public class ProgrammingLanguageTechnologie extends ParentEntity {

	@Column(name = "technologie_name")
    private String technologieName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "programming_language_id")
    @JsonIgnore
    private ProgrammingLanguage programmingLanguage;

    public ProgrammingLanguageTechnologie(String technologieName) {
        this.technologieName = technologieName;
    }

}
