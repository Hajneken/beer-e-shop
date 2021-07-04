package at.electro.shop.bff.api.inventory.models.inventory;

import at.electro.shop.bff.api.inventory.models.product.Product;


import java.util.List;

public class InventoryRequest {

    private String inventoryUuid;
    private String vendor;
    private List<String> productIdListToRemove;
    private List<Product> productsToAdd;

    public String getInventoryUuid() {
        return inventoryUuid;
    }

    public void setInventoryUuid(String inventoryUuid) {
        this.inventoryUuid = inventoryUuid;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public List<String> getProductIdListToRemove() {
        return productIdListToRemove;
    }

    public void setProductIdListToRemove(List<String> productIdListToRemove) {
        this.productIdListToRemove = productIdListToRemove;
    }

    public List<Product> getProductsToAdd() {
        return productsToAdd;
    }

    public void setProductsToAdd(List<Product> productsToAdd) {
        this.productsToAdd = productsToAdd;
    }
}
