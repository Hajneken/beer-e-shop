package at.electro.shop.service.transaction.services;

import at.electro.shop.service.api.models.TransactionRequest;
import at.electro.shop.service.api.models.TransactionResponse;
import at.electro.shop.service.transaction.converter.TransactionConverter;
import at.electro.shop.service.transaction.dao.TransactionDao;
import at.electro.shop.service.transaction.mapper.TransactionMapper;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {

  private TransactionMapper transactionMapper;
  private TransactionDao transactionDao;
  private TransactionConverter transactionConverter;

  public TransactionService(TransactionMapper transactionMapper,
      TransactionDao transactionDao,
      TransactionConverter transactionConverter) {
    this.transactionMapper = transactionMapper;
    this.transactionDao = transactionDao;
    this.transactionConverter = transactionConverter;
  }

  public List<TransactionResponse> get(Pageable pageable) {
    return transactionMapper.toApiList(transactionDao.fetchAll(pageable));
  }

  public List<TransactionResponse> getUserTransactions(String user, Pageable paging) {
    List<TransactionResponse> transactions = getBuyerTransactions(user, paging);
    transactions.addAll(getSellerTransactions(user, paging));
    return transactions;
  }

  public List<TransactionResponse> getBuyerTransactions(String buyer, Pageable paging) {
    return transactionMapper.toApiList(transactionDao.getBuyer(buyer, paging));
  }

  public List<TransactionResponse> getSellerTransactions(String seller, Pageable paging) {
    return transactionMapper.toApiList(transactionDao.getSeller(seller, paging));
  }

  public void post(TransactionRequest transaction) {
    transactionDao.create(transactionConverter.convert(transaction));
  }

  public BigDecimal getProfits(String sellerId) {
    return transactionDao.getProfits(sellerId);
  }
}