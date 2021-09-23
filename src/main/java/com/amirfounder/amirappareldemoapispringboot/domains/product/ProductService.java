package com.amirfounder.amirappareldemoapispringboot.domains.product;

import org.springframework.data.domain.Page;

public interface ProductService {

    public Page<Product> getProducts(Product product, Integer pageCount, Integer pageSize);

    public Product getProductById(Long id);
}
