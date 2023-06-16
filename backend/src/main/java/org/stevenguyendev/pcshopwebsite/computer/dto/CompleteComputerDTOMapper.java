package org.stevenguyendev.pcshopwebsite.computer.dto;

import org.stevenguyendev.pcshopwebsite.computer.entity.Computer;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class CompleteComputerDTOMapper implements Function<Computer, CompleteComputerDTO> {
    private final BrandDTOMapper brandDTOMapper;
    private final CategoryDTOMapper categoryDTOMapper;
    private final MediaDTOMapper mediaDTOMapper;

    public CompleteComputerDTOMapper(
            BrandDTOMapper brandDTOMapper, CategoryDTOMapper categoryDTOMapper,
            MediaDTOMapper mediaDTOMapper
    ) {
        this.brandDTOMapper = brandDTOMapper;
        this.categoryDTOMapper = categoryDTOMapper;
        this.mediaDTOMapper = mediaDTOMapper;
    }

    @Override
    public CompleteComputerDTO apply(Computer computer) {
        if (computer == null) {
            return null;
        }
        return new CompleteComputerDTO(
                computer.getName(),
                computer.getPrice(),
                computer.getDescription(),
                computer.getThumbnail(),
                computer.getRating(),
                brandDTOMapper.apply(computer.getBrand()),
                categoryDTOMapper.apply(computer.getCategory()),
                computer.getSpecification(),
                computer.getMediaList()
                        .stream()
                        .map(mediaDTOMapper)
                        .collect(Collectors.toList()),
                computer.getCreatedAt(),
                computer.getUpdatedAt()
        );
    }
}
