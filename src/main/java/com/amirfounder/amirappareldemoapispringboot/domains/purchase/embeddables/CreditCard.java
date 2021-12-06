package com.amirfounder.amirappareldemoapispringboot.domains.purchase.embeddables;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;

@Embeddable
@Getter
@Setter
public class CreditCard {

    private String cardholderName;
    private String cardNumber;
    private String expirationDate;
    private String cvv;

    public CreditCard() {
    }

    public String getCardholderName() {
        return cardholderName;
    }

    public void setCardholderName(String cardholderName) {
        this.cardholderName = cardholderName;
    }
}
