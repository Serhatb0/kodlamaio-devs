package com.biricik.devs.entities.concretes;

import javax.persistence.Column;
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
@Table(name = "translations")
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ToString
public class Translation extends ParentEntity {

	@Column(name = "translation")
	private String translation;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "word_id")
	private Word word;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "language_id")
	private Language language;

	public Translation(int id, String translation, Word word, Language language) {
		super(id);
		this.translation = translation;
		this.word = word;
		this.language = language;
	}

}
