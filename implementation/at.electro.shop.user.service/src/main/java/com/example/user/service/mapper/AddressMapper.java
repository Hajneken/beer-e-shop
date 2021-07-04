package com.example.user.service.mapper;

import com.example.user.service.api.model.user.AddressDto;
import com.example.user.service.usercart.model.user.Address;
import org.mapstruct.Mapper;

@Mapper
public interface AddressMapper {
    Address toDao(AddressDto addressDto);
    AddressDto toApi(Address address);
}
