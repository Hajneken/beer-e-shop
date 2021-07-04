package at.electro.shop.notification.service.productMarking.api.models;

import java.math.BigDecimal;

public class ProductMarkingRequest {
    private String user;
    private String emailAddress;
    private String product;
    private BigDecimal price;

    public BigDecimal getPrice() {
        return price;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

}
