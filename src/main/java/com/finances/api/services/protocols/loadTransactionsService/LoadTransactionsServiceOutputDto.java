package com.finances.api.services.protocols.loadTransactionsService;

import com.finances.api.domain.models.TransactionStatusEnum;

import java.util.UUID;

public record LoadTransactionsServiceOutputDto(
        UUID id,
        String accountName,
        String type,
        TransactionStatusEnum status,
        String value,
        Boolean isSender
) {}
