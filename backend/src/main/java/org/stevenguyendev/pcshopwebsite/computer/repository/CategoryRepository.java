package org.stevenguyendev.pcshopwebsite.computer.repository;

import org.stevenguyendev.pcshopwebsite.computer.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
