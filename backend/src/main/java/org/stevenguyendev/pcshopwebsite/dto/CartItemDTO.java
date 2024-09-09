package org.stevenguyendev.pcshopwebsite.dto;

import java.util.UUID;

public record CartItemDTO(
        ComputerLiteDTO computer,
        Integer quantity
) {
}
