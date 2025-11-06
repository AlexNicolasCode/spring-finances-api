package com.finances.api.infra.amqp;

import com.finances.api.data.protocols.amqpProducer.AmqpProducer;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;

public class RabbitMqProducer implements AmqpProducer {
    @Value("${queues.transactions}")
    private String transactionsQueue;

    private final RabbitTemplate rabbitTemplate;

    public RabbitMqProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendMessage(String exchange, String routingKey, String message) {
        this.rabbitTemplate.convertAndSend(transactionsQueue, message);
    }
}
