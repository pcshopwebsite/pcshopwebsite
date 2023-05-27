package org.pcshopwebsite.product;

import java.util.List;
import java.util.Optional;

public interface ProductDao {
    public List<Product> selectAllProducts();

    public Optional<Product> selectProductById(int id);
}
