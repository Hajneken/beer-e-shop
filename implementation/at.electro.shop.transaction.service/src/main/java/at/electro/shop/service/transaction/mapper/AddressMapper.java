package at.electro.shop.service.transaction.mapper;

import at.electro.shop.service.api.models.AddressRequest;
import at.electro.shop.service.transaction.dao.models.AddressEntity;
import at.electro.shop.service.transaction.models.Address;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AddressMapper {

  AddressEntity toDao(Address address);

  Address toDto(AddressEntity transactionDto);

  AddressRequest toApi(Address address);

  Address toDto(AddressRequest address);
}
