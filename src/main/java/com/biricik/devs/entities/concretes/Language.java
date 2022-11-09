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

import lombok.*;


@Data
@AllArgsConstructor
@Entity
@Table(name = "languages")
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class Language {
    
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "name")
	private String name;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "language")
	private List<Translation> translations;

	public Language(String name) {
		this.name = name;
	}

	
}
