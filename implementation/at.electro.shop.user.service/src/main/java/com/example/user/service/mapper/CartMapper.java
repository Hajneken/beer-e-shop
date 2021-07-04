package com.example.user.service.mapper;


import com.example.user.service.api.model.cart.CartDto;
import com.example.user.service.api.model.cart.ProductDto;
import com.example.user.service.usercart.model.cart.Cart;
import com.example.user.service.usercart.model.cart.Product;
import org.mapstruct.Mapper;

import java.util.ArrayList;
import java.util.List;

@Mapper
public abstract class CartMapper {

    public Cart toDao(CartDto cartDto) {
        Cart cart = new Cart();
        cart.setUuid(cartDto.getUuid());

        List<Product> productList = new ArrayList<>();
        for(ProductDto p: cartDto.getProducts()) {
            productList.add(new Product(p.getId()));
        }

        cart.setProducts(productList);
        return cart;
    }
    public CartDto toApi(Cart cart) {
        CartDto cartDto = new CartDto();
        cartDto.setUuid(cart.getUuid());

        List<ProductDto> productList = new ArrayList<>();
        for(Product p: cart.getProducts()) {
            productList.add(new ProductDto(p.getId()));
        }
        cartDto.setProducts(productList);
        return cartDto;
    }
}
