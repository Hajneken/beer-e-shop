package at.electro.shop.inventory.services.controller;

import at.electro.shop.inventory.services.product.dao.ProductTestModels;
import at.electro.shop.inventory.services.services.InventoryService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class InventoryControllerTest {
    @Mock
    private InventoryService inventoryService;

    @InjectMocks
    private InventoryController inventoryController;

    @Test
    @DisplayName("Should get inventory of a vendor")
    public void shouldGetInventory(){
        String mockVendorId = "5a48c43f-4c24-4ee5-bf75-430f83153349";
        inventoryController.getInventory(mockVendorId);
        Mockito.verify(inventoryService, Mockito.times(1)).getInventoryByVendorId(mockVendorId);
    }

//    @Test
//    @DisplayName("Should modify inventory of a vendor")
//    public void shouldPutInventory(){
//        String mockVendorId = "5a48c43f-4c24-4ee5-bf75-430f83153349";
//        inventoryController.putInventory(mockVendorId);
//    }
}