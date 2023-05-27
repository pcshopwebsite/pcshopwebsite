package org.pcshopwebsite.product;

import java.math.BigDecimal;
import java.util.Date;

public record ProductDTO(
        int id,
        String name,
        BigDecimal price,
        String description,
        String thumbnail,
        Float rating,
        Date createdAt,
        Date updatedAt
) {
}
