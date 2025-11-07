package com.finances.api.data.protocols.loadTransactionByIdRepository;

import com.finances.api.domain.models.TransactionStatusEnum;
import com.finances.api.domain.models.TransactionTypeEnum;

import java.util.UUID;

public record LoadTransactionByIdRepositoryOutputDto(
        UUID id,
        TransactionTypeEnum type,
        TransactionStatusEnum status,
        float value,
        UUID fromAccountId,
        UUID targetAccountId
) {}
