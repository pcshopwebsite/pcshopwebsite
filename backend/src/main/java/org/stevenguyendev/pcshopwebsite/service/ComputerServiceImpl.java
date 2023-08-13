package org.stevenguyendev.pcshopwebsite.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.stevenguyendev.pcshopwebsite.dto.ComputerDTO;
import org.stevenguyendev.pcshopwebsite.dto.ComputerFilterRequest;
import org.stevenguyendev.pcshopwebsite.dto.ComputerLiteDTO;
import org.stevenguyendev.pcshopwebsite.dto.mapper.ComputerDTOMapper;
import org.stevenguyendev.pcshopwebsite.dto.mapper.ComputerLiteDTOMapper;
import org.stevenguyendev.pcshopwebsite.exception.ResourceNotFoundException;
import org.stevenguyendev.pcshopwebsite.model.Brand;
import org.stevenguyendev.pcshopwebsite.model.Category;
import org.stevenguyendev.pcshopwebsite.model.Computer;
import org.stevenguyendev.pcshopwebsite.repository.BrandRepository;
import org.stevenguyendev.pcshopwebsite.repository.CategoryRepository;
import org.stevenguyendev.pcshopwebsite.repository.ComputerRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
public class ComputerServiceImpl implements ComputerService {
    private final ComputerRepository computerRepository;
    private final BrandRepository brandRepository;
    private final CategoryRepository categoryRepository;
    private final ComputerDTOMapper computerDTOMapper;
    private final ComputerLiteDTOMapper computerLiteDTOMapper;

    public List<ComputerLiteDTO> filterComputers(
            ComputerFilterRequest request
    ) {
        BigDecimal minPrice = request.minPrice() != null ? new BigDecimal(request.minPrice()) : null;
        BigDecimal maxPrice = request.maxPrice() != null ? new BigDecimal(request.maxPrice()) : null;
        return computerRepository.getFilteredAndSortedComputers(
                request.name(),
                request.brands(),
                request.categories(),
                minPrice,
                maxPrice,
                request.minRating(),
                request.sortBy(),
                request.sortOrder(),
                request.pageNumber(),
                request.pageSize()
        ).stream()
                                 .map(computerLiteDTOMapper)
                                 .collect(Collectors.toList());
    }

    public ComputerDTO findComputerByIdOrName(String idOrName) {
        UUID id = null;
        try {
            id = UUID.fromString(idOrName);
        } catch (IllegalArgumentException ignored) {
        }

        Optional<Computer> computerOptional = computerRepository.findComputerByName(idOrName);
        if (computerOptional.isEmpty() && id == null) {
            throw new ResourceNotFoundException("Computer not found.");
        } else if (computerOptional.isPresent()) {
            return computerDTOMapper.apply(computerOptional.get());
        } else {
            return computerRepository.findComputerById(id)
                    .map(computerDTOMapper)
                    .orElseThrow(() -> new ResourceNotFoundException("Computer not found."));
        }
    }

    public ComputerDTO createComputer(ComputerDTO computerDTO) {
        Computer computer = Computer.builder()
                .name(computerDTO.name())
                .price(computerDTO.price())
                .rating(computerDTO.rating())
                .description(computerDTO.description())
                .thumbnail(computerDTO.thumbnail())
                .build();
        Optional<Brand> brand = brandRepository.findByName(computerDTO.brand().name());
        if (brand.isPresent()) {
            computer.setBrand(brand.get());
        } else {
            Brand newBrand = Brand.builder()
                    .name(computerDTO.brand().name())
                    .build();
            computer.setBrand(newBrand);
        }
        Optional<Category> category = categoryRepository.findByName(computerDTO.category().name());
        if (category.isPresent()) {
            computer.setCategory(category.get());
        } else {
            Category newCategory = Category.builder()
                    .name(computerDTO.category().name())
                    .build();
            computer.setCategory(newCategory);
        }
        return computerDTOMapper.apply(computerRepository.save(computer));
    }
}
