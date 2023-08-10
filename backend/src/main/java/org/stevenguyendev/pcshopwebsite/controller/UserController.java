package org.stevenguyendev.pcshopwebsite.controller;

import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;
import org.stevenguyendev.pcshopwebsite.model.User;
import org.stevenguyendev.pcshopwebsite.dto.UserDTO;
import org.stevenguyendev.pcshopwebsite.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;
    private final ModelMapper modelMapper;
    public UserController(
            UserService userService,
            ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }
    @GetMapping
    public List<UserDTO> getAllUsers() {
        return this.userService.getAllUsers().stream()
                .map(userEntity -> this.modelMapper.map(userEntity, UserDTO.class))
                .toList();
    }

    @PostMapping
    public UserDTO addUser(UserDTO userDTO) {
        User user = this.modelMapper.map(userDTO, User.class);
        return this.modelMapper.map(this.userService.addUser(user), UserDTO.class);
    }

    @PutMapping
    public UserDTO updateUser(UserDTO userDTO) {
        User user = this.modelMapper.map(userDTO, User.class);
        return this.modelMapper.map(this.userService.updateUser(user), UserDTO.class);
    }

    @DeleteMapping("/{userId}")
    public void deleteUser(Long userId) {
        this.userService.deleteUser(userId);
    }
}
