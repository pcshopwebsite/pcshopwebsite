package org.stevenguyendev.pcshopwebsite.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record ComputerLiteDTO(
        UUID id,
        String name,
        String description,
        BigDecimal price,
        Float rating,
        String thumbnail,
        String brand,
        String category,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
