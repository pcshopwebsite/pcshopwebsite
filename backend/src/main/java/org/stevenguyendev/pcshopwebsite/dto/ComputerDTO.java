package org.stevenguyendev.pcshopwebsite.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

public record ComputerDTO(
        UUID id,
        String name,
        String description,
        BigDecimal price,
        Float rating,
        String thumbnail,
        CategoryDTO category,
        BrandDTO brand,
        String specification,
        Set<MediaDTO> medias,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        String createdBy,
        String updatedBy

) {
}
