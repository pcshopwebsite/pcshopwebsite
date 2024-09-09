package org.stevenguyendev.pcshopwebsite.dto.mapper;

import org.springframework.stereotype.Service;
import org.stevenguyendev.pcshopwebsite.dto.UserDTO;
import org.stevenguyendev.pcshopwebsite.model.User;

import java.util.function.Function;

@Service
public class UserDTOMapper implements Function<User, UserDTO> {
    @Override
    public UserDTO apply(User user) {
        if (user == null) {
            return null;
        }

        return new UserDTO(
                user.getId(),
                user.getName(),
                user.getEmail(),
                null
        );
    }
}
