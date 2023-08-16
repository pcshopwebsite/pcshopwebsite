package org.stevenguyendev.pcshopwebsite.service;

import org.stevenguyendev.pcshopwebsite.dto.CartDTO;
import org.stevenguyendev.pcshopwebsite.dto.CartUpdateRequest;

import java.util.Collection;
import java.util.UUID;

public interface CartService {
    Collection<CartDTO> getAllCarts();
    CartDTO getCartForUser(UUID userId);
    CartDTO createCartForUser(UUID userId);
    CartDTO updateCart(CartUpdateRequest cartDTO);
}
