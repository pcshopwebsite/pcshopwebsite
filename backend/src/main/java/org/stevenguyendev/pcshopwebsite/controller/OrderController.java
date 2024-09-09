package org.stevenguyendev.pcshopwebsite.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.stevenguyendev.pcshopwebsite.dto.OrderDTO;
import org.stevenguyendev.pcshopwebsite.dto.OrderItemDTO;
import org.stevenguyendev.pcshopwebsite.service.OrderServiceImpl;

import java.math.BigDecimal;
import java.util.Collection;

@RestController
@RequestMapping("/api/v1/orders")
@AllArgsConstructor
public class OrderController extends BaseController {

    private final OrderServiceImpl orderService;

    @GetMapping
    public Collection<OrderDTO> getAllOrders() {
        return orderService.getAllOrders();
    }

    @PostMapping
    public ResponseEntity<OrderDTO> placeOrder(@RequestBody OrderDTO orderDto) {
        return ResponseEntity.ok(orderService.placeOrder(orderDto));
    }

    @PutMapping
    public ResponseEntity<OrderDTO> updateOrder(OrderDTO orderDto) {
        return ResponseEntity.ok(orderService.updateOrder(orderDto));
    }

    @PostMapping("/shipping-cost")
    public ResponseEntity<BigDecimal> calculateShippingCost(@RequestBody OrderItemDTO orderItemDTO) {
        return ResponseEntity.ok(orderService.getShippingCost(orderItemDTO));
    }
}
