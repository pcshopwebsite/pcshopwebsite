package org.stevenguyendev.pcshopwebsite.computer.dto;

import org.stevenguyendev.pcshopwebsite.computer.entity.Computer;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class ComputerDTOMapper implements Function<Computer, ComputerDTO> {
    private final BrandDTOMapper brandDTOMapper;
    private final CategoryDTOMapper categoryDTOMapper;
    public ComputerDTOMapper(BrandDTOMapper brandDTOMapper, CategoryDTOMapper categoryDTOMapper) {
        this.brandDTOMapper = brandDTOMapper;
        this.categoryDTOMapper = categoryDTOMapper;
    }
    @Override
    public ComputerDTO apply(Computer computer) {
        if (computer == null) {
            return null;
        }
        return new ComputerDTO(
                computer.getName(),
                computer.getPrice(),
                computer.getDescription(),
                computer.getThumbnail(),
                computer.getRating(),
                brandDTOMapper.apply(computer.getBrand()),
                categoryDTOMapper.apply(computer.getCategory()),
                computer.getCreatedAt(),
                computer.getUpdatedAt()
        );
    }
}
