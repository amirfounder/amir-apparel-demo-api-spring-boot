package com.amirfounder.amirappareldemoapispringboot.domains.product;

import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

@Service
public class ProductUtils {

    public ArrayList<String> getProductFields() {
        Field[] fields = Product.class.getDeclaredFields();
        return (ArrayList<String>) Arrays
                        .stream(fields)
                        .map((Field::getName))
                        .collect(Collectors.toList());
    }

}
