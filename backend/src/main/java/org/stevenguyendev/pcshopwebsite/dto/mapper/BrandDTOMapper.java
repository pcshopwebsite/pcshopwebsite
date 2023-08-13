package org.stevenguyendev.pcshopwebsite.dto.mapper;

import org.springframework.stereotype.Service;
import org.stevenguyendev.pcshopwebsite.dto.BrandDTO;
import org.stevenguyendev.pcshopwebsite.model.Brand;

import java.util.function.Function;

@Service
public class BrandDTOMapper implements Function<Brand, BrandDTO> {
    @Override
    public BrandDTO apply(Brand brand) {
        if (brand == null) {
            return null;
        }
        return new BrandDTO(
                brand.getId(),
                brand.getName()
        );
    }
}
