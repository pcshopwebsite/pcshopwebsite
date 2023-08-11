package org.stevenguyendev.pcshopwebsite.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.stevenguyendev.pcshopwebsite.model.Brand;

import java.util.UUID;

public interface BrandRepository extends JpaRepository<Brand, UUID>{
}
