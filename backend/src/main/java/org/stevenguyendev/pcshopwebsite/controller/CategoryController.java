package org.stevenguyendev.pcshopwebsite.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.stevenguyendev.pcshopwebsite.dto.CategoryDTO;
import org.stevenguyendev.pcshopwebsite.dto.mapper.CategoryDTOMapper;
import org.stevenguyendev.pcshopwebsite.service.CategoryService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController extends BaseController {

    private final CategoryService categoryService;
    private final CategoryDTOMapper categoryDTOMapper;
    public CategoryController(
            CategoryService categoryService,
            CategoryDTOMapper categoryDTOMapper) {
        this.categoryService = categoryService;
        this.categoryDTOMapper = categoryDTOMapper;
    }
    @GetMapping
    public ResponseEntity<List<CategoryDTO>> getAllCategories() {
        return ResponseEntity.ok(this.categoryService.getAllCategories().stream()
                .map(categoryDTOMapper)
                .toList());
    }
}
