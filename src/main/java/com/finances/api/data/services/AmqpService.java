package com.finances.api.data.services;

import com.finances.api.data.protocols.amqpProducer.AmqpProducer;
import com.finances.api.services.protocols.sendTransactionToQueueService.ISendTransactionToQueueService;

import java.util.UUID;

public class AmqpService implements ISendTransactionToQueueService {
    private final AmqpProducer amqpProducer;

    public AmqpService(AmqpProducer amqpProducer) {
        this.amqpProducer = amqpProducer;
    }

    public void sendTransactionToQueue(UUID transactionId) {
        amqpProducer.sendMessage("transactions", "transactions", transactionId.toString());
    }
}