package org.stevenguyendev.pcshopwebsite.dto;

import java.util.UUID;

public record UserDTO(
        UUID id,
        String name,
        String email
) {
}
