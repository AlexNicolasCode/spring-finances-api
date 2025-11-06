package com.finances.api.domain.usecases.loadTransactions;

import java.util.UUID;

public record LoadTransactionsUseCaseOutputDto(
        UUID id,
        String targetName,
        String type,
        String value
) {
}
