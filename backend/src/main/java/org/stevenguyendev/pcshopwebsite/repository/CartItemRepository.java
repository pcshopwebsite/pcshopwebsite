package org.stevenguyendev.pcshopwebsite.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.stevenguyendev.pcshopwebsite.model.CartItem;

import java.util.UUID;

public interface CartItemRepository extends JpaRepository<CartItem, CartItem.CartProductPK> {
    CartItem findByCartProductPK(CartItem.CartProductPK cartProductPK);
    CartItem findByCartProductPK_CartIdAndCartProductPK_ComputerId(UUID cartId, UUID computerId);
    void deleteByCartProductPK(CartItem.CartProductPK cartProductPK);
    void deleteByCartProductPK_CartIdAndCartProductPK_ComputerId(UUID cartId, UUID computerId);
}
