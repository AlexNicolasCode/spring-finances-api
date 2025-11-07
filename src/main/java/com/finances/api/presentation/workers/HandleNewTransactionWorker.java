package com.finances.api.presentation.workers;

import com.finances.api.domain.usecases.handleNewTransaction.IHandleNewTransactionUsecase;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class HandleNewTransactionWorker {
    private final IHandleNewTransactionUsecase handleNewTransactionUsecase;

    public HandleNewTransactionWorker(
            IHandleNewTransactionUsecase handleNewTransactionUsecase
    ) {
        this.handleNewTransactionUsecase = handleNewTransactionUsecase;
    }

    @RabbitListener(queues = "teste")
    public void receiveMessage(UUID transactionId) {
        System.out.println("Received message: " + transactionId);
        this.handleNewTransactionUsecase.handleNewTransaction(transactionId);
    }
}
