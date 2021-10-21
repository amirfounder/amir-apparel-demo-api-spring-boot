package com.amirfounder.amirappareldemoapispringboot.domains.purchase;

import com.amirfounder.amirappareldemoapispringboot.domains.lineitem.LineItemService;
import com.amirfounder.amirappareldemoapispringboot.domains.lineitem.LineItemServiceImpl;
import com.amirfounder.amirappareldemoapispringboot.exceptions.ServiceUnavailable;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

@Service
public class PurchaseServiceImpl implements PurchaseService {

    Logger logger = LogManager.getLogger(PurchaseServiceImpl.class);

    private final PurchaseRepository purchaseRepository;
    private final PurchaseValidator purchaseValidator;
    private final LineItemService lineItemService;

    @Autowired
    public PurchaseServiceImpl(
            PurchaseRepository purchaseRepository,
            PurchaseValidator purchaseValidator,
            LineItemServiceImpl lineItemService
    ) {
        this.purchaseRepository = purchaseRepository;
        this.purchaseValidator = purchaseValidator;
        this.lineItemService = lineItemService;
    }

    @Override
    public Purchase savePurchase(Purchase purchase) {

        purchaseValidator.validatePurchase(purchase);
        lineItemService.saveLineItemsFromPurchase(purchase);

        try {
            return purchaseRepository.save(purchase);
        } catch (DataAccessException dae) {
            logger.error(dae.getMessage());
            throw new ServiceUnavailable(dae.getMessage());
        }
    }

}
