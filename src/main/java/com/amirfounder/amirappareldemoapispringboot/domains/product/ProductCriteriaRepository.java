package com.amirfounder.amirappareldemoapispringboot.domains.product;

import java.util.List;

public interface ProductCriteriaRepository {

    public List<Product> findAllWithFilter(Product product);

}
