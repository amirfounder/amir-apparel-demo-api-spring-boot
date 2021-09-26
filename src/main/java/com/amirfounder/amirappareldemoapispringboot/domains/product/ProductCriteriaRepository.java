package com.amirfounder.amirappareldemoapispringboot.domains.product;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductCriteriaRepository {

    Page<Product> findAllWithFilter(Product product, Pageable pageable);

}
