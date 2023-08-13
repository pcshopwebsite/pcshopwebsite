package org.stevenguyendev.pcshopwebsite.dto;

import java.util.UUID;

public record CartItemDTO(
        UUID id,
        ComputerLiteDTO computer,
        Integer quantity
) {
}
