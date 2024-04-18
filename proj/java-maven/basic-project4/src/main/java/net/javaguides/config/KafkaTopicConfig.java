package net.javaguides.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {

    @Bean
    public NewTopic configTopic(){
        return TopicBuilder.name("my-topic")
                .partitions(10)
                .build();
    }

    @Bean
    public NewTopic configJsonTopic(){
        return TopicBuilder.name("my-json-topic")
                .partitions(10)
                .build();
    }
}
