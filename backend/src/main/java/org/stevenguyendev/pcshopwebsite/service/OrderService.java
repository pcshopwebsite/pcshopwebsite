package org.stevenguyendev.pcshopwebsite.service;

import org.stevenguyendev.pcshopwebsite.dto.OrderDTO;
import org.stevenguyendev.pcshopwebsite.dto.OrderItemDTO;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.UUID;

public interface OrderService {
    OrderDTO placeOrder(OrderDTO orderDto);

    OrderDTO getOrder(UUID id);

    OrderDTO updateOrder(OrderDTO orderDto);

    Collection<OrderDTO> getAllOrders();

    Collection<OrderDTO> getAllOrdersForUser(UUID userId);

    BigDecimal getShippingCost(OrderItemDTO orderItemDTO);
}
