package com.amirfounder.amirappareldemoapispringboot.domains.product;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;

public interface ProductCriteriaRepository {

    Page<Product> findAllWithFilter(Product product, Pageable pageable);

    ArrayList<String> findDistinctAttributes(String attribute);
}
