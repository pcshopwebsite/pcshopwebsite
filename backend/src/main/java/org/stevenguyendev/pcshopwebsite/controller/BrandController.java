package org.stevenguyendev.pcshopwebsite.controller;

import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.stevenguyendev.pcshopwebsite.dto.BrandDTO;
import org.stevenguyendev.pcshopwebsite.dto.mapper.BrandDTOMapper;
import org.stevenguyendev.pcshopwebsite.service.BrandService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/brands")
public class BrandController {

    private final BrandService brandService;
    private final BrandDTOMapper brandDTOMapper;
    public BrandController(
            BrandService brandService,
            ModelMapper modelMapper, BrandDTOMapper brandDTOMapper) {
        this.brandService = brandService;
        this.brandDTOMapper = brandDTOMapper;
    }

    @GetMapping
    public List<BrandDTO> getAllBrands() {
        return brandService.getAllBrands().stream().map(brandDTOMapper).toList();
    }
}
