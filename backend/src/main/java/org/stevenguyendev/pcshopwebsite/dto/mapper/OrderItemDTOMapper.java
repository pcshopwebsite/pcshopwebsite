package org.stevenguyendev.pcshopwebsite.dto.mapper;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.stevenguyendev.pcshopwebsite.dto.OrderItemDTO;
import org.stevenguyendev.pcshopwebsite.model.OrderItem;

import java.util.function.Function;

@Service
@AllArgsConstructor
public class OrderItemDTOMapper implements Function<OrderItem, OrderItemDTO> {

    private final ComputerDTOMapper computerDTOMapper;

    @Override
    public OrderItemDTO apply(OrderItem orderItem) {
        if (orderItem == null) return null;
        return new OrderItemDTO(
                computerDTOMapper.apply(orderItem.getOrderProduct().getProduct()),
                orderItem.getQuantity(),
                orderItem.getPrice()
        );
    }
}
