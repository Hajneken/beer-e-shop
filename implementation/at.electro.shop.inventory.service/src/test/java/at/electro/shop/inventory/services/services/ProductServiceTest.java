package at.electro.shop.inventory.services.services;

import at.electro.shop.inventory.services.product.dao.ProductDao;
import at.electro.shop.inventory.services.product.dao.ProductTestModels;
import at.electro.shop.inventory.services.product.mapper.ProductMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {


    @Mock
    private ProductMapper productMapper;

    @Mock
    private ProductDao productDao;

    @InjectMocks
    private ProductService productService;

    @Test
    @DisplayName("Should update product rating")
    public void shouldUpdateProductRating() {
        when(productDao.updateProductRating(any(), any())).thenReturn(ProductTestModels.getProduct());
        when(productMapper.toApi(any())).thenReturn(ProductTestModels.getProductResponse());

        productService.updateProductRating(any(), any());

        verify(productDao).updateProductRating(any(), any());
        verify(productMapper).toApi(any());
    }
}