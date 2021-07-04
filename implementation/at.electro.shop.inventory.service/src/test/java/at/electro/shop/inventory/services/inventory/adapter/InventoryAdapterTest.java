package at.electro.shop.inventory.services.inventory.adapter;

import at.electro.shop.inventory.services.inventory.api.models.BoughtProductList;
import at.electro.shop.inventory.services.inventory.models.Inventory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class InventoryAdapterTest {

    @InjectMocks
    private InventoryAdapter inventoryAdapter;

    @Test
    @DisplayName("Should convert transaction to list of bought products")
    public void shouldConvertTransactionToInventory() {
        List<BoughtProductList> boughtProductLists = inventoryAdapter.convertTransactionToInventory(TransactionTestModels.getTransactions());

        assertEquals(1, boughtProductLists.size());
        assertEquals("321f737d-f8dd-4c9d-aca8-b8c6164a1dd1", boughtProductLists.get(0).getProductUuids().get(0));
    }
}