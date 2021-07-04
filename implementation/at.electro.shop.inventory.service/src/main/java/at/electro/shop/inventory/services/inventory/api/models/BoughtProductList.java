package at.electro.shop.inventory.services.inventory.api.models;

import java.util.List;

public class BoughtProductList {
    List<String> productUuids;
    String vendor;

    public List<String> getProductUuids() {
        return productUuids;
    }

    public void setProductUuids(List<String> productUuids) {
        this.productUuids = productUuids;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }
}
