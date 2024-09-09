package org.stevenguyendev.pcshopwebsite.dto;

import java.math.BigDecimal;

public record OrderItemDTO(
        ComputerDTO product,
        Integer quantity,
        BigDecimal price
) {
}
