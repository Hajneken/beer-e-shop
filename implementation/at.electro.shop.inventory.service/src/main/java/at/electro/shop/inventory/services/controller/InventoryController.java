package at.electro.shop.inventory.services.controller;


import at.electro.shop.inventory.services.inventory.api.models.InventoryRequest;
import at.electro.shop.inventory.services.inventory.api.models.InventoryResponse;
import at.electro.shop.inventory.services.services.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
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

