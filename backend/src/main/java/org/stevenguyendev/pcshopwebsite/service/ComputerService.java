package org.stevenguyendev.pcshopwebsite.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.stevenguyendev.pcshopwebsite.dto.ComputerFilterRequest;
import org.stevenguyendev.pcshopwebsite.exception.ResourceNotFoundException;
import org.stevenguyendev.pcshopwebsite.model.Computer;
import org.stevenguyendev.pcshopwebsite.repository.ComputerRepository;

import java.math.BigDecimal;
import java.util.List;

@Service
@Transactional
public class ComputerService {
    private final ComputerRepository computerRepository;
    public ComputerService(ComputerRepository computerRepository) {
        this.computerRepository = computerRepository;
    }

    public List<Computer> getFilteredAndSortedComputers(
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
        );
    }

    public Computer getComputerByIdOrName(String idOrName) {
        return computerRepository.findComputerByIdOrName(idOrName)
                                 .orElseThrow(
                                         () -> new ResourceNotFoundException("Computer with id or name " + idOrName + " not found")
                                 );
    }

    public Computer createComputer(Computer computerEntity) {
        return computerRepository.save(computerEntity);
    }

}
