package org.stevenguyendev.pcshopwebsite.dto.mapper;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.stevenguyendev.pcshopwebsite.dto.OrderDTO;
import org.stevenguyendev.pcshopwebsite.model.Order;

import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class OrderDTOMapper implements Function<Order, OrderDTO> {

    private final OrderItemDTOMapper orderItemDTOMapper;

    @Override
    public OrderDTO apply(Order order) {
        if(order == null) return null;
        return new OrderDTO(
                order.getId(),
                order.getUser().getId(),
                order.getShippingAddress(),
                order.getShippingCity(),
                order.getShippingPostalCode(),
                order.getReceiverName(),
                order.getReceiverPhone(),
                order.getAdditionalNote(),
                order.getTotalAmount(),
                order.getOrderStatus(),
//                order.getCreatedAt().toString(),
//                order.getUpdatedAt().toString(),
                null,
                null,
                order.getCreatedBy(),
                order.getUpdatedBy(),
                order.getOrderItems().stream().map(orderItemDTOMapper).collect(Collectors.toSet())
        );
    }
}
