package com.amirfounder.amirappareldemoapispringboot.domains.product;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Getter
@Setter
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
    private LocalDate launchDate;
    private String demographic;
    private String color;
    private String hexCode;

    public Product() {
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", description='" + description + '\'' +
                ", material='" + material + '\'' +
                ", price=" + price +
                ", availableQuantity=" + availableQuantity +
                ", activeStatus=" + activeStatus +
                ", launchDate=" + launchDate +
                ", demographic='" + demographic + '\'' +
                ", color='" + color + '\'' +
                '}';
    }
}
