package com.amirfounder.amirappareldemoapispringboot.domains.lineitem;

import com.amirfounder.amirappareldemoapispringboot.domains.product.Product;
import com.amirfounder.amirappareldemoapispringboot.domains.purchase.Purchase;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
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
}
