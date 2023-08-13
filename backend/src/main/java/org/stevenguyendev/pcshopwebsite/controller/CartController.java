package org.stevenguyendev.pcshopwebsite.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.stevenguyendev.pcshopwebsite.dto.CartDTO;
import org.stevenguyendev.pcshopwebsite.dto.mapper.CartDTOMapper;
import org.stevenguyendev.pcshopwebsite.service.CartService;
import org.stevenguyendev.pcshopwebsite.dto.CartUpdateRequest;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/carts")
public class CartController {

    private final CartService cartService;
    private final CartDTOMapper cartDTOMapper;
    public CartController(
            CartService cartService,
            CartDTOMapper cartDTOMapper) {
        this.cartService = cartService;
        this.cartDTOMapper = cartDTOMapper;
    }
    @GetMapping("/{cartId}")
    public ResponseEntity<CartDTO> getCart(@PathVariable UUID cartId)
    {
        return ResponseEntity.ok(cartDTOMapper.apply(cartService.getCart(cartId)));
    }

    @PutMapping
    public ResponseEntity<CartDTO> updateCart(@RequestBody CartUpdateRequest updateRequest)
    {
        return ResponseEntity.ok(cartDTOMapper.apply(cartService.updateCart(updateRequest)));
    }
}
