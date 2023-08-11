package org.stevenguyendev.pcshopwebsite.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.stevenguyendev.pcshopwebsite.dto.CartUpdateAction;
import org.stevenguyendev.pcshopwebsite.dto.CartUpdateRequest;
import org.stevenguyendev.pcshopwebsite.model.Computer;
import org.stevenguyendev.pcshopwebsite.repository.ComputerRepository;
import org.stevenguyendev.pcshopwebsite.exception.ResourceNotFoundException;
import org.stevenguyendev.pcshopwebsite.model.Cart;
import org.stevenguyendev.pcshopwebsite.model.CartItem;
import org.stevenguyendev.pcshopwebsite.model.User;
import org.stevenguyendev.pcshopwebsite.repository.CartItemRepository;
import org.stevenguyendev.pcshopwebsite.repository.CartRepository;
import org.stevenguyendev.pcshopwebsite.repository.UserRepository;

import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class CartService {
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final UserRepository userRepository;
    private final ComputerRepository computerRepository;
    public CartService(
            CartRepository cartRepository,
            UserRepository userRepository,
            CartItemRepository cartItemRepository,
            ComputerRepository computerRepository) {
        this.cartRepository = cartRepository;
        this.userRepository = userRepository;
        this.cartItemRepository = cartItemRepository;
        this.computerRepository = computerRepository;
    }
    public Cart getCartByUserId(Long userId) {
        return cartRepository.findByUserId(userId);
    }
    public Cart getCartByUserEmail(String userEmail) {
        return cartRepository.findByUserEmail(userEmail);
    }

    public Cart createCart(String userEmail){
        Optional<User> uO = userRepository.findByEmail(userEmail);
        if (uO.isEmpty()) {
            throw new ResourceNotFoundException("User not found for email " + userEmail);
        }
        User user = uO.get();
        Cart cartEntity = new Cart();
        cartEntity.setUser(user);
        return cartRepository.save(cartEntity);
    }

    public Cart updateCart(
            CartUpdateRequest updateRequest
    ) {
        Cart cartEntity = getCart(updateRequest.cartId());
        if (updateRequest.action() == CartUpdateAction.UPDATE) {
            CartItem cartItem = cartItemRepository.findCartItemByCartIdAndComputerId(
                    updateRequest.cartId(),
                    updateRequest.computerId()
            );
            if (cartItem == null) {
                throw new ResourceNotFoundException("Cart item not found for cart id " + updateRequest.cartId() + " and computer id " + updateRequest.computerId());
            }
            cartItem.setQuantity(updateRequest.quantity());
            cartItemRepository.save(cartItem);
        } else if (updateRequest.action() == CartUpdateAction.REMOVE) {
            cartItemRepository.deleteCartItemByCartIdAndComputerId(
                    updateRequest.cartId(),
                    updateRequest.computerId()
            );
        } else if (updateRequest.action() == CartUpdateAction.ADD) {
            CartItem cartItem = cartItemRepository.findCartItemByCartIdAndComputerId(
                    updateRequest.cartId(),
                    updateRequest.computerId()
            );
            if (cartItem == null) {
                cartItem = new CartItem();
                cartItem.setCart(cartEntity);
                Optional<Computer> computerEntity = computerRepository.findById(updateRequest.computerId());
                if (computerEntity.isEmpty()) {
                    throw new ResourceNotFoundException("Computer not found for id " + updateRequest.computerId());
                }
                cartItem.setComputer(computerEntity.get());
                cartItem.setQuantity(updateRequest.quantity());
                cartItemRepository.save(cartItem);
            } else {
                cartItem.setQuantity(cartItem.getQuantity() + updateRequest.quantity());
                cartItemRepository.save(cartItem);
            }
        }
        return cartRepository.save(cartEntity);
    }

    public Cart getCart(UUID cartId) {
        Optional<Cart> cartEntity = cartRepository.findById(cartId);
        if (cartEntity.isEmpty()) {
            throw new ResourceNotFoundException("Cart not found for id " + cartId);
        }
        return cartEntity.get();
    }
}
