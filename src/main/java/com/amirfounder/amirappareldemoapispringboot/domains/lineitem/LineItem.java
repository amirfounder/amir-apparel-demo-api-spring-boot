package com.amirfounder.amirappareldemoapispringboot.domains.lineitem;

import com.amirfounder.amirappareldemoapispringboot.domains.product.Product;
import com.amirfounder.amirappareldemoapispringboot.domains.purchase.Purchase;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
public class LineItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JsonIgnoreProperties("lineItems")
    private Purchase purchase;

    @ManyToOne
    private Product product;

    private Integer quantity;

    public LineItem() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Purchase getPurchase() {
        return purchase;
    }

    public void setPurchase(Purchase purchase) {
        this.purchase = purchase;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
