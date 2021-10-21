package com.amirfounder.amirappareldemoapispringboot.domains.purchase.embeddables;

import javax.persistence.Embeddable;

@Embeddable
public class CreditCard {

    private String cardholderName;
    private String creditCardNumber;
    private String expirationDate;
    private String cvv;

    public CreditCard() {}

    public String getCardholderName() {
        return cardholderName;
    }

    public void setCardholderName(String cardholderName) {
        this.cardholderName = cardholderName;
    }

    public String getCreditCardNumber() {
        return creditCardNumber;
    }

    public void setCreditCardNumber(String creditCardNumber) {
        this.creditCardNumber = creditCardNumber;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }
}
