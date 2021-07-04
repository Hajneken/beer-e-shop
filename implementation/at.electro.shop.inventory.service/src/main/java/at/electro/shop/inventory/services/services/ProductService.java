package at.electro.shop.inventory.services.services;

import at.electro.shop.inventory.services.product.api.models.ProductRequest;
import at.electro.shop.inventory.services.product.api.models.ProductResponse;
import at.electro.shop.inventory.services.product.dao.ProductDao;
import at.electro.shop.inventory.services.product.mapper.ProductMapper;
import at.electro.shop.inventory.services.product.models.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
public class ProductService {
    //    business logic happens here, do all checks, call repository

    private ProductDao productDao;
    private ProductMapper productMapper;
    private Logger LOG = LoggerFactory.getLogger(ProductService.class);


    public ProductService(ProductDao productDao, ProductMapper productMapper) {
        this.productDao = productDao;
        this.productMapper = productMapper;
    }

    public ProductResponse getProductById(String uuid) {
        return productMapper.toApi(productDao.fetchProductByUuid(uuid));
    }

    public List<ProductResponse> getProducts() {
        return productMapper.toApiList(productDao.fetchAll());
    }

    public ProductResponse postProduct(ProductRequest productRequest) {
        if (productRequest == null) {
            LOG.info("called postProduct with empty Request");
            throw new IllegalArgumentException("Invalid request. Request body is empty");
        }
        Product product = productMapper.toDto(productRequest);
        product.setUuid(UUID.randomUUID().toString());
        Product createdProduct = productDao.insertProduct(product, productRequest.getVendor());
        return productMapper.toApi(createdProduct);
    }

    public ProductResponse modifyProduct(String uuid, ProductRequest productRequest) {
        Product product = productDao.modifyProduct(uuid, productRequest);
        return productMapper.toApi(product);
    }

    public ProductResponse deleteProductById(String uuid) {
        Product deletedProduct = productDao.removeProductById(uuid);
        return productMapper.toApi(deletedProduct);
    }

    public ProductResponse updateProductRating(String uuid, BigDecimal rating) {
        Product product = productDao.updateProductRating(uuid, rating);
        return productMapper.toApi(product);
    }

    public ProductResponse updateProductPrice(String productUuid, BigDecimal price){
        Product product = productDao.updateProductPrice(productUuid, price);
        return productMapper.toApi(product);
    }


}
