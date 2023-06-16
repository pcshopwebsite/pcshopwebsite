package org.stevenguyendev.pcshopwebsite.computer.controller;

import org.stevenguyendev.pcshopwebsite.computer.dto.BrandDTO;
import org.stevenguyendev.pcshopwebsite.computer.service.ComputerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/brands")
public class BrandController {
    private final ComputerService computerService;
    public BrandController(ComputerService computerService) {
        this.computerService = computerService;
    }

    @GetMapping
    public List<BrandDTO> getAllBrands() {
        return computerService.getAllBrands();
    }
}
