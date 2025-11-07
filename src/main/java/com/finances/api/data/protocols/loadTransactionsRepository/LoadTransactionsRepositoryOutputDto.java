package com.finances.api.data.protocols.loadTransactionsRepository;

import com.finances.api.domain.models.TransactionStatusEnum;
import com.finances.api.domain.models.TransactionTypeEnum;

import java.util.UUID;

public record LoadTransactionsRepositoryOutputDto(
        UUID id,
        String accountName,
        TransactionTypeEnum type,
        String value,
        TransactionStatusEnum status,
        Boolean isSender
) {}