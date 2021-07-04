package at.electro.shop.notification.service.services;

import at.electro.shop.notification.service.productMarking.api.models.ProductChangeEvent;
import at.electro.shop.notification.service.productMarking.services.ProductMarkingService;
import at.electro.shop.notification.service.shipmentUpdate.models.ShipmentUpdate;
import at.electro.shop.notification.service.shipmentUpdate.services.ShipmentUpdateService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Profile("default")
@Service
public class ListenerService {

    private Logger LOG = LoggerFactory.getLogger(ShipmentUpdateService.class);
    private ShipmentUpdateService shipmentUpdateService;
    private ObjectMapper objectMapper;
    private ProductMarkingService productMarkingService;

    public ListenerService(ObjectMapper objectMapper, ShipmentUpdateService shipmentUpdateService,
        ProductMarkingService productMarkingService) {
        this.objectMapper = objectMapper;
        this.shipmentUpdateService = shipmentUpdateService;
        this.productMarkingService = productMarkingService;
    }

    @KafkaListener(topics = "shipment.updated")
    void shipmentUpdated(String data) throws JsonProcessingException {
        LOG.info(data);
        var shipmentUpdate = objectMapper.readValue(data, ShipmentUpdate.class);
        LOG.info("Deserialization successful:");
        shipmentUpdateService.processShipmentUpdate(shipmentUpdate);
    }

    @KafkaListener(topics = "product.update")
    void productChanged(String data) throws JsonProcessingException {
        LOG.info(data);
        var productChange = objectMapper.readValue(data, ProductChangeEvent.class);
        LOG.info("Deserialization successful:");
        productMarkingService.notifyUsersForChange(productChange);
    }
}
