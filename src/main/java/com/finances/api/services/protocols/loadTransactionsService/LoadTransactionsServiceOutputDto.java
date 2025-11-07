package com.finances.api.services.protocols.loadTransactionsService;

import com.finances.api.domain.models.TransactionStatusEnum;
import com.finances.api.domain.models.TransactionTypeEnum;

import java.util.UUID;

public record LoadTransactionsServiceOutputDto(
        UUID id,
        String accountName,
        TransactionTypeEnum type,
        TransactionStatusEnum status,
        String value,
        Boolean isSender
) {}
