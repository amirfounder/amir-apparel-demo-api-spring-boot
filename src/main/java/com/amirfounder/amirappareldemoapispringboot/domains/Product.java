package com.amirfounder.amirappareldemoapispringboot.domains;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String type;
    private String description;
    private String material;
    private BigDecimal price;
    private Integer availableQuantity;
    private Boolean activeStatus;

    public Product() {}

    public Product(Long id, String name, String type, String description, String material, BigDecimal price, Integer availableQuantity, Boolean activeStatus) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.description = description;
        this.material = material;
        this.price = price;
        this.availableQuantity = availableQuantity;
        this.activeStatus = activeStatus;
    }

    public Product(String name, String type, String description, String material, BigDecimal price, Integer availableQuantity, Boolean activeStatus) {
        this.name = name;
        this.type = type;
        this.description = description;
        this.material = material;
        this.price = price;
        this.availableQuantity = availableQuantity;
        this.activeStatus = activeStatus;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getAvailableQuantity() {
        return availableQuantity;
    }

    public void setAvailableQuantity(Integer availableQuantity) {
        this.availableQuantity = availableQuantity;
    }

    public Boolean getActiveStatus() {
        return activeStatus;
    }

    public void setActiveStatus(Boolean activeStatus) {
        this.activeStatus = activeStatus;
    }
}
