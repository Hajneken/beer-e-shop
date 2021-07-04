package at.electro.shop.service.shipment.mapper;

import at.electro.shop.service.api.models.ShipmentUpdate;
import at.electro.shop.service.shipment.dao.models.ShipmentEntity;
import at.electro.shop.service.shipment.models.Shipment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", uses = { AddressMapper.class })
public interface ShipmentMapper {

  ShipmentMapper INSTANCE = Mappers.getMapper(ShipmentMapper.class);

  @Mapping(source = "address", target = "address")
  ShipmentEntity toDao(Shipment shipment);

  @Mapping(source = "address", target = "address")
  Shipment toDto(ShipmentEntity shipmentEntity);

  @Mapping(source = "address", target = "address")
  ShipmentUpdate toApi(Shipment shipment);

  @Mapping(source = "address", target = "address")
  Shipment toDto(ShipmentUpdate shipmentUpdate);

  List<ShipmentUpdate> toApiList(List<Shipment> shipments);

  List<Shipment> toDtoList(List<ShipmentEntity> shipments);

  List<ShipmentEntity> toDaoList(List<Shipment> shipments);
}
