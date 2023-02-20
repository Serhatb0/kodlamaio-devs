package com.biricik.devs.business.requests;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PaginatedRequest {
	
	private int size;
	private int page;

    private final static int DEFAULT_SİZE = 20;

    public PaginatedRequest(int size, int page) {
        this.size  = size <= 0 ? DEFAULT_SİZE : size;
        this.page = page;
    }
}
