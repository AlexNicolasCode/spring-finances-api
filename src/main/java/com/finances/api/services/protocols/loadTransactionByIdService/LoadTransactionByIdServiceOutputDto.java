package com.finances.api.services.protocols.loadTransactionByIdService;

import com.finances.api.domain.models.TransactionStatusEnum;
import com.finances.api.domain.models.TransactionTypeEnum;

import java.util.UUID;

public record LoadTransactionByIdServiceOutputDto(
        UUID id,
        TransactionTypeEnum type,
        TransactionStatusEnum status,
        float value,
        UUID fromAccountId,
        UUID targetAccountId
) {}
