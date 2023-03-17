package com.biricik.devs.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import com.biricik.devs.util.MethodUtils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
@Data
public class ParentEntity {
    
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@CreatedDate
	@Column(name = "created_date")
	private Date createdDate = MethodUtils.getCurrentTimeStamp();

	@UpdateTimestamp
	@Column(name = "updated_date")
	private Date updatedDate;

    
	@LastModifiedDate
	@Column(name = "last_modified_date")
	private Date lastModifiedDate = MethodUtils.getCurrentTimeStamp();


	public ParentEntity(int id) {
		this.id = id;
	}

	
}
