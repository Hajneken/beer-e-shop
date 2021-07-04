package at.electro.shop.inventory.services.controller;

import at.electro.shop.inventory.services.product.api.models.ProductRequest;
import at.electro.shop.inventory.services.product.dao.ProductTestModels;
import at.electro.shop.inventory.services.services.ProductService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class ProductControllerTest {
    @Mock
    private ProductService productService;

    @InjectMocks
    private ProductController productController;

    @Test
    @DisplayName("Should get all products")
    public void shouldGetProducts(){
        productController.getProduct();
        Mockito.verify(productService,  Mockito.times(1)).getProducts();
    }

    @Test
    @DisplayName("Should get a product")
    public void shouldGetProduct(){
        String mockId = "321f737d-f8dd-4c9d-aca8-b8c6164a1dd1";
        productController.getProduct(mockId);
        Mockito.verify(productService,  Mockito.times(1)).getProductById(mockId);
    }

    @Test
    @DisplayName("Should modify a product")
    public void shouldPutProduct(){
        ProductRequest mockProductRequest = ProductTestModels.getProductRequest();
        String mockProductId = "321f737d-f8dd-4c9d-aca8-b8c6164a1dd1";
        productController.putProduct(mockProductId, mockProductRequest);
        Mockito.verify(productService, Mockito.times(1)).modifyProduct(mockProductId, mockProductRequest);
    }

    @Test
    @DisplayName("Should modify rating of a product")
    public void shouldModifyRating(){
        ProductRequest mockProductRequest = ProductTestModels.getProductRequest();
        String mockProductId = "5a48c43f-4c24-4ee5-bf75-430f83153349";
        productController.updateProductRating(mockProductId, mockProductRequest);
        Mockito.verify(productService, Mockito.times(1)).updateProductRating(mockProductId, mockProductRequest.getRating());
    }

    @Test
    @DisplayName("Should update price of a product")
    public void shouldUpdatePrice(){
        ProductRequest mockProductRequest = ProductTestModels.getProductRequest();
        String mockProductId = "5a48c43f-4c24-4ee5-bf75-430f83153349";
        productController.updatePrice(mockProductId, mockProductRequest);
        Mockito.verify(productService,Mockito.times(1)).updateProductPrice(mockProductId, mockProductRequest.getPrice());
    }

}