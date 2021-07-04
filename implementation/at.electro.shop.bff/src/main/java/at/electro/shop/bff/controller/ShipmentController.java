package at.electro.shop.bff.controller;

import at.electro.shop.bff.api.shipment.models.ShipmentStatus;
import at.electro.shop.bff.api.shipment.models.ShipmentUpdate;
import at.electro.shop.bff.services.ShipmentService;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class ShipmentController {
    private final ShipmentService shipmentService;

    public ShipmentController(ShipmentService shipmentService) {
        this.shipmentService = shipmentService;
    }

    @GetMapping("/shipments")
    public ResponseEntity<List<ShipmentUpdate>> get() {
        return ResponseEntity.ok(shipmentService.get());
    }

    @PostMapping("/shipment/{uuid}/status/{status}")
    public ResponseEntity<Void> postShipmentUpdateOrderConfirmation(@PathVariable String uuid,
            @PathVariable ShipmentStatus status) {
        shipmentService.postShipmentUpdateOrderConfirmation(uuid, status);
        return ResponseEntity.noContent().build();
    }
}
