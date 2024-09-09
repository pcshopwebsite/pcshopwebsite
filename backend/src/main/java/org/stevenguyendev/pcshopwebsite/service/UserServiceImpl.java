package org.stevenguyendev.pcshopwebsite.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.stevenguyendev.pcshopwebsite.dto.UserDTO;
import org.stevenguyendev.pcshopwebsite.dto.mapper.UserDTOMapper;
import org.stevenguyendev.pcshopwebsite.exception.ResourceNotFoundException;
import org.stevenguyendev.pcshopwebsite.model.Cart;
import org.stevenguyendev.pcshopwebsite.model.User;
import org.stevenguyendev.pcshopwebsite.repository.CartRepository;
import org.stevenguyendev.pcshopwebsite.repository.UserRepository;

import java.util.Collection;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final CartRepository cartRepository;

    private final UserDTOMapper userDTOMapper;

    public Collection<UserDTO> getAllUsers() {
        return userRepository.findAll().stream().map(userDTOMapper).collect(Collectors.toList());
    }

    public UserDTO addUser(UserDTO userDTO) {
        User user = User.builder()
                .name(userDTO.name())
                .email(userDTO.email())
                .password(userDTO.password())
                .build();
        user = userRepository.save(user);
        Cart newCart = new Cart();
        newCart.setUser(user);
        cartRepository.save(newCart);
        return userDTOMapper.apply(user);
    }

    public UserDTO updateUser(UserDTO userDTO) {
        User user = userRepository.findById(userDTO.id())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        user.setName(userDTO.name());
        user.setEmail(userDTO.email());
        return userDTOMapper.apply(userRepository.save(user));
    }

    public void deleteUser(UUID userId) {
        cartRepository.deleteById(userId);
        userRepository.deleteById(userId);
    }

    @Override
    public User getCurrentUser() throws ResourceNotFoundException {
        return userRepository.findByEmail("johndoe@gmail.com").orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }

    public UserDTO getUser(UUID userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        return userDTOMapper.apply(user);
    }
}
