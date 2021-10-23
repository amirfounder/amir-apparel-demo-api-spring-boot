package com.amirfounder.amirappareldemoapispringboot.domains.purchase;

import com.amirfounder.amirappareldemoapispringboot.domains.purchase.embeddables.CreditCard;
import com.amirfounder.amirappareldemoapispringboot.domains.purchase.embeddables.Address;
import com.amirfounder.amirappareldemoapispringboot.exceptions.BadRequest;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class PurchaseValidator {

    public void validatePurchase(Purchase purchase) {
    }

    public HashMap<String, String> getInvalidCreditCardFields(
            CreditCard creditCard
    ) {
        return new HashMap<>();
    }

    public HashMap<String, String> getInvalidDeliveryAddressFields(
            Address deliveryAddress
    ) {
        return new HashMap<>();
    }

    public void handleInvalidPurchase() {
        throw new BadRequest("Bad purchase");
    }

}
