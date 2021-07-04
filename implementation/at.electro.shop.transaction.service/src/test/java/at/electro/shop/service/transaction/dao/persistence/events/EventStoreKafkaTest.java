package at.electro.shop.service.transaction.dao.persistence.events;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import at.electro.shop.service.transaction.suppliers.TransactionSupplier;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.kafka.core.KafkaTemplate;

@ExtendWith(MockitoExtension.class)
class EventStoreKafkaTest {

  @Mock
  private KafkaTemplate<String, String> kafkaTemplate;

  @Mock
  private ObjectMapper objectMapper;

  @InjectMocks
  private EventStoreKafka eventStoreKafka;

  @Test
  @DisplayName("Should publish message")
  public void shouldPublishTransactions() throws JsonProcessingException {
    when(objectMapper.writeValueAsString(any())).thenReturn("message");

    eventStoreKafka.publish(List.of(TransactionSupplier.transactionEntityMock()));

    verify(kafkaTemplate, times(1)).send("transaction.list.added", "message");
  }

  @Test
  @DisplayName("Should throw error and continue normally")
  public void shouldThrowJsonProcessingException() throws JsonProcessingException {
    when(objectMapper.writeValueAsString(any())).thenThrow(new JsonProcessingException("Error") {
    });

    eventStoreKafka.publish(List.of());

    verify(kafkaTemplate, never()).send("transaction.list.added", "message");
  }

  @Test
  @DisplayName("Should normally process message in Mock")
  public void shouldPublishTransactionsInMock() throws JsonProcessingException {
    eventStoreKafka.publish(List.of(TransactionSupplier.transactionEntityMock()));
  }
}