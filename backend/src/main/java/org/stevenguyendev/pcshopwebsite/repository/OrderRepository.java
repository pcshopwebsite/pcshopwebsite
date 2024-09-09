package org.stevenguyendev.pcshopwebsite.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.stevenguyendev.pcshopwebsite.model.Order;
import org.stevenguyendev.pcshopwebsite.model.User;

import java.util.Collection;
import java.util.UUID;

public interface OrderRepository extends JpaRepository<Order, UUID> {
    Collection<Order> findAllByUser(User user);
}
