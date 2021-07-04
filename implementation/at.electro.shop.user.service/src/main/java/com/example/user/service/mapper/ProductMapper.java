package com.example.user.service.mapper;

import com.example.user.service.api.model.cart.ProductDto;
import com.example.user.service.usercart.model.cart.Product;
import org.mapstruct.Mapper;

@Mapper
public abstract class ProductMapper {
    Product toDao(ProductDto productDto) {
        Product product = new Product();
        product.setId(productDto.getId());
        return product;
    }

    ProductDto toApi(Product product) {
        ProductDto productDto = new ProductDto();
        productDto.setId(product.getId());
        return productDto;
    }
}
