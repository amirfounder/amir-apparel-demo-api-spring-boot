package com.amirfounder.amirappareldemoapispringboot.domains.product;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService {

    public Page<Product> getProducts(Product product, Pageable pageable);

    public Product getProductById(Long id);
}
