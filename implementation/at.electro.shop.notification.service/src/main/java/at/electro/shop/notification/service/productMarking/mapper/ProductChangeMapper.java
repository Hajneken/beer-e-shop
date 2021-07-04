package at.electro.shop.notification.service.productMarking.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import at.electro.shop.notification.service.productMarking.api.models.ProductChangeEvent;
import at.electro.shop.notification.service.productMarking.models.ProductChange;

@Mapper(componentModel = "spring")
public interface ProductChangeMapper {

    ProductChangeMapper INSTANCE = Mappers.getMapper(ProductChangeMapper.class);

    ProductChange toDto(ProductChangeEvent productChangeEvent);
}