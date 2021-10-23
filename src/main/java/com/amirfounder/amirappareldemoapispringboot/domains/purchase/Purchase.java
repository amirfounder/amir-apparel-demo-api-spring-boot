package com.amirfounder.amirappareldemoapispringboot.domains.purchase;

import com.amirfounder.amirappareldemoapispringboot.domains.lineitem.LineItem;
import com.amirfounder.amirappareldemoapispringboot.domains.purchase.embeddables.Address;
import com.amirfounder.amirappareldemoapispringboot.domains.purchase.embeddables.CreditCard;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Getter
@Setter
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
