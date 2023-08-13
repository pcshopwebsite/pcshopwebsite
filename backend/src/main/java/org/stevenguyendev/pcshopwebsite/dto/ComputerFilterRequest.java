package org.stevenguyendev.pcshopwebsite.dto;

import java.util.List;

public record ComputerFilterRequest(
        String name,
        List<String> categories,
        List<String> brands,
        Double minPrice,
        Double maxPrice,
        Float minRating,
        String sortBy,
        String sortOrder,
        int pageNumber,
        int pageSize
) {
}
