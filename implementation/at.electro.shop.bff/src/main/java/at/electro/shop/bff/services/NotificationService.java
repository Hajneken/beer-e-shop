package at.electro.shop.bff.services;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import at.electro.shop.bff.api.notification.models.ProductMarkingRequest;
import at.electro.shop.bff.api.notification.models.ProductMarkingResponse;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@FeignClient(name = "${clients.notification.name}", url = "${clients.notification.url}")
public interface NotificationService {
    @RequestMapping(method = RequestMethod.POST, value = "/api/v1/productmarking", consumes = "application/json")
    ProductMarkingResponse post(@RequestBody ProductMarkingRequest productMarking);

    @RequestMapping(method = RequestMethod.GET, value = "/api/v1/productmarking/user/{userid}", consumes = "application/json")
    List<ProductMarkingResponse> get(@PathVariable String userid);

    @RequestMapping(method = RequestMethod.DELETE, value = "/api/v1/productmarking/{id}", consumes = "application/json")
    ProductMarkingResponse delete(@PathVariable String id);
}
