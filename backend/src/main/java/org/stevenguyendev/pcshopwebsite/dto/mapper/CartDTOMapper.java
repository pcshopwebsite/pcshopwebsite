package org.stevenguyendev.pcshopwebsite.dto.mapper;

import org.springframework.stereotype.Service;
import org.stevenguyendev.pcshopwebsite.dto.CartDTO;
import org.stevenguyendev.pcshopwebsite.model.Cart;

import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class CartDTOMapper implements Function<Cart, CartDTO> {
    private final CartItemDTOMapper cartItemDTOMapper;
    public CartDTOMapper(CartItemDTOMapper cartItemDTOMapper) {
        this.cartItemDTOMapper = cartItemDTOMapper;
    }

    @Override
    public CartDTO apply(Cart cart) {
        if (cart == null) {
            return null;
        }
        return new CartDTO(
                cart.getId(),
                cart.getCartItems().stream().map(cartItemDTOMapper).collect(Collectors.toSet())
        );
    }
}

