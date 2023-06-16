package org.stevenguyendev.pcshopwebsite.computer.dao;

import org.stevenguyendev.pcshopwebsite.computer.entity.Brand;
import org.stevenguyendev.pcshopwebsite.computer.entity.Category;
import org.stevenguyendev.pcshopwebsite.computer.entity.Computer;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface ComputerDAO {
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

    Optional<Computer> getComputerByName(String name);

    List<Brand> getAllBrands();

    List<Category> getAllCategories();
}
