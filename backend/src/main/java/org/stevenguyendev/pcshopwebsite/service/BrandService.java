package org.stevenguyendev.pcshopwebsite.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.stevenguyendev.pcshopwebsite.model.Brand;
import org.stevenguyendev.pcshopwebsite.repository.BrandRepository;

import java.util.List;

@Service
@Transactional
public class BrandService {
    private final BrandRepository brandRepository;
    public BrandService(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }
    public List<Brand> getAllBrands() {
        return this.brandRepository.findAll();
    }
}
