package com.amirfounder.amirappareldemoapispringboot.domains.purchase;

import com.amirfounder.amirappareldemoapispringboot.domains.lineitem.LineItem;
import com.amirfounder.amirappareldemoapispringboot.domains.purchase.embeddables.Address;
import com.amirfounder.amirappareldemoapispringboot.domains.purchase.embeddables.CreditCard;

import javax.persistence.*;
import java.util.Objects;
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
    private Address shippingAddress;

    @Embedded
    private Address billingAddress;

    @Embedded
    private CreditCard creditCard;

    public Purchase() {
    }

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

    public Address getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(Address shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public Address getBillingAddress() {
        return billingAddress;
    }

    public void setBillingAddress(Address billingAddress) {
        this.billingAddress = billingAddress;
    }

    public CreditCard getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(CreditCard creditCard) {
        this.creditCard = creditCard;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Purchase purchase = (Purchase) o;
        return id.equals(purchase.id) && email.equals(purchase.email) && lineItems.equals(purchase.lineItems) && shippingAddress.equals(purchase.shippingAddress) && billingAddress.equals(purchase.billingAddress) && creditCard.equals(purchase.creditCard);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, lineItems, shippingAddress, billingAddress, creditCard);
    }

    @Override
    public String toString() {
        return "Purchase{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", lineItems=" + lineItems +
                ", shippingAddress=" + shippingAddress +
                ", billingAddress=" + billingAddress +
                ", creditCard=" + creditCard +
                '}';
    }
}
