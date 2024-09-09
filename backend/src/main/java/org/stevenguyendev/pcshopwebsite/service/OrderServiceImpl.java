package org.stevenguyendev.pcshopwebsite.service;


import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.stevenguyendev.pcshopwebsite.dto.OrderDTO;
import org.stevenguyendev.pcshopwebsite.dto.OrderItemDTO;
import org.stevenguyendev.pcshopwebsite.dto.mapper.OrderDTOMapper;
import org.stevenguyendev.pcshopwebsite.dto.mapper.OrderItemDTOMapper;
import org.stevenguyendev.pcshopwebsite.exception.ResourceNotFoundException;
import org.stevenguyendev.pcshopwebsite.model.Order;
import org.stevenguyendev.pcshopwebsite.model.OrderItem;
import org.stevenguyendev.pcshopwebsite.model.OrderStatus;
import org.stevenguyendev.pcshopwebsite.model.User;
import org.stevenguyendev.pcshopwebsite.repository.ComputerRepository;
import org.stevenguyendev.pcshopwebsite.repository.OrderItemRepository;
import org.stevenguyendev.pcshopwebsite.repository.OrderRepository;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;

    private final OrderDTOMapper orderDTOMapper;
    private final OrderItemDTOMapper orderItemDTOMapper;

    private final ComputerRepository computerRepository;

    private final UserService userService;

    @Override
    public OrderDTO placeOrder(OrderDTO orderDto) {
        // TODO refine - findbyreference
        Order order = Order.builder()
                .orderStatus(OrderStatus.Pending)
                .user(userService.getCurrentUser())
                .additionalNote(orderDto.additionalNote())
                .receiverName(orderDto.receiverName())
                .receiverPhone(orderDto.receiverPhone())
                .shippingAddress(orderDto.shippingAddress())
                .shippingCity(orderDto.shippingCity())
                .shippingPostalCode(orderDto.shippingPostalCode())
                .totalAmount(orderDto.totalAmount())
                .build();
        Set<OrderItem> orderItems = orderDto.orderItems().stream().map((orderItemDto) -> {
            var product = computerRepository.findComputerById(orderItemDto.product().id()).orElseThrow(() -> new ResourceNotFoundException("Computer not found with id: " + orderItemDto.product().id()));
            return new OrderItem(
                    order,
                    product,
                    orderItemDto.quantity(),
                    orderItemDto.price()
            );
        }).collect(Collectors.toSet());
        Order newOrder = orderRepository.save(order);
        order.setOrderItems(orderItems);
        return orderDTOMapper.apply(orderRepository.findById(newOrder.getId()).orElseThrow());
    }

    @Override
    public OrderDTO getOrder(UUID id) {
        return orderDTOMapper.apply(orderRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Order not found with id: " + id)));
    }

    @Override
    public OrderDTO updateOrder(OrderDTO orderDto) {
        Order order = orderRepository.findById(orderDto.id()).orElseThrow(() -> new ResourceNotFoundException("Order not found with id: " + orderDto.id()));
        order.setOrderStatus(orderDto.orderStatus());
        return orderDTOMapper.apply(orderRepository.save(order));
    }

    @Override
    public Collection<OrderDTO> getAllOrders() {
        return orderRepository.findAll().stream().map(orderDTOMapper).collect(Collectors.toList());
    }

    @Override
    public Collection<OrderDTO> getAllOrdersForUser(UUID userId) {
        User user = userService.getCurrentUser();
        return orderRepository.findAllByUser(user).stream().map(orderDTOMapper).collect(Collectors.toList());
    }

    @Override
    public BigDecimal getShippingCost(OrderItemDTO orderItemDTO) {
        return new BigDecimal(0.00);
    }
}
