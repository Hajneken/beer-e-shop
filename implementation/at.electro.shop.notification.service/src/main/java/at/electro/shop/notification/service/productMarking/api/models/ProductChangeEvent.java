package at.electro.shop.notification.service.productMarking.api.models;

import java.math.BigDecimal;

public class ProductChangeEvent {
    private String uuid;
    private BigDecimal price;
    private String name;

    public String getUuid() {
        return uuid;
    }

    public String getProductName() {
        return name;
    }

    public void productName(String productName) {
        this.name = productName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setUuid(String product) {
        this.uuid = product;
    }
}
