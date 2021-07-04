package at.electro.shop.inventory.services.inventory.models;


import at.electro.shop.inventory.services.product.models.Product;

import java.util.List;

public class Inventory {

    private Long id;
    private String inventoryUuid;
    private String vendor;
    private Double inventoryValue;
    private List<Product> products;


    public List<Product> getProducts() {
        return products;
    }

    public String getInventoryUuid() {
        return inventoryUuid;
    }

    public void setInventoryUuid(String inventoryUuid) {
        this.inventoryUuid = inventoryUuid;
    }

    public Double getInventoryValue() {
        return inventoryValue;
    }

    public void setInventoryValue(Double inventoryValue) {
        this.inventoryValue = inventoryValue;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
