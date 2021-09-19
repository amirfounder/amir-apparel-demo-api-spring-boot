package com.amirfounder.amirappareldemoapispringboot.data;

import com.amirfounder.amirappareldemoapispringboot.domains.product.Product;
import com.amirfounder.amirappareldemoapispringboot.domains.product.ProductRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class DataLoader implements CommandLineRunner {

    Logger logger = LogManager.getLogger(DataLoader.class);

    ProductFactory productFactory = new ProductFactory();

    ProductRepository productRepository;

    @Autowired
    public DataLoader(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        ArrayList<Product> products = productFactory.generateProducts(500);
        productRepository.saveAll(products);
        logger.info("Generated 500 random products.");

    }
}
