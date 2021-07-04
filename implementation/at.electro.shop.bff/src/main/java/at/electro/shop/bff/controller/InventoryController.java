package at.electro.shop.bff.controller;

import at.electro.shop.bff.api.inventory.models.inventory.InventoryRequest;
import at.electro.shop.bff.api.inventory.models.inventory.InventoryResponse;
import at.electro.shop.bff.services.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class InventoryController {
    private final InventoryService inventoryService;

    @Autowired
    InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    //    Get products from an Inventory of a vendor
    @GetMapping("/api/v1/vendor/{vendorId}/inventory")
    public ResponseEntity<InventoryResponse> getInventory(@PathVariable String vendorId) {
        return ResponseEntity.ok(inventoryService.getInventoryByVendorId(vendorId));
    }

    //    Update inventory of a vendor
    @PutMapping("/api/v1/vendor/{vendorId}/inventory")
    public ResponseEntity<InventoryResponse> putInventory(@RequestBody InventoryRequest inventoryRequest, @PathVariable String vendorId) {
        return ResponseEntity.ok(inventoryService.updateInventory(inventoryRequest));
    }

}
