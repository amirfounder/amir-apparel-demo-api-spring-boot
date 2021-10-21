package com.amirfounder.amirappareldemoapispringboot.domains.lineitem;

import com.amirfounder.amirappareldemoapispringboot.domains.purchase.Purchase;

import java.util.List;
import java.util.Set;

public interface LineItemService {

    public void saveLineItemsFromPurchase(Purchase purchase);

}
