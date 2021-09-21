package com.amirfounder.amirappareldemoapispringboot.domains.product;

import java.util.List;

public interface ProductService {

    public List<Product> getProducts(Product product);

    public Product getProductById(Long id);
}
