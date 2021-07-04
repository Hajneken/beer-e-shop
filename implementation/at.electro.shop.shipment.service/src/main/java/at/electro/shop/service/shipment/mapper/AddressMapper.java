package at.electro.shop.service.shipment.mapper;


import at.electro.shop.service.shipment.dao.models.AddressEntity;
import at.electro.shop.service.shipment.models.Address;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AddressMapper {

  AddressEntity toDao(Address address);

  Address toDto(AddressEntity addressEntity);

  Address toApi(Address address);

  Address toDto(Address address);
}
