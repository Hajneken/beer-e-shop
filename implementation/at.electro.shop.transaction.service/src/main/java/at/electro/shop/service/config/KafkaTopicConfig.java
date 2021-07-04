package at.electro.shop.service.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.kafka.config.TopicBuilder;

@Profile("default")
@Configuration
public class KafkaTopicConfig {

  @Bean
  public NewTopic transactionAdded() {
    return TopicBuilder.name("transaction.added").build();
  }

  @Bean
  public NewTopic transactionListAdded() {
    return TopicBuilder.name("transaction.list.added").build();
  }
}