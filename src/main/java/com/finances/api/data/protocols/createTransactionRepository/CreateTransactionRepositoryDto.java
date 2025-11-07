package com.finances.api.data.protocols.createTransactionRepository;

import com.finances.api.domain.models.TransactionTypeEnum;

import java.util.UUID;

public record CreateTransactionRepositoryDto(
        float value,
        TransactionTypeEnum type,
        UUID fromAccountId,
        UUID targetAccountId
) {}
