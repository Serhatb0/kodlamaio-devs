package com.biricik.devs.entities.concretes;

import java.util.List;

import javax.persistence.*;

import com.biricik.devs.entities.ParentEntity;

import lombok.*;

@Data
@AllArgsConstructor
@Entity
@Table(name = "words")
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ToString
public class Word extends ParentEntity {

	@Column(name = "`key`")
	private String key;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "word")
	private List<Translation> translations;

	public Word(String key) {
		this.key = key;
	}

}
