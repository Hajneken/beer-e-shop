package at.electro.shop.service.transaction.dao;

import at.electro.shop.service.transaction.dao.models.TransactionEntity;
import at.electro.shop.service.transaction.dao.persistence.EventStore;
import at.electro.shop.service.transaction.dao.persistence.TransactionRepository;
import at.electro.shop.service.transaction.mapper.TransactionMapper;
import at.electro.shop.service.transaction.models.Transaction;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TransactionDao {

  private TransactionMapper transactionMapper;
  private TransactionRepository transactionRepository;
  private EventStore eventStore;

  public TransactionDao(TransactionMapper transactionMapper,
      TransactionRepository transactionRepository,
      EventStore eventStore) {
    this.transactionMapper = transactionMapper;
    this.transactionRepository = transactionRepository;
    this.eventStore = eventStore;
  }

  public List<Transaction> fetchAll(Pageable pageable) {
    Page<TransactionEntity> pagedResult = transactionRepository.findAll(pageable);
    return mapToTransaction(pagedResult);
  }

  public List<Transaction> getSeller(String seller, Pageable pageable) {
    Page<TransactionEntity> pagedResult = transactionRepository.findBySeller(seller, pageable);
    return mapToTransaction(pagedResult);
  }

  public List<Transaction> getBuyer(String buyer, Pageable pageable) {
    Page<TransactionEntity> pagedResult = transactionRepository.findByBuyer(buyer, pageable);
    return mapToTransaction(pagedResult);
  }

  public List<Transaction> create(List<Transaction> transaction) {
    List<TransactionEntity> entities = transactionMapper.toDaoList(transaction);
    transactionRepository.saveAll(entities);
    eventStore.publish(entities);

    return transactionMapper.toDtoList(entities);
  }

  private List<Transaction> mapToTransaction(Page<TransactionEntity> pagedResult) {
    if (pagedResult.hasContent()) {
      return transactionMapper.toDtoList(pagedResult.getContent());
    }
    return new ArrayList<>();
  }

  public BigDecimal getProfits(String seller) {
    BigDecimal profit = transactionRepository.calculateProfit(seller);

    if (profit == null) {
      profit = BigDecimal.ZERO;
    }

    return profit;
  }
}
