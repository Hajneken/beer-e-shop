package at.electro.shop.notification.service.services;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import at.electro.shop.notification.service.models.Email;
import at.electro.shop.notification.service.shipmentUpdate.models.ShipmentUpdate;

@Service
public class ShipmentNotificationService extends NotificationService<ShipmentUpdate> {
    private Logger LOG = LoggerFactory.getLogger(ShipmentNotificationService.class);
    private static final String SUBJECT = "Shipment updated";
    private static final String TEMPLATE = "shipment-update.html";

    public ShipmentNotificationService(Email email) {
        this.email = email;
    }

    @Override
    protected String templatePath() {
        return TEMPLATE;
    }

    @Override
    protected String subject() {
        return SUBJECT;
    }

    @Override
    protected Map<String, Object> buildTemplateMap(ShipmentUpdate shipmentUpdate) {
        return new HashMap<String, Object>() {
            {
                put("address", shipmentUpdate.getAddress());
                put("productLink", shipmentUpdate.getProduct());
                put("shipmentStatus", shipmentUpdate.getStatus().getName());
            }

        };
    }

    @Override
    protected void log(String message, Object arg) {
        LOG.error(message, arg);
    }

}
