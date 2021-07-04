package at.electro.shop.notification.service.productMarking.services;

import java.math.BigDecimal;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import at.electro.shop.notification.service.productMarking.api.models.ProductChangeEvent;
import at.electro.shop.notification.service.productMarking.api.models.ProductMarkingRequest;
import at.electro.shop.notification.service.productMarking.api.models.ProductMarkingResponse;
import at.electro.shop.notification.service.productMarking.dao.ProductMarkingDAO;
import at.electro.shop.notification.service.productMarking.mapper.ProductChangeMapper;
import at.electro.shop.notification.service.productMarking.mapper.ProductMarkingMapper;
import at.electro.shop.notification.service.services.ProductUpdateNotificationService;

@Service
public class ProductMarkingService {
    private Logger LOG = LoggerFactory.getLogger(ProductMarkingService.class);
    private final ProductMarkingDAO productMarkingDAO;
    private ProductMarkingMapper productMarkingMapper;
    private ProductChangeMapper productChangeMapper;

    private final ProductUpdateNotificationService productUpdateNotificationService;

    public ProductMarkingService(ProductMarkingDAO productMarkingDAO,
            ProductUpdateNotificationService notificationService, ProductChangeMapper productChangeMapper,
            ProductMarkingMapper productMarkingMapper) {
        this.productMarkingDAO = productMarkingDAO;
        this.productUpdateNotificationService = notificationService;
        this.productChangeMapper = productChangeMapper;
        this.productMarkingMapper = productMarkingMapper;
    }

    public ProductMarkingResponse addProductMarking(ProductMarkingRequest req) {
        if (req == null) {
            LOG.info("called addProductMarking with empty Request");
            throw new IllegalArgumentException("Invalid request. Request body is empty");
        }

        var result = productMarkingDAO.saveMarking(productMarkingMapper.toDto(req));

        return productMarkingMapper.toApi(result);
    }

    public List<ProductMarkingResponse> getByUserId(String userId) {
        if (userId == null || userId.isEmpty()) {
            LOG.info("Tried to get Productmarking by empty or null Userid.");
            throw new IllegalArgumentException("Userid is null or empty");
        }
        var result = productMarkingDAO.fetchByUser(userId);
        return productMarkingMapper.toApiList(result);
    }

    public void delete(String id) {
        if (id == null || id.isEmpty()) {
            LOG.info("Tried to delete Productmarking by null or empty id.");
            throw new IllegalArgumentException("id is null or empty");
        }
        productMarkingDAO.delete(id);
    }

    public void notifyUsersForChange(ProductChangeEvent event) {
        if (event == null || event.getUuid() == null || event.getUuid().equals("")) {
            LOG.info("called notifyUsersForChaneg with empty Request or no productId");
            throw new IllegalArgumentException("Invalid event");
        }

        var productChange = productChangeMapper.toDto(event);
        LOG.info(productChange.getUuid());
        var markings = productMarkingDAO.getProductMarkingByProductId(productChange.getUuid());

        LOG.info("SEND MAIL");
        LOG.info(Integer.toString(markings.size()));
        markings.stream().filter((var marking) -> biggerThan(marking.getPrice(), productChange.getPrice())).forEach(
                (var marking) -> {
                    LOG.info("EMAIL IS ACTUALLY SENT");
                    productUpdateNotificationService.send(marking.getEmailAddress(), productChange);
                });
    }

    private boolean biggerThan(BigDecimal ldec, BigDecimal rdec) {
        LOG.info(ldec.toString());
        LOG.info(rdec.toString());
        return ldec.compareTo(rdec) > 0;
    }
}
