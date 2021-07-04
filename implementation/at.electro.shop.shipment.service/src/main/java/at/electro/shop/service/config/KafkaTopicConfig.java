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
    public NewTopic shipmentUpdated() {
        return TopicBuilder.name("shipment.updated").build();
    }
}