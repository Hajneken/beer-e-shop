package at.electro.shop.inventory.services.inventory.mapper;

import at.electro.shop.inventory.services.inventory.api.models.InventoryRequest;
import at.electro.shop.inventory.services.inventory.api.models.InventoryResponse;
import at.electro.shop.inventory.services.inventory.dao.models.InventoryEntity;
import at.electro.shop.inventory.services.inventory.models.Inventory;
import at.electro.shop.inventory.services.product.mapper.ProductMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", uses = {ProductMapper.class})
public interface InventoryMapper {

    InventoryEntity toDao(Inventory inventory);

    Inventory toDto(InventoryEntity inventory);

    Inventory toDto(InventoryRequest inventoryRequest);

    InventoryResponse toApi(Inventory inventory);

    List<Inventory> toApiList(List<Inventory> inventories);

    List<Inventory> toDtoList(List<InventoryEntity> inventories);

    List<InventoryEntity> toDaoList(List<Inventory> inventories);
}
