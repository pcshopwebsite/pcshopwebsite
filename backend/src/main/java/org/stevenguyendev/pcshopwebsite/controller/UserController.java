package org.stevenguyendev.pcshopwebsite.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.stevenguyendev.pcshopwebsite.dto.UserDTO;
import org.stevenguyendev.pcshopwebsite.service.UserService;

import java.util.Collection;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/users")
@AllArgsConstructor
public class UserController {
    private final UserService userService;
    @GetMapping
    public Collection<UserDTO> getAllUsers() {
        return this.userService.getAllUsers();
    }

    @GetMapping("/{userId}")
    public UserDTO getUser(@PathVariable UUID userId) {
        return this.userService.getUser(userId);
    }

    @PostMapping
    public ResponseEntity<?> addUser(@RequestBody UserDTO userDTO) {
        UserDTO user = userService.addUser(userDTO);
        return ResponseEntity
                .ok()
                .body(user);
    }

    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable UUID userId) {
        // TODO delete other resources associated with user
        this.userService.deleteUser(userId);
    }
}
