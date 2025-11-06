package com.finances.api.services.protocols.sendTransactionToQueueService;

import java.util.UUID;

public interface ISendTransactionToQueueService {
    void sendTransactionToQueue(UUID transactionId);
}
