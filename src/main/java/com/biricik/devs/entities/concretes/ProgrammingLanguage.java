
package com.biricik.devs.entities.concretes;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@Entity
@Table(name="programming_languages")
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class ProgrammingLanguage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;


    @OneToMany(fetch = FetchType.LAZY,mappedBy = "programmingLanguage")
    List<ProgrammingLanguageTechnologie> programmingLanguageTechnologies;


    
    public ProgrammingLanguage(int id, String name) {
        this.id = id;
        this.name = name;
    }



    public ProgrammingLanguage(String name) {
        this.name = name;
    }

    

}
