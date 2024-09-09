package org.stevenguyendev.pcshopwebsite.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.stevenguyendev.pcshopwebsite.model.Cart;

import java.util.Optional;
import java.util.UUID;

public interface CartRepository extends JpaRepository<Cart, UUID> {
     Optional<Cart> findByUserId(UUID userId);

    Optional<Cart> findByUserEmail(String userEmail);
}
