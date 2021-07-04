package at.electro.shop.service.transaction.dao.persistence.events;

import at.electro.shop.service.transaction.dao.persistence.EventStore;
import at.electro.shop.service.transaction.dao.models.TransactionEntity;
import at.electro.shop.service.transaction.services.TransactionService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Profile("default")
@Service
class EventStoreKafka implements EventStore {
  private static final Logger LOG = LoggerFactory.getLogger(TransactionService.class);

  private KafkaTemplate<String, String> kafkaTemplate;
  private ObjectMapper objectMapper;

  public EventStoreKafka(KafkaTemplate<String, String> kafkaTemplate, ObjectMapper objectMapper) {
    this.kafkaTemplate = kafkaTemplate;
    this.objectMapper = objectMapper;
  }

  private void publish(String topic, String data) {
    kafkaTemplate.send(topic, data);
  }

  public void publish(List<TransactionEntity> transactions){
    try {
      publish("transaction.list.added", objectMapper.writeValueAsString(transactions));
    } catch (JsonProcessingException jsonProcessingException) {
      LOG.error("Parsing unsuccessful for transaction list");
    }
  }
}
