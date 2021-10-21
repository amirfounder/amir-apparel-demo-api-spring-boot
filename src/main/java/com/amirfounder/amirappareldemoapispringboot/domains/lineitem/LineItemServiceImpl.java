package com.amirfounder.amirappareldemoapispringboot.domains.lineitem;

import com.amirfounder.amirappareldemoapispringboot.domains.purchase.Purchase;
import com.amirfounder.amirappareldemoapispringboot.exceptions.ServiceUnavailable;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class LineItemServiceImpl implements LineItemService {

    Logger logger = LogManager.getLogger(LineItemServiceImpl.class);

    private final LineItemRepository lineItemRepository;

    @Autowired
    public LineItemServiceImpl(LineItemRepository lineItemRepository) {
        this.lineItemRepository = lineItemRepository;
    }

    @Override
    public void saveLineItemsFromPurchase(Purchase purchase) {

        Set<LineItem> lineItems = purchase.getLineItems();

        lineItems.forEach(lineItem -> {
           lineItem.setPurchase(purchase);

            try {
                lineItemRepository.save(lineItem);
            } catch (DataAccessException dae) {
                logger.error(dae.getMessage());
                throw new ServiceUnavailable(dae.getMessage());
            }

        });
    }

}
