package at.electro.shop.inventory.services.inventory.api.models;

import java.util.List;

public class InventoryResponse {
    private String inventoryUuid;
    private String vendor;
    private Double inventoryValue;
    private List<ProductResult> products;


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

    public Double getInventoryValue() {
        return inventoryValue;
    }

    public void setInventoryValue(Double inventoryValue) {
        this.inventoryValue = inventoryValue;
    }

    public List<ProductResult> getProducts() {
        return products;
    }

    public void setProducts(List<ProductResult> products) {
        this.products = products;
    }
}
