package org.stevenguyendev.pcshopwebsite.dto;

import org.stevenguyendev.pcshopwebsite.model.OrderStatus;

import java.math.BigDecimal;
import java.util.Set;
import java.util.UUID;

public record OrderDTO(
        UUID id,
        UUID userId,
        String shippingAddress,
        String shippingCity,
        String shippingPostalCode,
        String receiverName,
        String receiverPhone,
        String additionalNote,
        BigDecimal totalAmount,
        OrderStatus orderStatus,
        String createdAt,
        String updatedAt,
        String createdBy,
        String updatedBy,
        Set<OrderItemDTO> orderItems
) {
}
