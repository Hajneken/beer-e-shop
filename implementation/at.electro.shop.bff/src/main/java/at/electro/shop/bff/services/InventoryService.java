package at.electro.shop.bff.services;

import at.electro.shop.bff.api.inventory.models.inventory.InventoryRequest;
import at.electro.shop.bff.api.inventory.models.inventory.InventoryResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "${clients.inventory.name}", url = "${clients.inventory.url}")
public interface InventoryService {
    @RequestMapping(method = RequestMethod.GET, value = "/api/v1/vendor/{vendorId}/inventory", produces = "application/json")
    InventoryResponse getInventoryByVendorId(@PathVariable String vendorId);

    @RequestMapping(method = RequestMethod.PUT, value = "/api/v1/vendor/{vendorId}/inventory", produces = "application/json")
    InventoryResponse updateInventory(@RequestBody InventoryRequest inventoryRequest);
}
