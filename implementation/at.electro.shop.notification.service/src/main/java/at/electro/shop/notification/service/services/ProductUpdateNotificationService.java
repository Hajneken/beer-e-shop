package at.electro.shop.notification.service.services;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import at.electro.shop.notification.service.models.Email;
import at.electro.shop.notification.service.productMarking.models.ProductChange;

@Service
public class ProductUpdateNotificationService extends NotificationService<ProductChange> {
    private Logger LOG = LoggerFactory.getLogger(ProductUpdateNotificationService.class);
    private static final String SUBJECT = "Product on Sale!";
    private static final String TEMPLATE = "product-notification.html";

    public ProductUpdateNotificationService(Email email) {
        this.email = email;
    }

    @Override
    protected String templatePath() {
        return TEMPLATE;
    }

    @Override
    protected String subject() {
        return SUBJECT;
    }

    @Override
    protected Map<String, Object> buildTemplateMap(ProductChange product) {
        return new HashMap<String, Object>() {
            {
                put("productName", product.getProductName());
                put("productLink", product.getUuid());
                put("price", product.getPrice());
            }
        };
    }

    @Override
    protected void log(String message, Object arg) {
        LOG.error(message, arg);
    }

}
