package org.stevenguyendev.pcshopwebsite.service;

import org.springframework.stereotype.Service;
import org.stevenguyendev.pcshopwebsite.exception.ResourceNotFoundException;
import org.stevenguyendev.pcshopwebsite.model.Cart;
import org.stevenguyendev.pcshopwebsite.model.User;
import org.stevenguyendev.pcshopwebsite.repository.CartRepository;
import org.stevenguyendev.pcshopwebsite.repository.UserRepository;

import java.util.Collection;
import java.util.UUID;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final CartRepository cartRepository;

    public UserService(UserRepository userRepository, CartRepository cartRepository) {
        this.userRepository = userRepository;
        this.cartRepository = cartRepository;
    }

    public Collection<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User addUser(User user) {
        User registeredUser = userRepository.save(user);
        Cart newCart = new Cart();
        newCart.setUser(registeredUser);
        cartRepository.save(newCart);
        return registeredUser;
    }

    public User updateUser(User user) {
        return userRepository.save(user);
    }

    public void deleteUser(UUID userId) {
        userRepository.deleteById(userId);
    }

    public User getUser(UUID userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }
}
