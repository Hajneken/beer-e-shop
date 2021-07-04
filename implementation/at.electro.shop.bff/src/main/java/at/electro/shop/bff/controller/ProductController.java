package at.electro.shop.bff.controller;

import at.electro.shop.bff.api.inventory.models.product.ProductRequest;
import at.electro.shop.bff.api.inventory.models.product.ProductResponse;
import at.electro.shop.bff.services.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    private Logger LOG = LoggerFactory.getLogger(ProductController.class);
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    //    Create a new product
    @PostMapping("/api/v1/products")
    public ResponseEntity<ProductResponse> postProduct(@RequestBody ProductRequest productRequest) {
        ProductResponse productResponse = productService.postProduct(productRequest);
        LOG.info("New product with id" + productResponse.getUuid() + " created");
        return ResponseEntity.ok(productResponse);
    }

    // Update rating
    @PutMapping("/api/v1/products/{uuid}/rating")
    public ResponseEntity<ProductResponse> updateProductRating(@RequestBody ProductRequest productRequest, @PathVariable String uuid) {
        ProductResponse productResponse = productService.updateProductRating(uuid, productRequest);
        LOG.info("Rating of product " + uuid + " updated");
        return ResponseEntity.ok(productResponse);
    }

    //    Update a specific product
    @PutMapping("/api/v1/products/{productId}")
    public ResponseEntity<ProductResponse> putProduct(@PathVariable String productId, @RequestBody ProductRequest productRequest) {
        ProductResponse productResponse = productService.modifyProduct(productId, productRequest);
        LOG.info("product " + productId + " modified");
        return ResponseEntity.ok(productResponse);
    }

    @PutMapping("/api/v1/products/{productId}/price")
    public ResponseEntity<ProductResponse> updatePrice(@PathVariable String productId, @RequestBody ProductRequest productRequest) {
        ProductResponse productResponse = productService.updateProductPrice(productId, productRequest);
        LOG.info("Price of product " + productId + " modified");
        return ResponseEntity.ok(productResponse);
    }

    // Get all products
    @GetMapping("/api/v1/products")
    public ResponseEntity<List<ProductResponse>> getProduct() {
        List<ProductResponse> productResponses = productService.getProducts();
        LOG.info("products retrieved");
        return ResponseEntity.ok(productResponses);
    }

    //    Get Single Product
    @GetMapping("/api/v1/products/{productId}")
    public ResponseEntity<ProductResponse> getProduct(@PathVariable String productId) {
        ProductResponse productResponse = productService.getProductById(productId);
        LOG.info("product " + productId + " retrieved");
        return ResponseEntity.ok(productResponse);
    }
}
