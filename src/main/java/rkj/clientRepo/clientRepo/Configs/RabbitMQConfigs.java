package rkj.clientRepo.clientRepo.Configs;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfigs {

//    @Value("${spring.queue.ticketevent.name}")
//    public String ticketEventQueue;
//    @Value("${spring.exchange.ticketevent.name}")
//    public String ticketEventExchange;
//    @Value("${spring.exchange.ticketevent.routing}")
//    public String ticketEventRoutingKey;

    @Bean
    public Queue getTicketEventQueue() {
        return new Queue("ticketQueue");
    }

    @Bean
    public TopicExchange getTicketEventExchange() {
        return new TopicExchange("trainAppExchange");
    }

    @Bean
    public Binding bindingTicketEvent() {
        return BindingBuilder
                .bind(getTicketEventQueue())
                .to(getTicketEventExchange())
                .with("ticket-event-key");
    }

    @Bean
    public MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public AmqpTemplate amqpTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter());
        return rabbitTemplate;
    }

}
