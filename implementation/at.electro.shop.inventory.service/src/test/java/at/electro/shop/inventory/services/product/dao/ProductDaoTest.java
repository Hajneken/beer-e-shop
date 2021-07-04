package at.electro.shop.inventory.services.product.dao;

import at.electro.shop.inventory.services.inventory.dao.InventoryDao;
import at.electro.shop.inventory.services.inventory.mapper.InventoryMapper;
import at.electro.shop.inventory.services.product.api.models.ProductRequest;
import at.electro.shop.inventory.services.product.dao.data.ProductRepository;
import at.electro.shop.inventory.services.product.dao.models.ProductEntity;
import at.electro.shop.inventory.services.product.mapper.ProductMapper;
import at.electro.shop.inventory.services.product.models.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class ProductDaoTest {
    @Mock
    private ProductMapper productMapper;

    @Mock
    private InventoryMapper inventoryMapper;

    @Mock
    private InventoryDao inventoryDao;

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductDao productDao;

    @Test
    @DisplayName("Should Insert Product")
    public void shouldInsertProduct() {
        String testedUuid = "276f1de7-b334-4674-9dfa-0450fcee0733";
        when(productMapper.toDao(any())).thenReturn(ProductTestModels.getProductEntity());

        var entity = productDao.insertProduct(ProductTestModels.getProduct(), testedUuid);

        verify(productRepository).save(any());
    }

    @Test
    @DisplayName("Should get product by Id")
    public void shouldGetProductById() {
        when(productRepository.findByUuid(any())).thenReturn(ProductTestModels.getProductEntity());
        productDao.fetchProductByUuid(ProductTestModels.getProduct().getUuid());
        verify(productRepository).findByUuid(any());
    }

// TODO
//    @Test
//    @DisplayName("Should modify product")
//    public void shouldModifyProduct() {
//        String testedUuid = "276f1de7-b334-4674-9dfa-0450fcee0733";
//
//        when(productMapper.toDto(any(ProductRequest.class))).thenReturn(ProductTestModels.getProduct());
//        when(productMapper.toDao(any())).thenReturn(any());
//        when(productRepository.save(any())).thenReturn(ProductTestModels.getProductEntity());
//        when(productRepository.findByUuid(any())).thenReturn(ProductTestModels.getProductEntity());
//
//        var product = productDao.modifyProduct(testedUuid, ProductTestModels.getProductRequest());
//
//        verify(productRepository).findByUuid(testedUuid);
//        verify(productRepository).save(any());
//    }

    @Test
    @DisplayName("Should count products by name and brand")
    public void shouldCountProductsByNameAndBrand() {
        when(productRepository.countByNameAndBrand(any(), any())).thenReturn(2);
        productDao.countProductsByNameAndBrand(any(), any());
        verify(productRepository).countByNameAndBrand(any(), any());
    }

    @Test
    @DisplayName("Should get all products")
    public void shouldFetchAll() {
        when(productRepository.findAll()).thenReturn(List.of(ProductTestModels.getProductEntity()));

        List<Product> products = productDao.fetchAll();
        verify(productRepository).findAll();
    }

    @Test
    @DisplayName("Should update product rating")
    public void shouldUpdateProductRating() {
        when(productRepository.findByUuid(any())).thenReturn(ProductTestModels.getProductEntity());
        when(productRepository.save(any())).thenReturn(ProductTestModels.getProductEntity());
        when(productMapper.toDto(any(ProductEntity.class))).thenReturn(ProductTestModels.getProduct());

        productDao.updateProductRating(ProductTestModels.getProductEntity().getUuid(), ProductTestModels.getProductEntity().getRating());

        verify(productRepository).save(any());
        verify(productMapper).toDto(any(ProductEntity.class));

        Assertions.assertEquals(BigDecimal.valueOf(4.7), ProductTestModels.getProductEntity().getRating());
    }

}