package at.electro.shop.notification.service.productMarking.dao;

import java.math.BigDecimal;

import at.electro.shop.notification.service.productMarking.ProductMarking;

public class ProductMarkingDAOTestModels {

    public static ProductMarking getProductMarking() {
        var email = "emailAddress@test.com";
        var price = new BigDecimal(10.1);
        var productId = "productId";
        var user = "userUuid";
        var productMarking = new ProductMarking();
        productMarking.setEmailAddress(email);
        productMarking.setPrice(price);
        productMarking.setProduct(productId);
        productMarking.setUser(user);
        return productMarking;
    }
}
