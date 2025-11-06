package com.finances.api.services.protocols.loadTransactionsService;

import java.util.UUID;

public record LoadTransactionsServiceOutputDto(
        UUID id,
        String accountName,
        String type,
        String value
) {}
