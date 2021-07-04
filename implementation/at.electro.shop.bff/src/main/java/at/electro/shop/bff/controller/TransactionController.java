package at.electro.shop.bff.controller;

import at.electro.shop.bff.api.transactions.models.TransactionRequest;
import at.electro.shop.bff.api.transactions.models.TransactionResponse;
import at.electro.shop.bff.services.TransactionService;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class TransactionController {

  private final TransactionService transactionService;

  public TransactionController(TransactionService transactionService) {
    this.transactionService = transactionService;
  }

  @GetMapping("/transactions")
  public ResponseEntity<List<TransactionResponse>> transactions(
      @RequestParam(defaultValue = "0") Integer pageNo,
      @RequestParam(defaultValue = "10") Integer pageSize,
      @RequestParam(defaultValue = "createdAt") String sortBy
  ) {
    return ResponseEntity.ok(transactionService.get(pageNo, pageSize, sortBy));
  }

  @PostMapping("/transactions")
  public ResponseEntity<List<TransactionResponse>> post(@RequestBody TransactionRequest transactionRequest) {
    transactionService.post(transactionRequest);
    return ResponseEntity.noContent().build();
  }

  @GetMapping("/sellers/{sellerId}/transactions")
  public ResponseEntity<List<TransactionResponse>> getSellerTransactions(@PathVariable String sellerId,
      @RequestParam(defaultValue = "0") Integer pageNo,
      @RequestParam(defaultValue = "10") Integer pageSize,
      @RequestParam(defaultValue = "createdAt") String sortBy) {
    return ResponseEntity.ok(transactionService.getSellerTransactions(sellerId, pageNo, pageSize, sortBy));
  }

  @GetMapping("/buyers/{buyerId}/transactions")
  public ResponseEntity<List<TransactionResponse>> getBuyerTransactions(@PathVariable String buyerId,
      @RequestParam(defaultValue = "0") Integer pageNo,
      @RequestParam(defaultValue = "10") Integer pageSize,
      @RequestParam(defaultValue = "createdAt") String sortBy) {
    return ResponseEntity.ok(transactionService.getBuyerTransactions(buyerId, pageNo, pageSize, sortBy));
  }

  @GetMapping("/users/{userId}/transactions")
  public ResponseEntity<List<TransactionResponse>> getUserTransactions(@PathVariable String userId,
      @RequestParam(defaultValue = "0") Integer pageNo,
      @RequestParam(defaultValue = "10") Integer pageSize,
      @RequestParam(defaultValue = "createdAt") String sortBy) {
    return ResponseEntity.ok(transactionService.getUserTransactions(userId, pageNo, pageSize, sortBy));
  }

  @GetMapping("/api/v1/sellers/{sellerId}/profits")
  public ResponseEntity<BigDecimal> getProfits(@PathVariable String sellerId) {
    return ResponseEntity.ok(transactionService.getProfits(sellerId));
  }
}
