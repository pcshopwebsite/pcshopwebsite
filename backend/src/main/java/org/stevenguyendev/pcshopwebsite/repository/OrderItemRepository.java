package org.stevenguyendev.pcshopwebsite.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.stevenguyendev.pcshopwebsite.model.OrderItem;

import java.util.UUID;

public interface OrderItemRepository extends JpaRepository<OrderItem, UUID> {
}
