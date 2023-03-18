package com.biricik.devs.business.responses;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaginatedGenericResponse<T> {

	private List<T> content;
	private int pageNumber;
	private int pageSize;
	private long totalElements;
	private long totalPages;

	public PaginatedGenericResponse<T> convert(List<T> content, int pageNumber, int pageSize, long totalElements,
			long totalPages) {

		return new PaginatedGenericResponse<T>(content, pageNumber, pageSize, totalElements, totalPages);
	}


}
