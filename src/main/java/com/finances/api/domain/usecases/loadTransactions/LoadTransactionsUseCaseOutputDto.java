package com.finances.api.domain.usecases.loadTransactions;

import com.finances.api.domain.models.TransactionStatusEnum;

import java.util.UUID;

public record LoadTransactionsUseCaseOutputDto(
        UUID id,
        String accountName,
        String type,
        TransactionStatusEnum status,
        String value,
        Boolean isSender
) {
}
