package org.stevenguyendev.pcshopwebsite.computer.dto;

import org.springframework.stereotype.Service;
import org.stevenguyendev.pcshopwebsite.computer.entity.Media;

import java.util.function.Function;

@Service
public class MediaDTOMapper implements Function<Media, MediaDTO> {
    @Override
    public MediaDTO apply(Media media) {
        if (media == null)
            return null;
        return new MediaDTO(
                media.getFilePath(),
                media.getFileType()
        );
    }
}
