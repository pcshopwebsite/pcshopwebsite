package org.stevenguyendev.pcshopwebsite.computer.repository;

import org.stevenguyendev.pcshopwebsite.computer.entity.Computer;

import java.math.BigDecimal;
import java.util.List;

public interface CustomComputerRepository {
    public List<Computer> getFilteredAndSortedComputers(
            String name,
            List<String> brands,
            List<String> categories,
            BigDecimal minPrice,
            BigDecimal maxPrice,
            Float minRating,
            String sortBy,
            String order,
            int page,
            int size
    );
}
