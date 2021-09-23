package com.amirfounder.amirappareldemoapispringboot.domains.product;

import com.amirfounder.amirappareldemoapispringboot.exceptions.ResourceNotFound;
import com.amirfounder.amirappareldemoapispringboot.exceptions.ServiceUnavailable;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    Logger logger = LogManager.getLogger(ProductServiceImpl.class);

    @Autowired
    ProductRepository productRepository;

    @Override
    public List<Product> getProducts(Product product) {
        try {
            return productRepository.findAll(
                Example.of(product, ExampleMatcher
                    .matching()
                    .withIgnoreCase()
            ));
        } catch (DataAccessException dae) {
            logger.error(dae.getMessage());
            throw new ServiceUnavailable(dae.getMessage());
        }
    }

    @Override
    public Product getProductById(Long id) {
        Product product;

        try {
            product = productRepository.findById(id).orElse(null);
        } catch (DataAccessException dae) {
            logger.error(dae.getMessage());
            throw new ServiceUnavailable(dae.getMessage());
        }

        if (product == null) {
            throw new ResourceNotFound("Could not find product with id: " + id);
        }

        return product;
    }

}
