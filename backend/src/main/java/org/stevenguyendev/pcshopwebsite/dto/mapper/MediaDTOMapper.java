package org.stevenguyendev.pcshopwebsite.dto.mapper;

import org.stevenguyendev.pcshopwebsite.dto.MediaDTO;
import org.stevenguyendev.pcshopwebsite.model.Media;

import java.util.function.Function;

public class MediaDTOMapper implements Function<Media, MediaDTO> {
    @Override
    public MediaDTO apply(Media media) {
        if (media == null) {
            return null;
        }

        return new MediaDTO(
                media.getId(),
                media.getFilePath(),
                media.getFileType()
        );
    }
}
