package at.electro.shop.bff.services;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import at.electro.shop.bff.api.shipment.models.ShipmentStatus;
import at.electro.shop.bff.api.shipment.models.ShipmentUpdate;

@FeignClient(name = "${clients.shipment.name}", url = "${clients.shipment.url}")
public interface ShipmentService {
    @RequestMapping(method = RequestMethod.GET, value = "/api/v1/shipments", produces = "application/json")
    List<ShipmentUpdate> get();

    @RequestMapping(method = RequestMethod.POST, value = "/api/v1/shipment/{uuid}/status/{status}", produces = "application/json")
    public void postShipmentUpdateOrderConfirmation(@PathVariable String uuid, @PathVariable ShipmentStatus status);
}
