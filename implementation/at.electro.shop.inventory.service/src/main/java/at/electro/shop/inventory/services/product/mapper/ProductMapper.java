package at.electro.shop.inventory.services.product.mapper;

import at.electro.shop.inventory.services.product.models.Product;
import at.electro.shop.inventory.services.product.api.models.ProductRequest;
import at.electro.shop.inventory.services.product.api.models.ProductResponse;
import at.electro.shop.inventory.services.product.dao.models.ProductEntity;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    //    From Model to DB
    ProductEntity toDao(Product product);

    //   from DB to business model -> GET
    Product toDto(ProductEntity productEntity);

    //    From POST to general
    Product toDto(ProductRequest productRequest);

    //    From general Model to GET
    ProductResponse toApi(Product product);

    List<ProductResponse> toApiList(List<Product> products);

    List<Product> toDtoList(List<ProductEntity> productEntities);
}
