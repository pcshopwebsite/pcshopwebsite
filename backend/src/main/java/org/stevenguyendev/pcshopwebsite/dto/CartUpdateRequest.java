package org.stevenguyendev.pcshopwebsite.dto;

public record CartUpdateRequest(
        Long cartId,
        Long computerId,
        CartUpdateAction action,
        Integer quantity
) {
}
