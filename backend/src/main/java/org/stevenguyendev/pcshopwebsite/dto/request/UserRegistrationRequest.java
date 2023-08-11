package org.stevenguyendev.pcshopwebsite.dto.request;

public record UserRegistrationRequest(
        String name,
        String email,
        String password
) {
}
