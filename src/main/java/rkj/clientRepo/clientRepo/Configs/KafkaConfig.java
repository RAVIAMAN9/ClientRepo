package rkj.clientRepo.clientRepo.Configs;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaConfig {

//    @Value("${spring.kafka.topic-name}")
    private String trainTopicName;

    @Bean
    public NewTopic trainTopic(){
        return TopicBuilder
                .name("train-topic")
                .build();
    }
}
