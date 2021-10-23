package com.amirfounder.amirappareldemoapispringboot.domains.lineitem;

import com.amirfounder.amirappareldemoapispringboot.domains.purchase.Purchase;

public interface LineItemService {

    public void saveLineItemsFromPurchase(Purchase purchase);

}
