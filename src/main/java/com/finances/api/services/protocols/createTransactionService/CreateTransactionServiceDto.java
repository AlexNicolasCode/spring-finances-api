package com.finances.api.services.protocols.createTransactionService;

import com.finances.api.domain.models.TransactionTypeEnum;

import java.util.UUID;

public record CreateTransactionServiceDto(
        float value,
        TransactionTypeEnum type,
        UUID fromAccountId,
        UUID targetAccountId
) {}
