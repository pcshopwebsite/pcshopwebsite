package org.stevenguyendev.pcshopwebsite.dto;

import java.util.UUID;

public record MediaDTO (
        UUID id,
        String filePath,
        String fileType
) {

}
