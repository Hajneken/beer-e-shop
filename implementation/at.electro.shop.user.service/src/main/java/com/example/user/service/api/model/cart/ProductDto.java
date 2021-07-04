package com.example.user.service.api.model.cart;

public class ProductDto {
    String id;

    public ProductDto(String id) {
        this.id = id;
    }

    public ProductDto() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "ProductDto{" +
                "id='" + id + '\'' +
                '}';
    }
}
