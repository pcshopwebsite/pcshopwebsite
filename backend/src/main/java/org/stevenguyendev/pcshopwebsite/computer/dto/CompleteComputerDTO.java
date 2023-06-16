package org.stevenguyendev.pcshopwebsite.computer.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record CompleteComputerDTO(
        String name,
        BigDecimal price,
        String description,
        String thumbnail,
        Float rating,
        BrandDTO brand,
        CategoryDTO category,
        String specification,
        List<MediaDTO> mediaList,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
