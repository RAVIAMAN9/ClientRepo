package rkj.clientRepo.clientRepo.AsyncClients.Producer;

import org.apache.kafka.clients.admin.NewTopic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;
import rkj.objLib.objLib.AsynchronousObjects.KafkaObjects.TrainStoppage;

@Component
public class TrainStoppageProducer {

    //@Value("${spring.kafka.topic-name}")

    private static final Logger logger = LoggerFactory.getLogger(TrainStoppageProducer.class);

    private NewTopic topicName;

    private KafkaTemplate<String, TrainStoppage> kafkaTemplate;

    public TrainStoppageProducer(NewTopic topicName,KafkaTemplate kafkaTemplate){
        this.topicName=topicName;
        this.kafkaTemplate=kafkaTemplate;
    }

    public void sendMessage(TrainStoppage trainStoppage) {
        Message<TrainStoppage> message = MessageBuilder
                .withPayload(trainStoppage)
                .setHeader(KafkaHeaders.TOPIC, topicName.name())
                .build();
        kafkaTemplate.send(message);
    }
}
