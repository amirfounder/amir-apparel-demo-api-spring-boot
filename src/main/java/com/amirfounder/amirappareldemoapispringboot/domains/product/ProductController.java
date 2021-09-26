package com.amirfounder.amirappareldemoapispringboot.domains.product;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static com.amirfounder.amirappareldemoapispringboot.utils.Paths.PRODUCTS_PATH;

@RestController
@RequestMapping(path = PRODUCTS_PATH)
public class ProductController {

    Logger logger = LogManager.getLogger(ProductController.class);

    private final ProductServiceImpl productService;

    @Autowired
    public ProductController(ProductServiceImpl productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<Page<Product>> getProducts(
            Product product,
            Pageable pageable
    ) {
        logger.info("Request received for getProducts");
        return new ResponseEntity<>(productService.getProducts(product, pageable), HttpStatus.OK);
    }

    @GetMapping(value = "/filter")
    public ResponseEntity<Page<Product>> getProductsWithFilter(
            Product product,
            Pageable pageable
    ) {
        logger.info("Request received for getProductsWithFilter");
        return new ResponseEntity<>(productService.getProductsWithFilter(product, pageable), HttpStatus.OK);
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<Product> getProductById(
            @PathVariable Long id
    ) {
        logger.info("Request received for getProductById with Id: " + id);
        return new ResponseEntity<>(productService.getProductById(id), HttpStatus.OK);
    }

    @GetMapping(value = "/attribute/{attribute}")
    public ResponseEntity<ArrayList<String>> getDistinctAttributes(
            @PathVariable String attribute
    ) {
        logger.info("Request received for getDistrictAttributes for attribute " + attribute);
        return new ResponseEntity<>(productService.getDistinctAttributes(attribute), HttpStatus.OK);
    }

}
