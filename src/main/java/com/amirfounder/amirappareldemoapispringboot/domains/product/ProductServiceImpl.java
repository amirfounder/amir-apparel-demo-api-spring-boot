package com.amirfounder.amirappareldemoapispringboot.domains.product;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ProductServiceImpl implements ProductService {

    Logger logger = LogManager.getLogger(ProductServiceImpl.class);

    @Autowired
    ProductRepository productRepository;

    @Override
    public Page<Product> getProducts(Product product, Integer pageCount, Integer pageSize) {

        Pageable page = PageRequest.of(pageCount, pageSize);
        Example<Product> example = Example.of(product, ExampleMatcher.matchingAll().withIgnoreCase());

        try {
            return productRepository.findAll(example, page);
        } catch (DataAccessException dae) {
            logger.error(dae.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, dae.getMessage());
        }
    }

    @Override
    public Product getProductById(Long id) {
        Product product;

        try {
            product = productRepository.findById(id).orElse(null);
        } catch (DataAccessException dae) {
            logger.error(dae.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, dae.getMessage());
        }

        if (product == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Could not find product with id: " + id);
        }

        return product;
    }
}
