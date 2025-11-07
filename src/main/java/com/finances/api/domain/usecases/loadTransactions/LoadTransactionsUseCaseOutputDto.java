package com.finances.api.domain.usecases.loadTransactions;

import com.finances.api.domain.models.TransactionStatusEnum;
import com.finances.api.domain.models.TransactionTypeEnum;

import java.util.UUID;

public record LoadTransactionsUseCaseOutputDto(
        UUID id,
        String accountName,
        TransactionTypeEnum type,
        TransactionStatusEnum status,
        String value,
        Boolean isSender
) {
}
