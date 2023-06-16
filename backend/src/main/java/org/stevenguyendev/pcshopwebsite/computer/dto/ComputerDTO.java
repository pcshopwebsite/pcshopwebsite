package org.stevenguyendev.pcshopwebsite.computer.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record ComputerDTO(
        String name,
        BigDecimal price,
        String description,
        String thumbnail,
        Float rating,
        BrandDTO brand,
        CategoryDTO category,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
