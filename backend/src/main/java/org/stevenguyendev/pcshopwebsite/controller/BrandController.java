package org.stevenguyendev.pcshopwebsite.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.stevenguyendev.pcshopwebsite.dto.BrandDTO;
import org.stevenguyendev.pcshopwebsite.dto.mapper.BrandDTOMapper;
import org.stevenguyendev.pcshopwebsite.service.BrandService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/brands")
public class BrandController extends BaseController{

    private final BrandService brandService;
    private final BrandDTOMapper brandDTOMapper;
    public BrandController(
            BrandService brandService,
            BrandDTOMapper brandDTOMapper) {
        this.brandService = brandService;
        this.brandDTOMapper = brandDTOMapper;
    }

    @GetMapping
    public ResponseEntity<List<BrandDTO>> getAllBrands() {
        return ResponseEntity.ok(this.brandService.getAllBrands().stream()
                .map(brandDTOMapper)
                .toList());
    }
}
