package rkj.clientRepo.clientRepo.AsyncClients.Consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import rkj.Repository.Repo.TrainRepositories.TrainPersistance;
import rkj.objLib.objLib.AsynchronousObjects.RabbitMqObjects.TicketEvent;

@Component
public class TicketEventConsumer {

    @Autowired
    private TrainPersistance trainPersistance;

    private static final Logger logger = LoggerFactory.getLogger(TicketEventConsumer.class);

    @RabbitListener(queues = "ticketQueue")
    public void consumeMessage(TicketEvent ticketEvent){
        logger.info(String.format("Ticket event %s",ticketEvent.toString()));
        System.out.println(ticketEvent.getNumberOfSeats()+" "+ticketEvent.getIsCancellable());
        trainPersistance.updateTrain(ticketEvent);
    }
}
