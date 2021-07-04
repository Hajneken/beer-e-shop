package at.electro.shop.bff.services;

import at.electro.shop.bff.api.transactions.models.TransactionRequest;
import at.electro.shop.bff.api.transactions.models.TransactionResponse;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "${clients.transactions.name}", url = "${clients.transactions.url}")
public interface TransactionService {

    @RequestMapping(method = RequestMethod.GET, value = "/api/v1/transactions", produces = "application/json")
    List<TransactionResponse> get(@RequestParam Integer pageNo, @RequestParam Integer pageSize,
            @RequestParam String sortBy);

    @RequestMapping(method = RequestMethod.GET, value = "/api/v1/sellers/{sellerId}/transactions", produces = "application/json")
    List<TransactionResponse> getSellerTransactions(@PathVariable String sellerId, @RequestParam Integer pageNo,
            @RequestParam Integer pageSize, @RequestParam String sortBy);

    @RequestMapping(method = RequestMethod.GET, value = "/api/v1/buyers/{buyerId}/transactions", produces = "application/json")
    List<TransactionResponse> getBuyerTransactions(@PathVariable String buyerId, @RequestParam Integer pageNo,
        @RequestParam Integer pageSize, @RequestParam String sortBy);

    @RequestMapping(method = RequestMethod.GET, value = "/api/v1/users/{userId}/transactions", produces = "application/json")
    List<TransactionResponse> getUserTransactions(@PathVariable String userId, @RequestParam Integer pageNo,
        @RequestParam Integer pageSize, @RequestParam String sortBy);

    @RequestMapping(method = RequestMethod.POST, value = "/api/v1/transactions", consumes = "application/json")
    void post(TransactionRequest transactions);

    @RequestMapping(method = RequestMethod.GET, value = "/api/v1/sellers/{sellerId}/profits", consumes = "application/json")
    BigDecimal getProfits(@PathVariable String sellerId);
}
