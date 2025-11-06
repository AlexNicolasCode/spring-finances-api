package com.finances.api.domain.usecases.loadTransactions;

import java.util.UUID;

public record LoadTransactionsUseCaseOutputDto(
        UUID id,
        String accountName,
        String type,
        String value,
        Boolean isSender
) {
}
