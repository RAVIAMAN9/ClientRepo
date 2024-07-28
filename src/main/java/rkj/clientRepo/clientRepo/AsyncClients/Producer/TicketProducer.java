package rkj.clientRepo.clientRepo.AsyncClients.Producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;
import rkj.objLib.objLib.AsynchronousObjects.RabbitMqObjects.TicketEvent;

@Component
public class TicketProducer {

    private static final Logger logger = LoggerFactory.getLogger(TicketProducer.class);

//    @Value("${spring.exchange.ticketevent.routing}")
//    private String routingKey;
//    @Value("${spring.exchange.ticketevent.name}")
//    private String exchange;

    private static final Integer cancellationFlag = 1;

    private RabbitTemplate template;

    public TicketProducer(RabbitTemplate template) {
        this.template = template;
    }

    public void sendMessage(TicketEvent ticketEvent) {
        logger.info(String.format("Sending Ticket Event -> %s", ticketEvent.toString()));
            template.convertAndSend("trainAppExchange","ticket-event-key",ticketEvent);
    }



}
