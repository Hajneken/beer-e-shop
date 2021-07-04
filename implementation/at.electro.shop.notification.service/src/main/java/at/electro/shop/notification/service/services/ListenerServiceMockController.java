package at.electro.shop.notification.service.services;

import com.fasterxml.jackson.core.JsonProcessingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import at.electro.shop.notification.service.productMarking.api.models.ProductChangeEvent;
import at.electro.shop.notification.service.productMarking.services.ProductMarkingService;
import at.electro.shop.notification.service.shipmentUpdate.models.ShipmentUpdate;
import at.electro.shop.notification.service.shipmentUpdate.services.ShipmentUpdateService;

@Controller
@Profile("dev")
public class ListenerServiceMockController {

    private Logger LOG = LoggerFactory.getLogger(ListenerServiceMockController.class);
    private ShipmentUpdateService shipmentUpdateService;
    private ProductMarkingService productMarkingService;

    @Autowired
    public ListenerServiceMockController(ShipmentUpdateService shipmentUpdateService,
            ProductMarkingService productMarkingService) {
        this.shipmentUpdateService = shipmentUpdateService;
        this.productMarkingService = productMarkingService;
    }

    @PostMapping("/shipment/updated")
    ResponseEntity<String> shipmentUpdated(@RequestBody ShipmentUpdate shipmentUpdate) throws JsonProcessingException {
        LOG.info("incoming shipment update");
        shipmentUpdateService.processShipmentUpdate(shipmentUpdate);
        return ResponseEntity.ok("ok");
    }

    @PostMapping("/product/changed")
    ResponseEntity<String> productChanged(@RequestBody ProductChangeEvent productChange)
            throws JsonProcessingException {
        LOG.info("incoming product change");
        productMarkingService.notifyUsersForChange(productChange);
        return ResponseEntity.ok("ok");
    }
}
