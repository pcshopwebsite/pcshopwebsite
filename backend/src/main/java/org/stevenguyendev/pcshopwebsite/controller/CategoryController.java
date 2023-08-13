package org.stevenguyendev.pcshopwebsite.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.stevenguyendev.pcshopwebsite.dto.CategoryDTO;
import org.stevenguyendev.pcshopwebsite.dto.mapper.CategoryDTOMapper;
import org.stevenguyendev.pcshopwebsite.service.CategoryService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {

    private final CategoryService categoryService;
    private final CategoryDTOMapper categoryDTOMapper;
    public CategoryController(
            CategoryService categoryService,
            CategoryDTOMapper categoryDTOMapper) {
        this.categoryService = categoryService;
        this.categoryDTOMapper = categoryDTOMapper;
    }
    @GetMapping
    public List<CategoryDTO> getAllCategories() {
        return this.categoryService.getAllCategories().stream()
                .map(categoryDTOMapper)
                .toList();
    }
}
