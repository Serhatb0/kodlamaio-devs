package com.biricik.devs.business.responses;

import java.util.List;

public record PaginatedGenericResponse<T>(List<T> content, int pageNumber,
                int pageSize, long totalElements, long totalPages) {

        public PaginatedGenericResponse<T> convert(List<T> content,int pageNumber,
                        int pageSize, long totalElements, long totalPages) {

                return new PaginatedGenericResponse<T>(content, pageNumber, pageSize, totalElements, totalPages);
        }

}
