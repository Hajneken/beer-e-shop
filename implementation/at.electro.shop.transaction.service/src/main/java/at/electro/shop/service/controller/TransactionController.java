package at.electro.shop.service.controller;

import at.electro.shop.service.api.models.TransactionRequest;
import at.electro.shop.service.api.models.TransactionResponse;
import at.electro.shop.service.transaction.models.Transaction;
import at.electro.shop.service.transaction.services.TransactionService;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TransactionController {

  private final TransactionService transactionService;

  @Autowired
  TransactionController(TransactionService transactionService) {
    this.transactionService = transactionService;
  }

  @GetMapping("/api/v1/transactions")
  public ResponseEntity<List<TransactionResponse>> get(
      @RequestParam(defaultValue = "0") Integer pageNo,
      @RequestParam(defaultValue = "10") Integer pageSize,
      @RequestParam(defaultValue = "createdAt") String sortBy
  ) {
    Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
    return ResponseEntity.ok(transactionService.get(paging));
  }

  @GetMapping("/api/v1/sellers/{sellerId}/transactions")
  public ResponseEntity<List<TransactionResponse>> getSellerTransactions(
      @PathVariable String sellerId,
      @RequestParam(defaultValue = "0") Integer pageNo,
      @RequestParam(defaultValue = "10") Integer pageSize,
      @RequestParam(defaultValue = "createdAt") String sortBy
  ) {
    Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
    return ResponseEntity.ok(transactionService.getSellerTransactions(sellerId, paging));
  }

  @GetMapping("/api/v1/sellers/{sellerId}/profits")
  public ResponseEntity<BigDecimal> getProfits(@PathVariable String sellerId) {
    return ResponseEntity.ok(transactionService.getProfits(sellerId));
  }

  @GetMapping("/api/v1/buyers/{buyerId}/transactions")
  public ResponseEntity<List<TransactionResponse>> getBuyerTransactions(
      @PathVariable String buyerId,
      @RequestParam(defaultValue = "0") Integer pageNo,
      @RequestParam(defaultValue = "10") Integer pageSize,
      @RequestParam(defaultValue = "createdAt") String sortBy
  ) {
    Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
    return ResponseEntity.ok(transactionService.getBuyerTransactions(buyerId, paging));
  }

  @GetMapping("/api/v1/users/{userId}/transactions")
  public ResponseEntity<List<TransactionResponse>> getUserTransactions(
      @PathVariable String userId,
      @RequestParam(defaultValue = "0") Integer pageNo,
      @RequestParam(defaultValue = "10") Integer pageSize,
      @RequestParam(defaultValue = "createdAt") String sortBy
  ) {
    Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
    return ResponseEntity.ok(transactionService.getUserTransactions(userId, paging));
  }

  @PostMapping("/api/v1/transactions")
  public ResponseEntity<List<Transaction>> post(@RequestBody TransactionRequest transactionRequest) {
    transactionService.post(transactionRequest);
    return ResponseEntity.noContent().build();
  }
}
