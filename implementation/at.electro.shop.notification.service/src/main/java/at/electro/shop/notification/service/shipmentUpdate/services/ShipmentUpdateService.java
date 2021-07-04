package at.electro.shop.notification.service.shipmentUpdate.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import at.electro.shop.notification.service.services.ShipmentNotificationService;
import at.electro.shop.notification.service.shipmentUpdate.models.ShipmentUpdate;

@Service
public class ShipmentUpdateService {
    private Logger LOG = LoggerFactory.getLogger(ShipmentUpdateService.class);
    private final ShipmentNotificationService shipmentNotificationService;

    @Autowired
    public ShipmentUpdateService(ShipmentNotificationService shipmentNotificationService) {
        this.shipmentNotificationService = shipmentNotificationService;
    }

    public void processShipmentUpdate(ShipmentUpdate update) {
        LOG.info("start processing shipment update");
        if (update == null || update.getAddress() == null) {
            throw new IllegalArgumentException("incoming update was null or invalid");
        }
        shipmentNotificationService.send(update.getAddress().getEmail(), update);
        LOG.info("proccesed shipment update");
    }
}
