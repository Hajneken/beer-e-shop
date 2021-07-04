package at.electro.shop.bff.api.user.models.cart;

import java.util.ArrayList;
import java.util.List;


public class CartDto {

    private String uuid;

    private List<ProductDto> products = new ArrayList<>();

    public CartDto(List<ProductDto> products) {
        this.products = products;
    }

    public CartDto() {

    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public List<ProductDto> getProducts() {
        return products;
    }

    public void setProducts(List<ProductDto> products) {
        this.products = products;
    }

}
