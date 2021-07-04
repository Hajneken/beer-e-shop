package at.electro.shop.notification.service.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import at.electro.shop.notification.service.productMarking.api.models.ProductMarkingRequest;
import at.electro.shop.notification.service.productMarking.api.models.ProductMarkingResponse;
import at.electro.shop.notification.service.productMarking.services.ProductMarkingService;

@Controller
public class ProductMarkingController {
    private Logger LOG = LoggerFactory.getLogger(ProductMarkingService.class);
    private final ProductMarkingService productMarkingService;

    @Autowired
    public ProductMarkingController(ProductMarkingService productMarkingService) {
        this.productMarkingService = productMarkingService;
    }

    @PostMapping("/api/v1/productmarking")
    public ResponseEntity<ProductMarkingResponse> marking(@RequestBody ProductMarkingRequest req) {
        LOG.debug("incoming productmarking post request:" + req.getPrice());
        var result = productMarkingService.addProductMarking(req);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/api/v1/productmarking/user/{userid}")
    public ResponseEntity<List<ProductMarkingResponse>> getMarkingsByUser(@PathVariable String userid) {
        LOG.debug("incoming GET productmarking :" + userid);
        var result = productMarkingService.getByUserId(userid);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/api/v1/productmarking/{id}")
    public ResponseEntity<Void> deleteProductMarking(@PathVariable String id) {
        LOG.debug("incoming DELETE productmarking request:" + id);
        productMarkingService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
