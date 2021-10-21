package com.amirfounder.amirappareldemoapispringboot.domains.purchase;

import com.amirfounder.amirappareldemoapispringboot.domains.lineitem.LineItem;
import com.amirfounder.amirappareldemoapispringboot.domains.purchase.embeddables.CreditCard;
import com.amirfounder.amirappareldemoapispringboot.domains.purchase.embeddables.DeliveryAddress;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Purchase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    @OneToMany(cascade = CascadeType.ALL)
    private Set<LineItem> lineItems;

    @Embedded
    private DeliveryAddress deliveryAddress;

    @Embedded
    private CreditCard creditCard;

    public Purchase() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<LineItem> getLineItems() {
        return lineItems;
    }

    public void setLineItems(Set<LineItem> lineItems) {
        this.lineItems = lineItems;
    }

    public DeliveryAddress getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(DeliveryAddress deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public CreditCard getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(CreditCard creditCard) {
        this.creditCard = creditCard;
    }
}
