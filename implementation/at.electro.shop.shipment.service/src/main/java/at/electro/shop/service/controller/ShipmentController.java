package at.electro.shop.service.controller;

import at.electro.shop.service.api.models.ShipmentStatus;
import at.electro.shop.service.api.models.ShipmentUpdate;
import at.electro.shop.service.shipment.services.ShipmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class ShipmentController {

    private Logger LOG = LoggerFactory.getLogger(ShipmentController.class);
    private final ShipmentService shipmentService;

    @Autowired
    ShipmentController(ShipmentService shipmentService) {
        this.shipmentService = shipmentService;
    }

    @GetMapping("/api/v1/shipments")
    public ResponseEntity<List<ShipmentUpdate>> get() {
        return ResponseEntity.ok(shipmentService.get());
    }

    @PostMapping("/api/v1/shipment/{uuid}/status/{status}")
    public ResponseEntity postShipmentUpdateOrderConfirmation(@PathVariable String uuid,
            @PathVariable ShipmentStatus status) {
        shipmentService.updateShipmentStatusByUuid(uuid, status);
        LOG.info("shipment update for status: " + status.toString() + " published");
        return ResponseEntity.noContent().build();
    }
}
