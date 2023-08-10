package org.stevenguyendev.pcshopwebsite.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.stevenguyendev.pcshopwebsite.model.Category;
import org.stevenguyendev.pcshopwebsite.repository.CategoryRepository;

import java.util.List;

@Service
@Transactional
public class CategoryService {
    private final CategoryRepository categoryRepository;
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> getAllCategories() {
        return this.categoryRepository.findAll();
    }
}
