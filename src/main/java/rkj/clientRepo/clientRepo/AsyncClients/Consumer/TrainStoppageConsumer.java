package rkj.clientRepo.clientRepo.AsyncClients.Consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import rkj.objLib.objLib.AsynchronousObjects.KafkaObjects.TrainStoppage;

@Component
public class TrainStoppageConsumer {

    private static final Logger logger = LoggerFactory.getLogger(TrainStoppageConsumer.class);

    @KafkaListener(topics = "train-topic",groupId = "train-group")
    public void consumeMessage(TrainStoppage trainStoppage) {
        logger.info(String.format("Consumed the message %s ",trainStoppage.toString()));

    }
}
