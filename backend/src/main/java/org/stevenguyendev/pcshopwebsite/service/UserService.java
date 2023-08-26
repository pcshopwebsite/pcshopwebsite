package org.stevenguyendev.pcshopwebsite.service;

import org.stevenguyendev.pcshopwebsite.dto.UserDTO;
import org.stevenguyendev.pcshopwebsite.model.User;

import java.util.Collection;
import java.util.UUID;

public interface UserService {
    Collection<UserDTO> getAllUsers();
    UserDTO getUser(UUID userId);
    UserDTO addUser(UserDTO userDTO);
    UserDTO updateUser(UserDTO userDTO);
    void deleteUser(UUID userId);

    @Deprecated
    User getCurrentUser();
}
