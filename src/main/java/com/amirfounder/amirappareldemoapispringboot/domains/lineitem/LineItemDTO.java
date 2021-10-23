package com.amirfounder.amirappareldemoapispringboot.domains.lineitem;

import com.amirfounder.amirappareldemoapispringboot.domains.product.Product;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LineItemDTO {

    private Long id;
    private Product product;
    private Integer quantity;

}
