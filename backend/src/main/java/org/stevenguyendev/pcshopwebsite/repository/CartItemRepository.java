package org.stevenguyendev.pcshopwebsite.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.stevenguyendev.pcshopwebsite.model.CartItem;

import java.util.UUID;

public interface CartItemRepository extends JpaRepository<CartItem, UUID> {
    CartItem findCartItemByCartIdAndComputerId(UUID cartId, UUID computerId);
    CartItem findCartItemById(UUID cartItemId);
    void deleteCartItemByCartIdAndComputerId(UUID cartId, UUID computerId);
}
