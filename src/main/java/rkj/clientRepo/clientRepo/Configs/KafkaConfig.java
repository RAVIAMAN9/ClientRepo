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
    public NewTopic ticketTopic(){
        return TopicBuilder
                .name("ticketTopic")
                .build();
    }

    @Bean
    public NewTopic trainStoppageTopic(){
        return TopicBuilder.name("trainStoppageTopic")
                .build();
    }
}
