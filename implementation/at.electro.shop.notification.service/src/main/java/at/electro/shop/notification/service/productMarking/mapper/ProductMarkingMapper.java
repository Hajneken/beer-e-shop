package at.electro.shop.notification.service.productMarking.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import at.electro.shop.notification.service.productMarking.ProductMarking;
import at.electro.shop.notification.service.productMarking.api.models.ProductMarkingRequest;
import at.electro.shop.notification.service.productMarking.api.models.ProductMarkingResponse;
import at.electro.shop.notification.service.productMarking.dao.models.ProductMarkingEntity;

@Mapper(componentModel = "spring")
public interface ProductMarkingMapper {

    ProductMarkingMapper INSTANCE = Mappers.getMapper(ProductMarkingMapper.class);

    ProductMarking toDto(ProductMarkingEntity productMarkingDAO);

    ProductMarking toDto(ProductMarkingRequest productMarkingRequest);

    ProductMarkingEntity toDao(ProductMarking productMarking);

    ProductMarkingResponse toApi(ProductMarking productMarking);

    List<ProductMarkingResponse> toApiList(List<ProductMarking> productMarking);

    List<ProductMarking> toDtoList(List<ProductMarkingEntity> productChangeEvent);
}
