package org.stevenguyendev.pcshopwebsite.repository;

import org.stevenguyendev.pcshopwebsite.model.Computer;

import java.math.BigDecimal;
import java.util.List;

public interface CustomComputerRepository {
     List<Computer> getFilteredAndSortedComputers(
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
