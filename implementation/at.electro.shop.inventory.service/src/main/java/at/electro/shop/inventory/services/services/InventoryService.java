package at.electro.shop.inventory.services.services;

import at.electro.shop.inventory.services.inventory.api.models.InventoryRequest;
import at.electro.shop.inventory.services.inventory.api.models.InventoryResponse;
import at.electro.shop.inventory.services.inventory.api.models.ProductResult;
import at.electro.shop.inventory.services.inventory.dao.InventoryDao;
import at.electro.shop.inventory.services.inventory.mapper.InventoryMapper;
import at.electro.shop.inventory.services.inventory.models.Inventory;
import at.electro.shop.inventory.services.product.dao.ProductDao;
import at.electro.shop.inventory.services.product.models.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class InventoryService {

    private final InventoryDao inventoryDao;
    private ProductDao productDao;
    private InventoryMapper inventoryMapper;
    private Logger LOG = LoggerFactory.getLogger(InventoryService.class);

    public InventoryService(InventoryDao inventoryDao, ProductDao productDao, InventoryMapper inventoryMapper) {
        this.inventoryDao = inventoryDao;
        this.productDao = productDao;
        this.inventoryMapper = inventoryMapper;
    }

    public InventoryResponse getInventoryByVendorId(String uuid) {
        Inventory inventory = inventoryDao.fetchInventoryByVendor(uuid);
        double inventoryValue = calculateInventoryValue(inventory);
        inventory.setInventoryValue(inventoryValue);

        var updatedProducts = inventory.getProducts();

        Set<Product> resultSet = updatedProducts.stream().collect(Collectors.toCollection(HashSet::new));
        List<ProductResult> productResults = new ArrayList<>();
//        Results of looping through Set
        for (Product product : resultSet) {
            ProductResult productResult = new ProductResult();
            productResult.setProduct(product);
            productResult.setAmount(productDao.countProductsByNameAndBrand(product.getName(), product.getBrand()));
            productResults.add(productResult);
        }
        var resultInventory = inventoryMapper.toApi(inventory);
        resultInventory.setProducts(productResults);

        return resultInventory;
    }

    private double calculateInventoryValue(Inventory inventory) {
        List<Product> products = inventory.getProducts();
        double value = 0;
        if (!products.isEmpty()){
            for (Product product : products) {
                value += product.getPrice().doubleValue();
            }
        }
        return value;
    }

    public InventoryResponse updateInventory(InventoryRequest inventoryRequest) {
        if (inventoryRequest == null) {
            LOG.info("Called update inventory with empty Request");
            throw new IllegalArgumentException("Invalid request. Request body is empty");
        }

        List<Product> productsToIncrease = inventoryRequest.getProductsToAdd();
        List<String> productsToRemove = inventoryRequest.getProductIdListToRemove();

        if (!productsToRemove.isEmpty()) {
            for (String productId :
                    productsToRemove) {
                productDao.removeProductById(productId);
            }
        }

        if (!productsToIncrease.isEmpty()) {
            for (Product product : productsToIncrease) {
                productDao.insertProduct(product, inventoryRequest.getVendor());
            }
        }

        Inventory updatedInventory = inventoryDao.fetchInventoryByVendor(inventoryRequest.getVendor());
        updatedInventory.setInventoryValue(calculateInventoryValue(updatedInventory));

        List<Product> updatedProducts = updatedInventory.getProducts();
        Set<Product> resultSet = updatedProducts.stream().collect(Collectors.toCollection(HashSet::new));

        List<ProductResult> productResults = new ArrayList<>();

        for (Product product : resultSet) {
            ProductResult productResult = new ProductResult();
            productResult.setProduct(product);
            productResult.setAmount(productDao.countProductsByNameAndBrand(product.getName(), product.getBrand()));
            productResults.add(productResult);
        }

        InventoryResponse inventoryResponse = inventoryMapper.toApi(updatedInventory);
        inventoryResponse.setProducts(productResults);

        return inventoryResponse;
    }

    //    When the transaction arrives -> modify the amount of a product in an inventory by removing it from the product list
    public InventoryResponse modifyInventoryProductAmount(String vendorUuid, String productUuid, boolean increase) {
        Inventory inventory;
        if (increase) {
            inventory = inventoryDao.increaseInventoryProductAmountByVendorAndProduct(vendorUuid, productUuid);
        } else {
            inventory = inventoryDao.decreaseInventoryProductAmountByVendorAndProduct(vendorUuid, productUuid);
        }
        return inventoryMapper.toApi(inventory);
    }

}