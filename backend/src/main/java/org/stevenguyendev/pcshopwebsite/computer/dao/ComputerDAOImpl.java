package org.stevenguyendev.pcshopwebsite.computer.dao;

import jakarta.transaction.Transactional;
import org.stevenguyendev.pcshopwebsite.computer.entity.Brand;
import org.stevenguyendev.pcshopwebsite.computer.entity.Category;
import org.stevenguyendev.pcshopwebsite.computer.entity.Computer;
import org.stevenguyendev.pcshopwebsite.computer.repository.BrandRepository;
import org.stevenguyendev.pcshopwebsite.computer.repository.CategoryRepository;
import org.stevenguyendev.pcshopwebsite.computer.repository.ComputerRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public class ComputerDAOImpl implements ComputerDAO {
    private final ComputerRepository computerRepository;
    private final BrandRepository brandRepository;
    private final CategoryRepository categoryRepository;

    public ComputerDAOImpl(
            ComputerRepository computerRepository,
            BrandRepository brandRepository,
            CategoryRepository categoryRepository
    ) {
        this.computerRepository = computerRepository;
        this.brandRepository = brandRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
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
    ) {
        return this.computerRepository.getFilteredAndSortedComputers(
                name,
                brands,
                categories,
                minPrice,
                maxPrice,
                minRating,
                sortBy,
                order,
                page,
                size);
    }

    @Override
    public Optional<Computer> getComputerByName(String name) {
        return computerRepository.findComputerByName(name);
    }

    @Override
    public List<Brand> getAllBrands() {
        return brandRepository.findAll();
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }
}
