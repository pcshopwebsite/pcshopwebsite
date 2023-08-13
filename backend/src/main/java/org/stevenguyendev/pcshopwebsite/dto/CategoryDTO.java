package org.stevenguyendev.pcshopwebsite.dto;

import java.util.UUID;

public record CategoryDTO (
        UUID id,
        String name
) {
}
