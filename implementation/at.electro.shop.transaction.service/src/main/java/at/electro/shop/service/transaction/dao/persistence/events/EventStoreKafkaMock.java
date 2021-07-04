package at.electro.shop.service.transaction.dao.persistence.events;

import at.electro.shop.service.transaction.dao.persistence.EventStore;
import at.electro.shop.service.transaction.dao.models.TransactionEntity;
import java.util.List;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Profile("dev")
@Service
public class EventStoreKafkaMock implements EventStore {

  @Override
  public void publish(List<TransactionEntity> transactions) {

  }
}
