package com.amirfounder.amirappareldemoapispringboot.domains.product;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductCriteriaRepository {

    public Page<Product> findAllWithFilter(Product product, Pageable pageable);

}
