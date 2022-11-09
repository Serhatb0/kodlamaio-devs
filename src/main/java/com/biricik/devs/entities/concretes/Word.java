package com.biricik.devs.entities.concretes;

import java.util.List;

import javax.persistence.*;

import lombok.*;

@Data
@AllArgsConstructor
@Entity
@Table(name = "words")
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class Word {
    

    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "key")
	private String key;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "word")
	private List<Translation> translations;

	public Word(String key) {
		this.key = key;
	}

	
}
