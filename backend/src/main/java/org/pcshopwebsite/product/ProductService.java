package org.pcshopwebsite.product;

import org.pcshopwebsite.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final ProductDao productDao;
    private final ProductDTOMapper productDTOMapper;

    public ProductService(@Qualifier("jpa") ProductDao productDao, ProductDTOMapper productDTOMapper) {
        this.productDao = productDao;
        this.productDTOMapper = productDTOMapper;
    }

    public List<ProductDTO> getAllProducts() {
        return productDao.selectAllProducts().stream().map(productDTOMapper).collect(Collectors.toList());
    }

    public ProductDTO getProductById(int id) {
        return productDao.selectProductById(id).map(productDTOMapper).orElseThrow(() -> new ResourceNotFoundException(
                "product with id [%s] not found".formatted(id)
        ));
    }
}
