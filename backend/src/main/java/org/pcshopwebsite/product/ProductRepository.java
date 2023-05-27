package org.pcshopwebsite.product;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

@Transactional
public interface ProductRepository extends JpaRepository<Product, Integer> {
}
