package at.electro.shop.bff.services;

import at.electro.shop.bff.api.inventory.models.product.ProductRequest;
import at.electro.shop.bff.api.inventory.models.product.ProductResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.math.BigDecimal;
import java.util.List;

@FeignClient(name = "${clients.product.name}", url = "${clients.product.url}")
public interface ProductService {
    @RequestMapping(method = RequestMethod.POST, value = "/api/v1/products", produces = "application/json")
    ProductResponse postProduct(@RequestBody ProductRequest productRequest);

    @RequestMapping(method = RequestMethod.PUT, value = "/api/v1/products/{uuid}/rating", produces = "application/json")
    ProductResponse updateProductRating(@PathVariable String uuid, ProductRequest request);

    @RequestMapping(method = RequestMethod.PUT, value = "/api/v1/products/{productId}", produces = "application/json")
    ProductResponse modifyProduct(@PathVariable String productId, @RequestBody ProductRequest productRequest);

    @RequestMapping(method = RequestMethod.PUT, value = "/api/v1/products/{productId}/price", produces = "application/json")
    ProductResponse updateProductPrice(@PathVariable String productId, ProductRequest productRequest);

    @RequestMapping(method = RequestMethod.GET, value = "/api/v1/products", produces = "application/json")
    List<ProductResponse> getProducts();

    @RequestMapping(method = RequestMethod.GET, value = "/api/v1/products/{productId}", produces = "application/json")
    ProductResponse getProductById(@PathVariable String productId);
}