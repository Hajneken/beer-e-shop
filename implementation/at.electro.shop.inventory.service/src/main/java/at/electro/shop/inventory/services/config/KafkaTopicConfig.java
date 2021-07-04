package at.electro.shop.inventory.services.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.kafka.config.TopicBuilder;

@Profile("default")
@Configuration
public class KafkaTopicConfig {
    //  topic issued is the publishing method
    @Bean
    public NewTopic topicIssued() {
        return TopicBuilder.name("product.update").build();
    }

}
