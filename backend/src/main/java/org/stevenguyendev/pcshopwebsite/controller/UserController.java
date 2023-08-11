package org.stevenguyendev.pcshopwebsite.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.stevenguyendev.pcshopwebsite.dto.request.UserRegistrationRequest;
import org.stevenguyendev.pcshopwebsite.dto.mapper.UserDTOMapper;
import org.stevenguyendev.pcshopwebsite.dto.request.UserUpdateRequest;
import org.stevenguyendev.pcshopwebsite.model.User;
import org.stevenguyendev.pcshopwebsite.dto.UserDTO;
import org.stevenguyendev.pcshopwebsite.service.UserService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    private final UserService userService;

    private final UserDTOMapper userDTOMapper;
    public UserController(
            UserService userService,
            UserDTOMapper userDTOMapper) {
        this.userService = userService;
        this.userDTOMapper = userDTOMapper;
    }
    @GetMapping
    public List<UserDTO> getAllUsers() {
        return this.userService.getAllUsers().stream()
                .map(userDTOMapper)
                .toList();
    }

    @GetMapping("/{userId}")
    public UserDTO getUser(@PathVariable UUID userId) {
        return this.userDTOMapper.apply(this.userService.getUser(userId));
    }

    @PostMapping
    public ResponseEntity<?> addUser(@RequestBody UserRegistrationRequest userRegistrationRequest) {
        User user = User.builder()
                .name(userRegistrationRequest.name())
                .email(userRegistrationRequest.email())
                .password(userRegistrationRequest.password())
                .build();
        user = userService.addUser(user);
        return ResponseEntity
                .ok()
                .body(userDTOMapper.apply(user));
    }

    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable UUID userId) {
        this.userService.deleteUser(userId);
    }
}
