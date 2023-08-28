package org.stevenguyendev.pcshopwebsite.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.stevenguyendev.pcshopwebsite.dto.CartDTO;
import org.stevenguyendev.pcshopwebsite.dto.mapper.CartDTOMapper;
import org.stevenguyendev.pcshopwebsite.service.CartServiceImpl;
import org.stevenguyendev.pcshopwebsite.dto.CartUpdateRequest;

import java.util.Collection;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/carts")
@CrossOrigin(origins = "http://localhost:4200")
public class CartController extends BaseController {

    private final CartServiceImpl cartService;
    public CartController(
            CartServiceImpl cartService) {
        this.cartService = cartService;
    }

    @GetMapping
    public ResponseEntity<Collection<CartDTO>> getAllCarts() {
        return ResponseEntity.ok(cartService.getAllCarts());
    }

    @GetMapping("/{cartId}")
    public ResponseEntity<CartDTO> getCart(@PathVariable UUID cartId)
    {
        return ResponseEntity.ok(cartService.getCartForUser(cartId));
    }

    @PutMapping
    public ResponseEntity<CartDTO> updateCart(@RequestBody CartUpdateRequest updateRequest)
    {
        return ResponseEntity.ok(cartService.updateCart(updateRequest));
    }
}
