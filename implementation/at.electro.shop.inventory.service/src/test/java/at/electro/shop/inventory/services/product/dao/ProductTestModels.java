package at.electro.shop.inventory.services.product.dao;

import at.electro.shop.inventory.services.product.api.models.ProductRequest;
import at.electro.shop.inventory.services.product.api.models.ProductResponse;
import at.electro.shop.inventory.services.product.dao.models.ProductEntity;
import at.electro.shop.inventory.services.product.models.Product;

import java.math.BigDecimal;
import java.util.UUID;

public class ProductTestModels {

    public static Product getProduct(){
        Product product = new Product();
        product.setUuid("276f1de7-b334-4674-9dfa-0450fcee0733");
        product.setName("Best product");
        product.setBrand("Brand");
        product.setImg("https://image.example.com");
        product.setRating(BigDecimal.valueOf(4.7));
        product.setRatingsCount(5);
        product.setDescription("Example description");
        product.setAlcoholPercent(BigDecimal.valueOf(4.7));
        product.setPrice(BigDecimal.valueOf(20.0));
        product.setCurrency("EUR");
        return product;
    }

    public static ProductEntity getProductEntity(){
        ProductEntity productEntity = new ProductEntity();
        productEntity.setId(1L);
        productEntity.setUuid("276f1de7-b334-4674-9dfa-0450fcee0733");
        productEntity.setName("Best product");
        productEntity.setBrand("Brand");
        productEntity.setImg("https://image.example.com");
        productEntity.setDescription("Example description");
        productEntity.setRating(BigDecimal.valueOf(4.7));
        productEntity.setRatingsCount(5);
        productEntity.setAlcoholPercent(BigDecimal.valueOf(4.7));
        productEntity.setPrice(BigDecimal.valueOf(20));
        productEntity.setCurrency("EUR");
        return productEntity;
    }

    public static ProductRequest getProductRequest(){
        ProductRequest productRequest = new ProductRequest();
        productRequest.setVendor("5a48c43f-4c24-4ee5-bf75-430f83153349");
        productRequest.setName("Mock A");
        productRequest.setBrand("Mock Brand");
        productRequest.setImg("https://image.example.com");
        productRequest.setRating(BigDecimal.valueOf(4.7));
        productRequest.setRatingsCount(5);
        productRequest.setDescription("Example description");
        productRequest.setAlcoholPercent(BigDecimal.valueOf(5));
        productRequest.setPrice(BigDecimal.valueOf(20));
        productRequest.setCurrency("EUR");
        return productRequest;
    }


    public static ProductResponse getProductResponse(){
        ProductResponse productResponse = new ProductResponse();
        productResponse.setUuid("321f737d-f8dd-4c9d-aca8-b8c6164a1dd1");
        productResponse.setName("Mock A");
        productResponse.setBrand("Mock Brand");
        productResponse.setImg("https://image.example.com");
        productResponse.setRating(BigDecimal.valueOf(4.7));
        productResponse.setDescription("Example description");
        productResponse.setRatingsCount(5);
        productResponse.setAlcoholPercent(BigDecimal.valueOf(4.7));
        productResponse.setPrice(BigDecimal.valueOf(4.7));
        productResponse.setCurrency("EUR");
        return productResponse;
    }
}
