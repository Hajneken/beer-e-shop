package at.electro.shop.inventory.services.inventory.dao;

import at.electro.shop.inventory.services.inventory.api.models.InventoryRequest;
import at.electro.shop.inventory.services.inventory.dao.data.InventoryRepository;
import at.electro.shop.inventory.services.product.dao.data.events.EventStore;
import at.electro.shop.inventory.services.inventory.dao.models.InventoryEntity;
import at.electro.shop.inventory.services.inventory.mapper.InventoryMapper;
import at.electro.shop.inventory.services.inventory.models.Inventory;
import at.electro.shop.inventory.services.product.dao.models.ProductEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class InventoryDao {
    private EventStore eventStore;
    private InventoryRepository inventoryRepository;
    private InventoryMapper inventoryMapper;

    public InventoryDao(EventStore eventStore, InventoryRepository inventoryRepository, InventoryMapper inventoryMapper) {
        this.eventStore = eventStore;
        this.inventoryRepository = inventoryRepository;
        this.inventoryMapper = inventoryMapper;
    }

    public Inventory fetchInventoryByVendor(String uuid) {
        InventoryEntity inventoryEntity = inventoryRepository.findByVendor(uuid);
        return inventoryMapper.toDto(inventoryEntity);
    }

    public Inventory updateInventory(InventoryEntity inventoryEntity){
        InventoryEntity savedEntity =  inventoryRepository.saveAndFlush(inventoryEntity);
        return inventoryMapper.toDto(savedEntity);
    }

    public Inventory increaseInventoryProductAmountByVendorAndProduct(String vendorUuid, String productUuid){
        InventoryEntity inventoryEntity = inventoryRepository.findByVendor(vendorUuid);
        List<ProductEntity> productEntities = inventoryEntity.getProducts();

        for (ProductEntity productEntity: productEntities){
            if (productEntity.getUuid().equals(productUuid)){
                productEntities.add(productEntity);
            }
        }
        inventoryEntity.setProducts(productEntities);
        inventoryRepository.save(inventoryEntity);

        return inventoryMapper.toDto(inventoryEntity);
    }

    public Inventory decreaseInventoryProductAmountByVendorAndProduct(String vendorUuid, String productUuid){
        InventoryEntity inventoryEntity = inventoryRepository.findByVendor(vendorUuid);
        List<ProductEntity> productEntities = inventoryEntity.getProducts();
        List<ProductEntity> updatedProductEntities = new ArrayList<ProductEntity>() {};

        for (ProductEntity productEntity: productEntities){
            if (!productEntity.getUuid().equals(productUuid)){
                updatedProductEntities.add(productEntity);
            }
        }

        inventoryEntity.setProducts(updatedProductEntities);
        inventoryRepository.save(inventoryEntity);

        return inventoryMapper.toDto(inventoryEntity);
    }


    public List<Inventory> fetchAll() {
        return inventoryMapper.toDtoList(inventoryRepository.findAll());
    }

    public List<Inventory> create(List<Inventory> inventories) {
        List<InventoryEntity> entities = inventoryMapper.toDaoList(inventories);
        inventoryRepository.saveAll(entities);
        return inventoryMapper.toDtoList(entities);
    }
}
