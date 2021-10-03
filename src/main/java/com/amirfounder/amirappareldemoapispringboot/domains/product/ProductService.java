package com.amirfounder.amirappareldemoapispringboot.domains.product;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;

public interface ProductService {

    Page<Product> getProducts(Product product, Pageable pageable);

    Page<Product> getProductsWithFilter(Product product, Pageable pageable);

    Product getProductById(Long id);

    ArrayList<String> getDistinctAttributes(String attribute);
}
