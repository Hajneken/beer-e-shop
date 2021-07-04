package at.electro.shop.inventory.services.product.dao;

import at.electro.shop.inventory.services.inventory.dao.InventoryDao;
import at.electro.shop.inventory.services.inventory.dao.models.InventoryEntity;
import at.electro.shop.inventory.services.inventory.mapper.InventoryMapper;
import at.electro.shop.inventory.services.inventory.models.Inventory;
import at.electro.shop.inventory.services.product.api.models.ProductRequest;
import at.electro.shop.inventory.services.product.dao.data.ProductRepository;
import at.electro.shop.inventory.services.product.dao.data.events.EventStore;
import at.electro.shop.inventory.services.product.dao.models.ProductEntity;
import at.electro.shop.inventory.services.product.mapper.ProductMapper;
import at.electro.shop.inventory.services.product.models.Product;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ProductDao {

    private Logger LOG = LoggerFactory.getLogger(ProductDao.class);

    private ProductRepository productRepository;
    private ProductMapper productMapper;
    private InventoryMapper inventoryMapper;
    private InventoryDao inventoryDao;
    private EventStore eventStore;

    public ProductDao(ProductRepository productRepository, ProductMapper productMapper, InventoryMapper inventoryMapper,
        InventoryDao inventoryDao, EventStore eventStore) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
        this.inventoryMapper = inventoryMapper;
        this.inventoryDao = inventoryDao;
        this.eventStore = eventStore;
    }

    public Product fetchProductByUuid(String uuid) {
        var product = productRepository.findByUuid(uuid);
        return productMapper.toDto(product);
    }

    public List<Product> fetchAll() {
        return productMapper.toDtoList(productRepository.findAll());
    }

    public Product insertProduct(Product product, String vendorUuid) {
        ProductEntity productEntity = productMapper.toDao(product);
        Inventory inventory = inventoryDao.fetchInventoryByVendor(vendorUuid);

        productEntity.setModifiedAt(LocalDateTime.now());
        productEntity.setUuid(UUID.randomUUID().toString());

        InventoryEntity inventoryEntity = inventoryMapper.toDao(inventory);
        productEntity.setInventory(inventoryEntity);

        ProductEntity savedProductEntity = productRepository.save(productEntity);
        return productMapper.toDto(savedProductEntity);
    }

    public Product modifyProduct(String uuid, ProductRequest productRequest) {
        Product product = productMapper.toDto(productRequest);
        boolean priceNotChanged = product.getPrice().doubleValue() == productRequest.getPrice().doubleValue();
        if (!priceNotChanged) {
            // Publishing to Kafka Here
            var productToPublish = productMapper.toDto(productRequest);
            eventStore.publish(productToPublish);
        }
        ProductEntity productEntity = productMapper.toDao(product);
        ProductEntity productEntityRetrieved = productRepository.findByUuid(uuid);
        productEntity.setUuid(productEntityRetrieved.getUuid());
        productEntity.setId(productEntityRetrieved.getId());
        productEntity.setModifiedAt(LocalDateTime.now());
        productEntity.setInventory(productEntityRetrieved.getInventory());

        var savedProduct = productRepository.save(productEntity);
        return productMapper.toDto(savedProduct);
    }

    public Product removeProductById(String uuid) {
        var productEntity = productRepository.findByUuid(uuid);
        if (productEntity == null) {
            System.out.println("productEntity" + productEntity + " does not exist");
            throw new IllegalArgumentException("Invalid request. Request body is empty");
        }
        productRepository.delete(productEntity);
        return productMapper.toDto(productEntity);
    }

    public Integer countProductsByNameAndBrand(String name, String brand) {
        return productRepository.countByNameAndBrand(name, brand);
    }

    public Product updateProductRating(String uuid, BigDecimal rating) {
        ProductEntity productEntity = productRepository.findByUuid(uuid);

        Integer oldRatingsCount = productEntity.getRatingsCount();
        Integer newRatingsCount = oldRatingsCount + 1;
        BigDecimal oldRating = productEntity.getRating();

        BigDecimal productScore = oldRating.multiply(BigDecimal.valueOf(oldRatingsCount));
        BigDecimal newRatingScore = productScore.add(rating);
        BigDecimal newRating = newRatingScore.divide(BigDecimal.valueOf(newRatingsCount), 2, RoundingMode.HALF_UP);

        productEntity.setRatingsCount(newRatingsCount);
        productEntity.setRating(newRating);
        productEntity.setModifiedAt(LocalDateTime.now());

        productRepository.save(productEntity);
        Product product = productMapper.toDto(productEntity);
        product.setRating(newRating.setScale(1, RoundingMode.HALF_UP));

        return product;
    }

    public Product updateProductPrice(String productUuid, BigDecimal price) {
        ProductEntity productEntity = productRepository.findByUuid(productUuid);

        Product product = productMapper.toDto(productEntity);
        boolean priceNotChanged = productEntity.getPrice().doubleValue() == price.doubleValue();
        if (!priceNotChanged) {
            LOG.error("Price is different");
            // Publishing to Kafka Here
            productEntity.setPrice(price);
            productEntity.setModifiedAt(LocalDateTime.now());

            eventStore.publish(product);
            productRepository.save(productEntity);
        }
        return product;
    }
}
