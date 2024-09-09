package org.stevenguyendev.pcshopwebsite.dto.mapper;

import org.springframework.stereotype.Service;
import org.stevenguyendev.pcshopwebsite.dto.CartItemDTO;
import org.stevenguyendev.pcshopwebsite.model.CartItem;

import java.util.function.Function;

@Service
public class CartItemDTOMapper implements Function<CartItem, CartItemDTO> {
    @Override
    public CartItemDTO apply(CartItem cartItem) {
        if (cartItem == null) {
            return null;
        }

        return new CartItemDTO(
                new ComputerLiteDTOMapper().apply(cartItem.getCartProductPK().getComputer()),
                cartItem.getQuantity()
        );
    }
}
