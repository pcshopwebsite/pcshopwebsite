package org.stevenguyendev.pcshopwebsite.computer.service;

import org.stevenguyendev.pcshopwebsite.computer.dao.ComputerDAO;
import org.springframework.stereotype.Service;
import org.stevenguyendev.pcshopwebsite.computer.dto.*;
import org.stevenguyendev.pcshopwebsite.computer.request.ComputerFilterRequest;
import org.stevenguyendev.pcshopwebsite.exception.ResourceNotFoundException;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ComputerService {
    private final ComputerDAO computerDAO;
    private final ComputerDTOMapper computerDTOMapper;
    private final CompleteComputerDTOMapper completeComputerDTOMapper;
    private final BrandDTOMapper brandDTOMapper;
    private final CategoryDTOMapper categoryDTOMapper;

    public ComputerService(
            ComputerDAO computerDAO,
            ComputerDTOMapper computerDTOMapper,
            CompleteComputerDTOMapper completeComputerDTOMapper,
            BrandDTOMapper brandDTOMapper,
            CategoryDTOMapper categoryDTOMapper
    ) {
        this.computerDAO = computerDAO;
        this.computerDTOMapper = computerDTOMapper;
        this.completeComputerDTOMapper = completeComputerDTOMapper;
        this.brandDTOMapper = brandDTOMapper;
        this.categoryDTOMapper = categoryDTOMapper;
    }

    public List<ComputerDTO> getFilteredAndSortedComputers(
            ComputerFilterRequest request
    ) {
        BigDecimal minPrice = request.minPrice() != null ? new BigDecimal(request.minPrice()) : null;
        BigDecimal maxPrice = request.maxPrice() != null ? new BigDecimal(request.maxPrice()) : null;

        return computerDAO.getFilteredAndSortedComputers(
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
                          )
                          .stream()
                          .map(computerDTOMapper)
                          .collect(Collectors.toList());
    }

    public CompleteComputerDTO getComputerByName(String name) {
        return computerDAO.getComputerByName(name)
                          .map(completeComputerDTOMapper)
                          .orElseThrow(
                                  () -> new ResourceNotFoundException("Computer with name " + name + " not found")
                          );
    }

    public List<BrandDTO> getAllBrands() {

        return computerDAO.getAllBrands()
                          .stream()
                          .map(brandDTOMapper)
                          .collect(Collectors.toList());
    }

    public List<CategoryDTO> getAllCategories() {
        return computerDAO.getAllCategories()
                          .stream()
                          .map(categoryDTOMapper)
                          .collect(Collectors.toList());
    }
}
