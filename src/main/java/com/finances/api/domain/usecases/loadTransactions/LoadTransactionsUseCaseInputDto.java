package com.finances.api.domain.usecases.loadTransactions;

import java.util.UUID;

public record LoadTransactionsUseCaseInputDto(
        UUID accountId,
        String search
) {}
