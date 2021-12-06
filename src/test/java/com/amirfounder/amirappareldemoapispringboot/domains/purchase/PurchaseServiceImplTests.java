package com.amirfounder.amirappareldemoapispringboot.domains.purchase;

import com.amirfounder.amirappareldemoapispringboot.data.ProductFactory;
import com.amirfounder.amirappareldemoapispringboot.domains.lineitem.LineItem;
import com.amirfounder.amirappareldemoapispringboot.domains.product.Product;
import com.amirfounder.amirappareldemoapispringboot.domains.purchase.embeddables.Address;
import com.amirfounder.amirappareldemoapispringboot.domains.purchase.embeddables.CreditCard;
import org.junit.Before;
import org.junit.Test;
import org.modelmapper.ModelMapper;

import java.util.HashSet;
import java.util.Set;

public class PurchaseServiceImplTests {

    private final ModelMapper modelMapper = new ModelMapper();
    private final Purchase purchase = new Purchase();
    private final LineItem lineItem1 = new LineItem();
    private final LineItem lineItem2 = new LineItem();
    private final Product product = new Product();
    private final ProductFactory productFactory = new ProductFactory();
    private final Set<LineItem> lineItems = new HashSet<>();

    @Before
    public void setup() {
        CreditCard creditCard = new CreditCard();
        Address billingAddress = new Address();
        Address shippingAddress = new Address();

        lineItem1.setPurchase(purchase);
        lineItem1.setProduct(productFactory.generateProduct(1));
        lineItem1.setQuantity(3);

        lineItem2.setPurchase(purchase);
        lineItem2.setProduct(productFactory.generateProduct(1));
        lineItem2.setQuantity(5);

        lineItems.add(lineItem1);
        lineItems.add(lineItem2);

        purchase.setBillingAddress(billingAddress);
        purchase.setShippingAddress(shippingAddress);
        purchase.setCreditCard(creditCard);
        purchase.setLineItems(lineItems);
    }

    @Test
    public void purchaseToPurchaseDTO_CreditCardNotAvailable() {
        purchase.getCreditCard().setCardNumber("1234123412341234");
        purchase.getBillingAddress().setCity("City");
        purchase.setId(1L);

        PurchaseDTO purchaseDTO = modelMapper.map(purchase, PurchaseDTO.class);

    }

}
