package com.amirfounder.amirappareldemoapispringboot.domains.purchase;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.amirfounder.amirappareldemoapispringboot.utils.Constants.PURCHASES_PATH;

@RestController
@RequestMapping(path = PURCHASES_PATH)
public class PurchaseController {

    Logger logger = LogManager.getLogger(PurchaseController.class);

    private PurchaseService purchaseService;

    @Autowired
    public PurchaseController(PurchaseService purchaseService) {
        this.purchaseService = purchaseService;
    }

    @PostMapping
    public ResponseEntity<Purchase> savePurchase(
            @RequestBody Purchase purchase
    ) {
        logger.info("Request received for savePurchase");
        return new ResponseEntity<>(purchaseService.savePurchase(purchase), HttpStatus.CREATED);
    }

}
