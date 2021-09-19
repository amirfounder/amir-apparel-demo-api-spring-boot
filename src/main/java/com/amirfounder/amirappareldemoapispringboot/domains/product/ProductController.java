package com.amirfounder.amirappareldemoapispringboot.domains.product;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

import static com.amirfounder.amirappareldemoapispringboot.utils.Paths.PRODUCTS_PATH;

@RestController
@RequestMapping(path = PRODUCTS_PATH)
public class ProductController {

    Logger logger = LogManager.getLogger(ProductController.class);

    @Autowired
    ProductServiceImpl productService;

    @GetMapping
    public ResponseEntity<List<Product>> getProducts(Product product) {
        logger.info("Request received for getProducts");
        return new ResponseEntity<>(productService.getProducts(product), HttpStatus.OK);
    }

}
