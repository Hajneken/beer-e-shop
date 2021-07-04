package at.electro.shop.service.transaction.dao.persistence;

import at.electro.shop.service.transaction.dao.models.TransactionEntity;
import java.util.List;

public interface EventStore {
  void publish(List<TransactionEntity> transactions);
}
