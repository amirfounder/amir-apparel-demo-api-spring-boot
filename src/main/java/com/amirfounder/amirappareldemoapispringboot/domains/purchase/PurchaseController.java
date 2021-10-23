package com.amirfounder.amirappareldemoapispringboot.domains.purchase;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
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

    private final PurchaseService purchaseService;
    private final ModelMapper modelMapper;

    @Autowired
    public PurchaseController(
            PurchaseService purchaseService,
            ModelMapper modelMapper
    ) {
        this.purchaseService = purchaseService;
        this.modelMapper = modelMapper;
    }

    @PostMapping
    public ResponseEntity<PurchaseDTO> savePurchase(
            @RequestBody Purchase purchase
    ) {
        logger.info("Request received for savePurchase");
        Purchase postedPurchase = purchaseService.savePurchase(purchase);
        PurchaseDTO postedPurchaseDTO = modelMapper.map(postedPurchase, PurchaseDTO.class);
        return new ResponseEntity<>(postedPurchaseDTO, HttpStatus.CREATED);
    }

}
