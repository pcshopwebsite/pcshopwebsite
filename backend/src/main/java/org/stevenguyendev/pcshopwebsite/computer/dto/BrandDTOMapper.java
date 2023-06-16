package org.stevenguyendev.pcshopwebsite.computer.dto;

import org.stevenguyendev.pcshopwebsite.computer.entity.Brand;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class BrandDTOMapper implements Function<Brand, BrandDTO> {
    @Override
    public BrandDTO apply(Brand brand) {
        if (brand == null) {
            return null;
        }
        return new BrandDTO(
                brand.getName()
        );
    }
}
