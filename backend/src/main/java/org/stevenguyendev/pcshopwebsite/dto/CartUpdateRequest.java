package org.stevenguyendev.pcshopwebsite.dto;

import java.util.UUID;

public record CartUpdateRequest(
        UUID cartId,
        UUID computerId,
        CartUpdateAction action,
        Integer quantity
) {
}
