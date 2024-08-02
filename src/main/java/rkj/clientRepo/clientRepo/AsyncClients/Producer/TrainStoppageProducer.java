package rkj.clientRepo.clientRepo.AsyncClients.Producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.StreamReadFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.admin.NewTopic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;
import rkj.objLib.objLib.AsynchronousObjects.KafkaObjects.TrainStoppage;

import java.nio.charset.StandardCharsets;

@Component
public class TrainStoppageProducer {

    //@Value("${spring.kafka.topic-name}")

    private static final Logger logger = LoggerFactory.getLogger(TrainStoppageProducer.class);

    private NewTopic topicName;

    private KafkaTemplate<String, TrainStoppage> kafkaTemplate;

    private ObjectMapper mapper = new ObjectMapper();

    public TrainStoppageProducer(@Qualifier("trainStoppageTopic") NewTopic topicName, KafkaTemplate kafkaTemplate){
        this.topicName=topicName;
        this.kafkaTemplate=kafkaTemplate;
    }

    public void sendMessage(TrainStoppage trainStoppage) throws JsonProcessingException {
        logger.info(String.format("Produce the message %s",trainStoppage));
        String payload = mapper.writeValueAsString(trainStoppage);
        Message<byte[]> message = MessageBuilder
                .withPayload(payload.getBytes(StandardCharsets.UTF_8))
                .setHeader(KafkaHeaders.TOPIC, topicName.name())
                .build();
        kafkaTemplate.send(message);
    }
}
