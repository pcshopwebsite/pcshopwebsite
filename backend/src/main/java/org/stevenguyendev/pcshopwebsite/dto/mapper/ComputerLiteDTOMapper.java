package org.stevenguyendev.pcshopwebsite.dto.mapper;

import org.springframework.stereotype.Service;
import org.stevenguyendev.pcshopwebsite.dto.ComputerLiteDTO;
import org.stevenguyendev.pcshopwebsite.model.Computer;

import java.util.function.Function;

@Service
public class ComputerLiteDTOMapper implements Function<Computer, ComputerLiteDTO> {

    @Override
    public ComputerLiteDTO apply(Computer computer) {
        if (computer == null) {
            return null;
        }

        return new ComputerLiteDTO(
                computer.getId(),
                computer.getName(),
                computer.getDescription(),
                computer.getPrice(),
                computer.getRating(),
                computer.getThumbnail(),
                computer.getBrand().getName(),
                computer.getCategory().getName(),
                computer.getCreatedAt(),
                computer.getUpdatedAt()
        );
    }
}
