package rkj.clientRepo.clientRepo.AsyncClients.Consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.StreamReadFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import rkj.Repository.Repo.StationRepository.StationPersistence;
import rkj.Repository.Repo.StationRepository.StationRepo;
import rkj.Repository.Repo.TrainRepositories.TrainPersistance;
import rkj.Repository.Repo.TrainRepositories.TrainRepo;
import rkj.objLib.objLib.AsynchronousObjects.KafkaObjects.TrainStoppage;

@Component
public class TrainStoppageConsumer {

    @Autowired
    private StationPersistence stationPersistence;

    @Autowired
    private TrainPersistance trainPersistance;

    private ObjectMapper mapper = new ObjectMapper();

    private static final Logger logger = LoggerFactory.getLogger(TrainStoppageConsumer.class);

    @KafkaListener(topics = "trainStoppageTopic",groupId = "train-group")
    public void consumeMessage(String message) throws JsonProcessingException {
        mapper.enable(StreamReadFeature.INCLUDE_SOURCE_IN_LOCATION.mappedFeature());
        logger.info(String.format("Consumed the message %s ",message));
        TrainStoppage trainStoppage = mapper.readValue(message,TrainStoppage.class);
        String stationCode = trainStoppage.getStoppageCode();
        Integer trainNumber = trainStoppage.getTrainNumber();
        stationPersistence.updateTrainStoppage(stationCode, trainNumber);
        trainPersistance.updateStoppagesInTrain(stationCode, trainNumber);
    }
}
