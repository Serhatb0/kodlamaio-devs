package com.biricik.devs.entities.concretes;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.biricik.devs.entities.ParentEntity;

import lombok.*;


@Data
@AllArgsConstructor
@Entity
@Table(name = "languages")
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ToString
public class Language extends ParentEntity {
    

	@Column(name = "name")
	private String name;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "language")
	private List<Translation> translations;

	public Language(String name) {
		this.name = name;
	}

	

	
}
