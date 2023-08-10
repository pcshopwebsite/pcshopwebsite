package org.stevenguyendev.pcshopwebsite.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.stevenguyendev.pcshopwebsite.model.CartItem;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    CartItem findCartItemByCartIdAndComputerId(Long cartId, Long computerId);
    CartItem findCartItemById(Long cartItemId);
    void deleteCartItemByCartIdAndComputerId(Long cartId, Long computerId);
}
