package org.stevenguyendev.pcshopwebsite.computer.dto;

import org.stevenguyendev.pcshopwebsite.computer.entity.Category;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class CategoryDTOMapper implements Function<Category, CategoryDTO> {
    @Override
    public CategoryDTO apply(Category category) {
        if (category == null) {
            return null;
        }
        return new CategoryDTO(
                category.getName()
        );
    }
}
