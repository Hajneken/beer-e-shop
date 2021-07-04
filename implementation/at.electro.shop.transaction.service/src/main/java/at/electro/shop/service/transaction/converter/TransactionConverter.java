package at.electro.shop.service.transaction.converter;

import at.electro.shop.service.api.models.ProductRequest;
import at.electro.shop.service.api.models.TransactionRequest;
import at.electro.shop.service.transaction.mapper.TransactionMapper;
import at.electro.shop.service.transaction.models.Transaction;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Service;

@Service
public class TransactionConverter {

  private final TransactionMapper transactionMapper;

  TransactionConverter(TransactionMapper transactionMapper) {
    this.transactionMapper = transactionMapper;
  }

  public List<Transaction> convert(TransactionRequest transaction) {
    List<Transaction> transactions = new ArrayList<>();

    for (ProductRequest product : transaction.getProducts()) {
      Transaction transactionDto = transactionMapper.toDto(transaction);
      transactionDto.setUuid(UUID.randomUUID().toString());
      transactionDto.setPrice(product.getPrice());
      transactionDto.setProduct(product.getId());

      transactions.add(transactionDto);
    }

    return transactions;
  }
}