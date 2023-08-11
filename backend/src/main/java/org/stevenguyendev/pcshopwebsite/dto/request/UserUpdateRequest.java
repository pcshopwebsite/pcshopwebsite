package org.stevenguyendev.pcshopwebsite.dto.request;

public record UserUpdateRequest(
        String name,
        String email,
        String password
) {
}
