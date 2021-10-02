package com.amirfounder.amirappareldemoapispringboot.data;

import com.amirfounder.amirappareldemoapispringboot.domains.product.Product;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;

public class ProductFactory {

    public ArrayList<Product> generateProducts(int count) {
        ArrayList<Product> generatedProducts = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            Product product = generateProduct(i);
            generatedProducts.add(product);
        }
        return generatedProducts;
    }

    private Product generateProduct(int id) {
        Product product = new Product();

        String material = generateMaterial();
        String type = generateType();
        String name = generateName(material, type);
        String hexCode = generateHexCode();

        product.setId((long) id + 1);
        product.setName(name);
        product.setType(type);
        product.setMaterial(material);
        product.setDescription(generateDescription(name));
        product.setPrice(generatePrice());
        product.setAvailableQuantity(generateAvailableQuantity());
        product.setActiveStatus(generateActiveStatus());
        product.setDemographic(generateDemographic());
        product.setLaunchDate(generateLaunchDate());
        product.setHexCode(hexCode);
        product.setColor(generateColor(hexCode));

        return product;
    }

    private String generateColor(String hexCode) {
        switch (hexCode) {
            case "b0160b":
                return "Dark Red";
            case "870bb0":
                return "Purple";
            case "16f7d2":
                return "Turquoise";
            case "0b81b0":
                return "Blue";
            default:
                return "Unknown";
        }
    }

    private String generateType() {
        String[] types = {"Gloves", "Shorts", "Pants", "Shoes", "Socks", "Boxers"};
        int randomIndex = generateRandomInt(types.length);
        return types[randomIndex];
    }

    private String generateMaterial() {
        String[] materials = {"Cotton", "Polyesters", "Silk", "Leather"};
        int randomIndex = generateRandomInt(materials.length);
        return materials[randomIndex];
    }

    private String generateName(String material, String type) {
        return material + " " + type;
    }

    private String generateDescription(String name) {
        return "These are some awesome " + name + "! These guys are definitely a steal!";
    }

    private BigDecimal generatePrice() {
        double randomDouble = generateRandomDouble();
        double randomPrice = (randomDouble * 100);
        return BigDecimal.valueOf(randomPrice).setScale(2, RoundingMode.HALF_UP);
    }

    private Integer generateAvailableQuantity() {
        int min = 100;
        int max = 500;
        int randomInt = generateRandomInt(max - min);
        return randomInt + min;
    }

    private Boolean generateActiveStatus() {
        return generateRandomBoolean();
    }

    private String generateDemographic() {
        String[] demographics = {"Kids", "Women", "Men"};
        int randomIndex = generateRandomInt(demographics.length);
        return demographics[randomIndex];
    }

    private LocalDate generateLaunchDate() {
        int minDay = (int) LocalDate.of(1990, 1, 1).toEpochDay();
        int maxDay = (int) LocalDate.of(2010, 1, 1).toEpochDay();
        int randomDay = minDay + generateRandomInt(maxDay - minDay);
        return LocalDate.ofEpochDay(randomDay);
    }

    private String generateHexCode() {
        String[] hexCodes = {"b0160b", "16f7d2", "870bb0", "0b81b0"};
        int randomIndex = generateRandomInt(hexCodes.length);
        return hexCodes[randomIndex];
    }

    private int generateRandomInt(int maxBound) {
        Random random = new Random();
        return random.nextInt(maxBound);
    }

    private double generateRandomDouble() {
        Random random = new Random();
        return random.nextDouble();
    }

    private boolean generateRandomBoolean() {
        Random random = new Random();
        return random.nextBoolean();
    }

}
