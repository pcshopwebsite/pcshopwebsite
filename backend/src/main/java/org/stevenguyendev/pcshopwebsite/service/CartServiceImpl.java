package org.stevenguyendev.pcshopwebsite.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.stevenguyendev.pcshopwebsite.dto.CartDTO;
import org.stevenguyendev.pcshopwebsite.dto.CartUpdateAction;
import org.stevenguyendev.pcshopwebsite.dto.CartUpdateRequest;
import org.stevenguyendev.pcshopwebsite.dto.mapper.CartDTOMapper;
import org.stevenguyendev.pcshopwebsite.model.Computer;
import org.stevenguyendev.pcshopwebsite.repository.ComputerRepository;
import org.stevenguyendev.pcshopwebsite.exception.ResourceNotFoundException;
import org.stevenguyendev.pcshopwebsite.model.Cart;
import org.stevenguyendev.pcshopwebsite.model.CartItem;
import org.stevenguyendev.pcshopwebsite.model.User;
import org.stevenguyendev.pcshopwebsite.repository.CartItemRepository;
import org.stevenguyendev.pcshopwebsite.repository.CartRepository;
import org.stevenguyendev.pcshopwebsite.repository.UserRepository;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
public class CartServiceImpl implements CartService {
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final UserRepository userRepository;
    private final ComputerRepository computerRepository;
    private final CartDTOMapper cartDTOMapper;
    public CartServiceImpl(
            CartRepository cartRepository,
            UserRepository userRepository,
            CartItemRepository cartItemRepository,
            ComputerRepository computerRepository,
            CartDTOMapper cartDTOMapper) {
        this.cartRepository = cartRepository;
        this.userRepository = userRepository;
        this.cartItemRepository = cartItemRepository;
        this.computerRepository = computerRepository;
        this.cartDTOMapper = cartDTOMapper;
    }

    public Collection<CartDTO> getAllCarts() {
        return cartRepository.findAll().stream().map(cartDTOMapper).collect(Collectors.toList());
    }
    public CartDTO getCartForUser(UUID userId) {
        return cartDTOMapper.apply(cartRepository.findByUserId(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Cart not found for user id " + userId)));
    }

    public CartDTO createCartForUser(UUID userId){
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found for id " + userId));
        Cart cartEntity = new Cart();
        cartEntity.setUser(user);
        return cartDTOMapper.apply(cartRepository.save(cartEntity));
    }

    public CartDTO updateCart(
            CartUpdateRequest updateRequest
    ) {
        Cart cartEntity = getCart(updateRequest.cartId());
        if (updateRequest.action() == CartUpdateAction.UPDATE) {
            CartItem cartItem = cartItemRepository.findByCartProductPK_CartIdAndCartProductPK_ComputerId(
                    updateRequest.cartId(),
                    updateRequest.computerId()
            );
            if (cartItem == null) {
                throw new ResourceNotFoundException("Cart item not found for cart id " + updateRequest.cartId() + " and computer id " + updateRequest.computerId());
            }
            cartItem.setQuantity(updateRequest.quantity());
            cartItemRepository.save(cartItem);
        } else if (updateRequest.action() == CartUpdateAction.REMOVE) {
            cartItemRepository.deleteByCartProductPK_CartIdAndCartProductPK_ComputerId(
                    updateRequest.cartId(),
                    updateRequest.computerId()
            );
        } else if (updateRequest.action() == CartUpdateAction.ADD) {
            CartItem cartItem = cartItemRepository.findByCartProductPK_CartIdAndCartProductPK_ComputerId(
                    updateRequest.cartId(),
                    updateRequest.computerId()
            );
            if (cartItem == null) {
                cartItem = new CartItem();
                cartItem.getCartProductPK().setCart(cartEntity);
                Optional<Computer> computerEntity = computerRepository.findById(updateRequest.computerId());
                if (computerEntity.isEmpty()) {
                    throw new ResourceNotFoundException("Computer not found for id " + updateRequest.computerId());
                }
                cartItem.getCartProductPK().setComputer(computerEntity.get());
                cartItem.setQuantity(updateRequest.quantity());
                cartItemRepository.save(cartItem);
            } else {
                cartItem.setQuantity(cartItem.getQuantity() + updateRequest.quantity());
                cartItemRepository.save(cartItem);
            }
        }
        return cartDTOMapper.apply(cartRepository.save(cartEntity));
    }

    public Cart getCart(UUID cartId) {
        Optional<Cart> cartEntity = cartRepository.findById(cartId);
        if (cartEntity.isEmpty()) {
            throw new ResourceNotFoundException("Cart not found for id " + cartId);
        }
        return cartEntity.get();
    }
}
