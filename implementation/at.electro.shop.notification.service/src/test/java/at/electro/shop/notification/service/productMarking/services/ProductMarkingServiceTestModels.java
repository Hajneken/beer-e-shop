package at.electro.shop.notification.service.productMarking.services;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import at.electro.shop.notification.service.productMarking.ProductMarking;
import at.electro.shop.notification.service.productMarking.api.models.ProductMarkingRequest;

public class ProductMarkingServiceTestModels {
    public static List<ProductMarking> getTwoProductMarkings() {
        var list = new ArrayList<ProductMarking>();
        var marking = new ProductMarking();
        marking.setEmailAddress("emailAddress");
        marking.setPrice(new BigDecimal(29.9));
        marking.setProduct("321f737d-f8dd-4c9d-aca8-b8c6164a1dd1");
        marking.setUser("321f737d-f8dd-4c9d-aca8-b8c6164a1dd1");
        list.add(marking);
        var m = new ProductMarking();
        m.setEmailAddress("emailAddress");
        m.setPrice(new BigDecimal(15.9));
        m.setProduct("1234537d-1234-1234-1234-123456789dd1");
        m.setUser("1234537d-1234-1234-1234-123456789dd1");
        list.add(m);
        return list;
    }

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
        productMarking.setUuid(UUID.randomUUID().toString());
        return productMarking;
    }

    public static ProductMarkingRequest getProductMarkingRequest() {
        var email = "emailAddress@test.com";
        var price = new BigDecimal(10.1);
        var productId = "productId";
        var userId = "userUuid";
        var req = new ProductMarkingRequest();
        req.setEmailAddress(email);
        req.setPrice(price);
        req.setProduct(productId);
        req.setUser(userId);
        return req;
    }
}
