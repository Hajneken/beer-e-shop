package at.electro.shop.inventory.services.services;

import at.electro.shop.inventory.services.inventory.dao.InventoryDao;
import at.electro.shop.inventory.services.inventory.mapper.InventoryMapper;
import at.electro.shop.inventory.services.inventory.models.Inventory;
import at.electro.shop.inventory.services.product.dao.ProductDao;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class InventoryServiceTest {
    @Mock
    private InventoryDao inventoryDao;

    @Mock
    private ProductDao productDao;

    @Mock
    private InventoryMapper inventoryMapper;

    @InjectMocks
    private InventoryService inventoryService;

    @Test
    @DisplayName("Should get inventory of a vendor")
    public void shouldGetInventoryById(){
//        TODO
    }

    @Test
    @DisplayName("Should calculate inventory value")
    public void shouldCalculateInventoryValue(){

//        TODO
    }

    @Test
    @DisplayName("Should update inventory")
    public void shouldUpdateInventory(){
//        TODO
    }

    @Test
    @DisplayName("Should modify product amount")
    public void shouldModifyInventoryProductAmount(){
//        TODO, most likely unnecessary
    }

}