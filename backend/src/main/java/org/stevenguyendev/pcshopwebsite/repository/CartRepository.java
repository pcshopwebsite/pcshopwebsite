package org.stevenguyendev.pcshopwebsite.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.stevenguyendev.pcshopwebsite.model.Cart;

import java.util.UUID;

public interface CartRepository extends JpaRepository<Cart, UUID> {
     Cart findByUserId(UUID userId);

    Cart findByUserEmail(String userEmail);
}
