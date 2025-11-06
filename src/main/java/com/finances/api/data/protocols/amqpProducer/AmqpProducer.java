package com.finances.api.data.protocols.amqpProducer;

public interface AmqpProducer {
    void sendMessage(String exchange, String routingKey, String message);
}
