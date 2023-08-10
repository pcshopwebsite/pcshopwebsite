package org.stevenguyendev.pcshopwebsite.dto.mapper;

import org.springframework.stereotype.Service;
import org.stevenguyendev.pcshopwebsite.dto.ComputerDTO;
import org.stevenguyendev.pcshopwebsite.model.Computer;

import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class ComputerDTOMapper implements Function<Computer, ComputerDTO> {
    private final BrandDTOMapper brandDTOMapper;
    private final CategoryDTOMapper categoryDTOMapper;
    private final MediaDTOMapper mediaDTOMapper;

    public ComputerDTOMapper(BrandDTOMapper brandDTOMapper, CategoryDTOMapper categoryDTOMapper, MediaDTOMapper mediaDTOMapper) {
        this.brandDTOMapper = brandDTOMapper;
        this.categoryDTOMapper = categoryDTOMapper;
        this.mediaDTOMapper = mediaDTOMapper;
    }
    @Override
    public ComputerDTO apply(Computer computer) {
        if (computer == null) {
            return null;
        }

        return new ComputerDTO(
                computer.getId(),
                computer.getName(),
                computer.getDescription(),
                computer.getPrice(),
                computer.getRating(),
                computer.getThumbnail(),
                categoryDTOMapper.apply(computer.getCategory()),
                brandDTOMapper.apply(computer.getBrand()),
                computer.getSpecification(),
                computer.getMedias().stream().map(mediaDTOMapper).collect(Collectors.toSet()),
                computer.getCreatedAt(),
                computer.getUpdatedAt(),
                computer.getCreatedBy(),
                computer.getUpdatedBy());
    }
}
