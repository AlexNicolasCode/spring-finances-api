package com.finances.api.domain.usecases.handleNewTransaction;

import java.util.UUID;

public interface IHandleNewTransactionUsecase {
    void handleNewTransaction(UUID transactionId);
}
