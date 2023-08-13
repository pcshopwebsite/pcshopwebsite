package org.stevenguyendev.pcshopwebsite.dto;

import java.util.Set;
import java.util.UUID;

public record CartDTO(

        UUID id,
        Set<CartItemDTO> cartItems
) {
}
