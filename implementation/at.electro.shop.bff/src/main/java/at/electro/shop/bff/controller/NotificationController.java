package at.electro.shop.bff.controller;

import at.electro.shop.bff.api.notification.models.ProductMarkingRequest;
import at.electro.shop.bff.api.notification.models.ProductMarkingResponse;
import at.electro.shop.bff.services.NotificationService;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class NotificationController {
    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @PostMapping("/notification/productmarking")
    public ResponseEntity<ProductMarkingResponse> addProductMarking(@RequestBody ProductMarkingRequest request) {
        return ResponseEntity.ok(notificationService.post(request));
    }

    @GetMapping("/notification/productmarking/user/{userid}")
    public ResponseEntity<List<ProductMarkingResponse>> getMarkings(@PathVariable String userid) {
        return ResponseEntity.ok(notificationService.get(userid));
    }

    @DeleteMapping("/notification/productmarking/{id}")
    public ResponseEntity<ProductMarkingResponse> deleteMarking(@PathVariable String id) {
        return ResponseEntity.ok(notificationService.delete(id));
    }
}
