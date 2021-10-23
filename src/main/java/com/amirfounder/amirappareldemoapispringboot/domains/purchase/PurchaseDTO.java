package com.amirfounder.amirappareldemoapispringboot.domains.purchase;

import com.amirfounder.amirappareldemoapispringboot.domains.lineitem.LineItem;
import com.amirfounder.amirappareldemoapispringboot.domains.lineitem.LineItemDTO;
import com.amirfounder.amirappareldemoapispringboot.domains.purchase.embeddables.Address;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class PurchaseDTO {

    private Long id;
    private Long email;
    private Address shippingAddress;
    private Address billingAddress;
    private Set<LineItemDTO> lineItems;

}
