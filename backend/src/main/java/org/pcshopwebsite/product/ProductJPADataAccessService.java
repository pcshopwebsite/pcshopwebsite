package org.pcshopwebsite.product;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository("jpa")
public class ProductJPADataAccessService implements ProductDao {

    private final ProductRepository productRepository;

    public ProductJPADataAccessService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> selectAllProducts() {
        Page<Product> page = productRepository.findAll(Pageable.ofSize(1000));
        return page.getContent();
    }

    @Override
    public Optional<Product> selectProductById(int id) {
        return productRepository.findById(id);  // Optional<Product>
    }
}
