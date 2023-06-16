package org.stevenguyendev.pcshopwebsite.computer.controller;

import org.stevenguyendev.pcshopwebsite.computer.dto.CategoryDTO;
import org.stevenguyendev.pcshopwebsite.computer.service.ComputerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {
    private final ComputerService computerService;
    public CategoryController(ComputerService computerService) {
        this.computerService = computerService;
    }

    @GetMapping
    public List<CategoryDTO> getAllCategories() {
        return computerService.getAllCategories();
    }
}
