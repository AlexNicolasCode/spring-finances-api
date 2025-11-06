package com.finances.api.data.protocols.loadTransactionsRepository;

import com.finances.api.domain.models.TransactionStatusEnum;

import java.util.UUID;

public record LoadTransactionsRepositoryOutputDto(
        UUID id,
        String accountName,
        String type,
        String value,
        TransactionStatusEnum status,
        Boolean isSender
) {}