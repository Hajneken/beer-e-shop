package at.electro.shop.notification.service.api.NotificationIntTestModels;

import java.math.BigDecimal;

import at.electro.shop.notification.service.productMarking.api.models.ProductMarkingRequest;

public class NotificationIntTestModels {
    public static ProductMarkingRequest getValidProductMarkginRequest() {
        var request = new ProductMarkingRequest();
        request.setProduct("6d8ea0ea-1162-4739-87b4-6729ef7f1a20");
        request.setUser("5a48c43f-4c24-4ee5-bf75-430f83153349");
        request.setEmailAddress("emailAddress");
        request.setPrice(new BigDecimal(20.3));
        return request;
    }

    public static final String uuid = "008865a4-8348-11eb-8dcd-0242ac131233";
    public static final String user = "008865a4-8348-11eb-8dcd-0242ac130003";
    public static final String product = "4c68e51d-04d5-4c3d-b943-8903rc0ea92f";
    public static final String email = "testsets@gmaitestse.com";
    public static final double price = 10;
}
