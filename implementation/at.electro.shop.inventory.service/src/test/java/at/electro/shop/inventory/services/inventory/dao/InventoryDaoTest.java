package at.electro.shop.inventory.services.inventory.dao;

import at.electro.shop.inventory.services.inventory.dao.data.InventoryRepository;
import at.electro.shop.inventory.services.inventory.dao.models.InventoryEntity;
import at.electro.shop.inventory.services.inventory.mapper.InventoryMapper;
import at.electro.shop.inventory.services.inventory.models.Inventory;
import at.electro.shop.inventory.services.product.dao.data.events.EventStore;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static io.restassured.RestAssured.when;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class InventoryDaoTest {
    @Mock
    private EventStore eventStore;

    @Mock
    private InventoryRepository inventoryRepository;

    @Mock
    private InventoryMapper inventoryMapper;

    @InjectMocks
    private InventoryDao inventoryDao;

    @Test
    @DisplayName("Should get an inventory by vendor id")
    public void shouldFetchInventoryByVendor() {
        String mockVendorId = "5a48c43f-4c24-4ee5-bf75-430f83153349";
        Mockito.when(inventoryRepository.findByVendor(mockVendorId)).thenReturn(any());
        inventoryDao.fetchInventoryByVendor(mockVendorId);
        Mockito.verify(inventoryRepository).findByVendor(mockVendorId);
    }

//    TODO
//    @Test
//    @DisplayName("Should update an inventory")
//    public void shouldUpdateInventory() {
//        Mockito.when(inventoryMapper.toDto(any(InventoryEntity.class))).thenReturn(any());
//        Mockito.when(inventoryRepository.saveAndFlush(any(InventoryEntity.class))).thenReturn(any(InventoryEntity.class));
//
//        inventoryDao.updateInventory(InventoryTestModels.getInventoryEntity());
//
//        Mockito.verify(inventoryRepository).saveAndFlush(any());
//    }

    @Test
    @DisplayName("Should increase amount of product in an inventory")
    public void shouldIncreaseAmountOfProduct() {
//        TODO
    }

    @Test
    @DisplayName("Should decrease amount of product in an inventory")
    public void shouldDecreaseAmountOfProduct() {
//        TODO
    }


    @Test
    @DisplayName("Should fetch all inventories")
    public void shouldFetchAll() {
//        TODO
    }

    @Test
    @DisplayName("Should create a new inventory")
    public void shouldCreate() {
//        TODO
    }


}