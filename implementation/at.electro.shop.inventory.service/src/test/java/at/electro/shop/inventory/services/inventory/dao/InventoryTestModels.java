package at.electro.shop.inventory.services.inventory.dao;

import at.electro.shop.inventory.services.inventory.dao.models.InventoryEntity;
import at.electro.shop.inventory.services.inventory.mapper.InventoryMapper;
import at.electro.shop.inventory.services.inventory.models.Inventory;
import at.electro.shop.inventory.services.product.dao.ProductTestModels;
import at.electro.shop.inventory.services.product.dao.models.ProductEntity;
import at.electro.shop.inventory.services.product.models.Product;

import java.util.ArrayList;
import java.util.List;

public class InventoryTestModels {
    public static Inventory getInventory(){
        Inventory inventory = new Inventory();
        Product product = ProductTestModels.getProduct();
        List<Product> productList = new ArrayList<>();
        productList.add(product);

        inventory.setId(1L);
        inventory.setInventoryUuid("8fd31a0b-1ffb-4e17-8a90-645f1110855b");
        inventory.setVendor("5a48c43f-4c24-4ee5-bf75-430f83153349");
        inventory.setProducts(productList);
        inventory.setInventoryValue(192.0);

        return inventory;
    }

    public static InventoryEntity getInventoryEntity(){
        InventoryEntity inventoryEntity = new InventoryEntity();
        List<ProductEntity> productEntityList = new ArrayList<>();
        ProductEntity productEntity = ProductTestModels.getProductEntity();
        productEntityList.add(productEntity);

        inventoryEntity.setId(1L);
        inventoryEntity.setinventoryUuid("8fd31a0b-1ffb-4e17-8a90-645f1110855b");
        inventoryEntity.setVendor("5a48c43f-4c24-4ee5-bf75-430f83153349");
        inventoryEntity.setProducts(productEntityList);

        return inventoryEntity;
    }

}
